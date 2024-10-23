package com.semicolon.africa.eventbookingapplication.repository;

import com.semicolon.africa.eventbookingapplication.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Organizers extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findOrganizerByEmailIgnoreCase(String email);

    Optional<Organizer> findOrganizerById(Long id);
}

