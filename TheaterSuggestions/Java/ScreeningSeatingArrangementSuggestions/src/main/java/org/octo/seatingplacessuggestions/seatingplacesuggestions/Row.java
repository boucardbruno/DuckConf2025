package org.octo.seatingplacessuggestions.seatingplacesuggestions;

import java.util.ArrayList;
import java.util.List;

public record Row(String name, List<SeatingPlace> seatingPlaces) {

    public SeatingOptionIsSuggested suggestSeatingOption(int partyRequested, PricingCategory pricingCategory) {

        var seatAllocation = new SeatingOptionIsSuggested(partyRequested, pricingCategory);

        for (var seat : seatingPlaces) {
            if (seat.isAvailable() && seat.matchCategory(pricingCategory)) {
                seatAllocation.addSeat(seat);

                if (seatAllocation.matchExpectation())
                    return seatAllocation;

            }
        }
        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    public Row addSeatingPlace(SeatingPlace seatingPlace) {
        List<SeatingPlace> newSeatingPlaces = new ArrayList<>( seatingPlaces );
        newSeatingPlaces.add(seatingPlace);
        return new Row(name, newSeatingPlaces);
    }

    public Row allocate(SeatingPlace seatingPlaceSuggested) {
        ArrayList<SeatingPlace> newSeatingPlaces = new ArrayList<>();
        seatingPlaces.forEach(seatingPlace -> {
            if (seatingPlaceSuggested.isSameLocation(seatingPlace))
                newSeatingPlaces.add(seatingPlaceSuggested.allocate());
            else
                newSeatingPlaces.add(seatingPlace);
        });
        return new Row(name, newSeatingPlaces);
    }

}
