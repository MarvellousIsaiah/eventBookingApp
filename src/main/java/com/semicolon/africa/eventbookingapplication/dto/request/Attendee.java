package com.semicolon.africa.eventbookingapplication.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
@RequiredArgsConstructor
public class AttendeeRequest {
    private UUID id;
    private String email;
    private String phoneNumber;
    private Long ticketId;
}
