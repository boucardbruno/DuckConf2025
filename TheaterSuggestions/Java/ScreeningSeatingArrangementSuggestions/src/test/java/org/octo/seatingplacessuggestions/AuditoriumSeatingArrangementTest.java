package org.octo.seatingplacessuggestions;

import org.junit.jupiter.api.Test;
import org.octo.seatingplacessuggestions.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.octo.seatingplacessuggestions.externaldependencies.reservationsprovider.ReservationsProvider;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.AuditoriumSeatingArrangements;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;


import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuditoriumSeatingArrangementTest {

    @Test
    public void be_a_Value_Type() throws IOException {
        var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());

        String showIdWithoutReservationYet = "18";
        var auditoriumSeatingFirstInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance
        SeatingPlace seatAllocated   = auditoriumSeatingSecondInstance.getRows().values().iterator()
                .next().seatingPlaces().iterator().next().allocate();
        assertThat(seatAllocated).isInstanceOf(SeatingPlace.class);
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);
    }
}
