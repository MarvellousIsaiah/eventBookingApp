package com.semicolon.africa.eventbookingapplication.services.impls;

import com.semicolon.africa.eventbookingapplication.dto.request.AddDiscountRequest;
import com.semicolon.africa.eventbookingapplication.dto.response.TicketResponse;
import com.semicolon.africa.eventbookingapplication.models.Discount;

import java.util.List;

public interface DiscountService {
    List<TicketResponse> viewAllTicketFor(Long id);
    Discount addDiscountToTicket(AddDiscountRequest addDiscountRequest);
}
