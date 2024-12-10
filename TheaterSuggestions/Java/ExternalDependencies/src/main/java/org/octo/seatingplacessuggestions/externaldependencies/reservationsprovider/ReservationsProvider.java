package org.octo.seatingplacessuggestions.externaldependencies.reservationsprovider;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReservationsProvider {

    private final Map<String, ReservedSeatsDto> repository = new HashMap<>();

    public ReservationsProvider() throws IOException {
        var jsonDirectory = Paths.get(System.getProperty("user.dir")).getParent().getParent().getParent().toString() + "/Stubs/AuditoriumLayouts";

        try (var directoryStream = Files.newDirectoryStream(Paths.get(jsonDirectory))) {

            for (Path path : directoryStream) {
                if (path.toString().contains("_booked_seats.json")) {
                    var fileName = path.getFileName().toString();
                    var mapper = new ObjectMapper();
                    repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), ReservedSeatsDto.class));
                }

            }
        }
    }

    public ReservedSeatsDto getReservedSeats(String showId) {
        if (repository.containsKey(showId)) {
            return repository.get(showId);
        }
        return new ReservedSeatsDto(Collections.emptyList());
    }
}