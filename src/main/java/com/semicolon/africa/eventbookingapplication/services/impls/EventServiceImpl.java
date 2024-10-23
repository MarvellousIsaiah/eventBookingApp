package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.dto.request.AddEventRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.PurchaseTicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.ReservedTicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.EventResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.PurchaseTicketResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.TicketResponse;
import com.semicolon.africa.eventbookingapplication.models.Event;
import com.semicolon.africa.eventbookingapplication.models.Organizer;
import com.semicolon.africa.eventbookingapplication.repository.Addresses;
import com.semicolon.africa.eventbookingapplication.repository.Events;
import com.semicolon.africa.eventbookingapplication.repository.Organizers;
import com.semicolon.africa.eventbookingapplication.services.impls.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final Organizers organizers;
    private  final Addresses addresses;
    private final Events events;
    private final ModelMapper modelMapper;
    @Override
    public TicketResponse reserveTicket(ReservedTicketRequest reservedTicketRequest) {

        return null;
    }

    @Override
    public PurchaseTicketResponse purchaseTicket(PurchaseTicketRequest purchaseTicketRequest) {
        return null;
    }

    @Override
    public List<EventResponse> viewAllEventsFor(Long organizerId) {
        return events.getEventsByOrganizerId(organizerId).stream().map(event -> modelMapper.map(event,EventResponse.class)).toList();

    }

    @Override
    public Event addEvent(AddEventRequest addEventRequest) {
                Event event = Event.builder().organizer(organizers.findOrganizerById( addEventRequest.getOrganizerId())
                .orElseThrow(()->new RuntimeException("you don't know what you doing")))
                .eventTitle(addEventRequest.getEventTitle()).eventDate(addEventRequest.getEventDate()).eventTime(addEventRequest.getEvenTime())
                .eventVenue(addresses.findAddressById(addEventRequest.getAddressId()).orElseThrow(()->new RuntimeException("know what you are doing"))).build();
                 return events.save(event);
    }
}
