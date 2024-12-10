package org.octo.seatingplacessuggestions.externaldependencies;

import org.octo.seatingplacessuggestions.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.octo.seatingplacessuggestions.externaldependencies.reservationsprovider.ReservationsProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class ExternalDependenciesTest {

    @Test
    void should_allow_us_to_retrieve_reserved_seats_for_a_given_ShowId() throws IOException {
        var seatsRepository = new ReservationsProvider();
        var reservedSeatsDto = seatsRepository.getReservedSeats("1");
        assertEquals(19, reservedSeatsDto.reservedSeats().size(), "Expected 19 reserved seats");
    }

    @Test
    void should_allow_us_to_retrieve_AuditoriumLayout_for_a_given_ShowId() throws IOException {

        var eventRepository = new AuditoriumLayoutRepository();
        var theaterDto = eventRepository.findByShowId("2");

        // JUnit 5 Assertions
        assertEquals(6, theaterDto.rows().size(), "Expected 6 rows in the auditorium");
        assertEquals(2, theaterDto.corridors().size(), "Expected 2 corridors in the auditorium");
        var firstSeatOfFirstRow = theaterDto.rows().get("A").get(0);
        System.out.println(firstSeatOfFirstRow);
        assertEquals(2, firstSeatOfFirstRow.category(), "Expected category 2 for the first seat of the first row");
    }
}
