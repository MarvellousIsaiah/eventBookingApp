package com.semicolon.africa.eventbookingapplication.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Organizers {
    @Id
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
