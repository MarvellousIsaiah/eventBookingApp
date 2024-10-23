package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.dto.request.TicketRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.ReservedTicketResponse;
import com.semicolon.africa.eventbookingapplication.models.Ticket;
import com.semicolon.africa.eventbookingapplication.repository.Tickets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.time.LocalTime.now;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final Tickets tickets;
    @Override
    public List<Ticket> viewAllTicketFor(Long eventId) {
        var listOfTicket = tickets.findTicketByEventId(eventId);
        if(listOfTicket.isEmpty())throw new RuntimeException("no tickets found for event");

        return listOfTicket;
    }

    @Override
    public ReservedTicketResponse reserveTicket(UUID ticketId) {
        var ticket = tickets.findTicketById((ticketId).orElseThrow(()-> new RuntimeException("could not find ticket")));
        if(!now().plusDays(4L).isBefore(ticket.getEventDate().atTime(ticket.getEvent().getEventTime())))throw new IllegalArgumentException("you no go get fit reserve ticket boss time done go");
        return ReservedTicketResponse.builder().ticketId(tickets.save(ticket).getId()).message("successfully purchased").build();


    }

    @Override
    public Ticket createTicket(TicketRequest ticketRequest) {
        Ticket ticket = Ticket.builder()
                .ticketType(ticketRequest.getTicketType())
                .price(ticketRequest.getPrice())
                .build();
        return tickets.save(ticket);

    }
}
