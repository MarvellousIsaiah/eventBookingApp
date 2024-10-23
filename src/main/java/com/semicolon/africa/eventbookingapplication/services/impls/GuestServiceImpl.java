package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.dto.request.*;
import com.semicolon.africa.eventbookingapplication.dto.response.DiscountResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.EventResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.GuestResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.OrganizersResponse;
import com.semicolon.africa.eventbookingapplication.models.Guest;
import com.semicolon.africa.eventbookingapplication.models.Ticket;
import com.semicolon.africa.eventbookingapplication.repository.Events;
import com.semicolon.africa.eventbookingapplication.repository.Guests;
import com.semicolon.africa.eventbookingapplication.repository.Tickets;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService{
   private  final Events events;
    private final Tickets tickets;
    private final TicketServicesImpl ticketServices;
    private final Guests guest;
    private  final ModelMapper modelMapper;


    @Override
    public Ticket purchaseTicket(GuestTicketRequest guestTicketRequest) {
        var ticket = tickets.findTicketByEventAndTicketType(guestTicketRequest.getEventId(), guestTicketRequest.getTicketType());
        ticketServices.purchaseTicket(ticket.get().getId());
        Guest guest = Guest.builder().email(guestTicketRequest.getEmail()).ticket(ticket.get())
                .phoneNumber(guestTicketRequest.getPhoneNumber()).build();
                 guest.save(guest); return ticket.get();


    }

    @Override
    public Ticket reserveTicket(GuestTicketRequest guestTicketRequest) {
        var ticket = tickets.findTicketByEventAndTicketType(guestTicketReserveRequest.getEventId(),guestTicketRequest.getTicketType());
        ticketServices.reserveTicket(ticket.get().getId());
        ticket = tickets.findTicketById(ticket.get().getId());
        Guest guest = Guest.builder().email(guestTicketReserveRequest.getEmail()).ticket(ticket.get()).phoneNumber(guestTicketReserveRequest.getPhoneNumber()).build();
        guest.save(guest); return ticket.get();

        return null;
    }
}
