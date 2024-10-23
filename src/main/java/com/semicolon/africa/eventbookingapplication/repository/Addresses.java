package com.semicolon.africa.eventbookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Address extends JpaRepository<Address,Long> {
    Optional<Address> findAddressById(Long addressId);
}
