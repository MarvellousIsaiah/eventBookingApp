package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.dto.request.AddDiscountRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.AddTicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.OrganizersRequest;

import com.semicolon.africa.eventbookingapplication.dto.request.ViewAllEventAttendeeRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.DiscountResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.EventResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.GuestResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.OrganizersResponse;
import com.semicolon.africa.eventbookingapplication.models.Discount;
import com.semicolon.africa.eventbookingapplication.models.Organizer;
import com.semicolon.africa.eventbookingapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizersServiceImpl implements OrganizerService {
    private final Organizers organizers;
    private final ModelMapper modelMapper;
    private  final Tickets tickets;
    private final Events events;
    private final Discounts discounts;
    private final Guests guests;
//    public OrganizerResponse register(OrganizerRequest organizerRequest) {
//        if (organizers.findOrganizerByEmailIgnoreCase(organizerRequest.getEmail()).isPresent())
//            throw new RuntimeException("you are already registered");
//        Organizer organizer = Organizer.builder()
//                .email(organizerRequest.getEmail()).password(organizerRequest.getPassword())
//                .UserName(organizerRequest.getUsername()).build();
//        return modelMapper.map(organizers.save(organizer), OrganizerResponse.class);
//    }



@Override
public OrganizersResponse register(OrganizersRequest organizersRequest){
    if(organizers.findOrganizerByEmailIgnoreCase(organizersRequest.getEmail()).isPresent())
        throw new RuntimeException("you are already registered");
    Organizer organizer = Organizer.builder()
            .email(organizersRequest.getEmail()).password(organizersRequest.getPassword())
            .UserName(organizersRequest.getUsername()).build();
    organizer =organizers.save(organizer);
    return modelMapper.map(organizer,OrganizersResponse.class);
}

    @Override
    public EventResponse addTicketTo(AddTicketRequest addTicketRequest) {
    var ticket =tickets.findTicketById(addTicketRequest.getTicketId())
            .orElseThrow(()-> new RuntimeException("ticket not found"));
    ticket.setEvent(events.findById(addTicketRequest.getEventId()).get());
    tickets.save(ticket);
    return modelMapper.map(ticket.getEvent(),EventResponse.class);
    }

    @Override
    public DiscountResponse addDiscountForTicket(AddDiscountRequest addDiscountRequest) {
    var ticket=tickets.findTicketById(addDiscountRequest.getTicketId()).orElseThrow(()-> new RuntimeException("ticket not found"));
    if(!isValidPercentage(addDiscountRequest.getPercentageDiscount()))throw new RuntimeException("invalid percentage discount");
    BigDecimal percentageDiscount = calculateDiscountOnTicketPrice(ticket.getPrice(),addDiscountRequest.getPercentageDiscount());
    Discount discount = Discount.builder().expirationDate(addDiscountRequest.getExpirationDate()).ticketType(addDiscountRequest.getTicketType()).expirationTime(addDiscountRequest.getExpirationTime()).percentageDiscount(addDiscountRequest.getPercentageDiscount()).build();
    var saveDiscount = discounts.save(discount);
    ticket.setPrice(ticket.getPrice().subtract(percentageDiscount));tickets.save(ticket);
    return modelMapper.map(saveDiscount,DiscountResponse.class);


    }

    @Override
    public List<GuestResponse> viewAllEventAttendees(ViewAllEventAttendeeRequest viewAllEventAttendeeRequest) {

        return guests.getGuestByEventId(viewAllEventAttendeeRequest.getEventId()).stream().map(guests ->modelMapper.map(guests,GuestResponse.class) ).toList();
    }

    private boolean isValidPercentage(int percentage){return percentage >= 0 && percentage <= 100;}


    private BigDecimal calculateDiscountOnTicketPrice(BigDecimal price, int percentage){
    return price.multiply(BigDecimal.valueOf(percentage).divide(BigDecimal.valueOf(100l)));

    }
}















