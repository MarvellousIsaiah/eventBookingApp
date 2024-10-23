package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.repository.Guests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class GuestsTest {
    @Autowired
    private Guests guests;
   @Test
    @Sql(scripts = {"/db/data.organizer.sql"})
    @Sql(scripts ={"/db/data.address.sql"})
    @Sql(scripts ={"/db/data.Event.sql"})
    @Sql(scripts = {"/db/data.ticket.sql"})
    @Sql(scripts ={"/db/data.Guest.sql"})
    void getGuestTicketId2(){
       var guestList = guests.getGuestByEventId(1L);
               assertThat(guestList).hasSize(2);
    }
}