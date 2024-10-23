package com.semicolon.africa.eventbookingapplication.services.impls;

import com.semicolon.africa.eventbookingapplication.dto.request.AddEventRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.PurchaseTicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.request.ReservedTicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.EventResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.PurchaseTicketResponse;
import com.semicolon.africa.eventbookingapplication.dto.response.TicketResponse;
import com.semicolon.africa.eventbookingapplication.models.Event;

import java.util.List;

public interface EventService {
    TicketResponse reserveTicket(ReservedTicketRequest reservedTicketRequest);
    PurchaseTicketResponse purchaseTicket(PurchaseTicketRequest purchaseTicketRequest);
    List<EventResponse> viewAllEventsFor(Long organizerId);
    Event addEvent(AddEventRequest addEventRequest);

    }

