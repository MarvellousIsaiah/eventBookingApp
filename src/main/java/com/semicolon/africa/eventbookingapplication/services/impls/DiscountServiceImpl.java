package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.dto.request.AddDiscountRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.TicketResponse;
import com.semicolon.africa.eventbookingapplication.models.Discount;
import com.semicolon.africa.eventbookingapplication.services.impls.DiscountService;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    @Override
    public List<TicketResponse> viewAllTicketFor(Long id) {
        return null;
    }

    @Override
    public Discount addDiscountToTicket(AddDiscountRequest addDiscountRequest) {
        return null;
    }
}
