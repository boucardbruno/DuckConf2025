package org.octo.seatingplacessuggestions.externaldependencies.auditoriumlayoutrepository;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record AuditoriumDto(@JsonProperty("Rows") Map<String, List<SeatDto>> rows, @JsonProperty("Corridors") List<CorridorDto> corridors) {
}
