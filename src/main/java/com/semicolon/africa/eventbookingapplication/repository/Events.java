package com.semicolon.africa.eventbookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Events extends JpaRepository<Events,Long> {

    @Query("SELECT events from Events events where events.organizer.id=:organizerId")
    List<Events> getEventsByOrganizerId(Long organizerId);
}

