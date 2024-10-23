package com.semicolon.africa.eventbookingapplication.services;

import com.semicolon.africa.eventbookingapplication.repository.Events;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class EventslTest {
    @Autowired
    private Events events;

    @Test
    @Sql(scripts = {"/db/data.organizer.sql"})
    @Sql(scripts = {"/db/data.address.sql"})
    @Sql(scripts = {"/db/data.Event.sql"})
    void getEventByOrganizerId(){
        var event = events.getEventsByOrganizerId(100L);
        assertThat(event).hasSize(2);

    }


}