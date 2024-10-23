package com.semicolon.africa.eventbookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Guests extends JpaRepository<Guests,Long> {
 @Query("SELECT guest from Guest guest where guest.ticket.event.id =: EventId")
 List<Guests> getGuestByEventId(Long eventId);
}
