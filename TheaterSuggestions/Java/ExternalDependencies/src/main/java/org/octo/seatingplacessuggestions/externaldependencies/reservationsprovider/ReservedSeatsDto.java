package org.octo.seatingplacessuggestions.externaldependencies.reservationsprovider;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ReservedSeatsDto(@JsonProperty("ReservedSeats") List<String> reservedSeats) {
}
