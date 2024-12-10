package org.octo.seatingplacessuggestions.externaldependencies.auditoriumlayoutrepository;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeatDto(@JsonProperty("Name") String name, @JsonProperty("Category") int category) {
}
