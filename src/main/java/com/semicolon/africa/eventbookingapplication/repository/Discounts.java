package com.semicolon.africa.eventbookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Discount extends JpaRepository<Discount,Long> {
    Optional<Discount> findDiscountById(Long discountId);
}
