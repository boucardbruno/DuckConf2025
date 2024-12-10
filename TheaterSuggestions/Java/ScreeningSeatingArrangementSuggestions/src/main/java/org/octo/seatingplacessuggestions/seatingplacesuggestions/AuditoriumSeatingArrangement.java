package org.octo.seatingplacessuggestions.seatingplacesuggestions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record AuditoriumSeatingArrangement(Map<String, Row> rows) {

    public Map<String, Row> getRows() {
        return rows;
    }

    public SeatingOptionIsSuggested suggestSeatingOptionFor(int partyRequested, PricingCategory pricingCategory) {
        for (Row row : rows.values()) {
            var seatingOptionSuggested = row.suggestSeatingOption(partyRequested, pricingCategory);

            if (seatingOptionSuggested.matchExpectation()) {
                return seatingOptionSuggested;
            }
        }
        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    public AuditoriumSeatingArrangement allocate(List<SeatingPlace> seatingPlaces) {
        Map<String, Row> newRows = new HashMap<>(rows);
        seatingPlaces.forEach(seatingPlace -> {
            Row formerRow = newRows.get(seatingPlace.rowName());
            var newRow = formerRow.allocate(seatingPlace);
            newRows.put(newRow.name(), newRow);
        });
        return new AuditoriumSeatingArrangement(newRows);
    }
}
