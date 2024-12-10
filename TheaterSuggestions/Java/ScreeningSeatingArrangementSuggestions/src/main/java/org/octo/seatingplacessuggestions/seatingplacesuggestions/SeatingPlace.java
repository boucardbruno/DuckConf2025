package org.octo.seatingplacessuggestions.seatingplacesuggestions;

public record SeatingPlace(String rowName, int number, PricingCategory pricingCategory, SeatingPlaceAvailability seatingPlaceAvailability) {

    public boolean isAvailable() {
        return seatingPlaceAvailability == SeatingPlaceAvailability.AVAILABLE;
    }

    public boolean matchCategory(PricingCategory pricingCategory) {
        if (pricingCategory == PricingCategory.IGNORED) return true;
        return this.pricingCategory == pricingCategory;
    }

    public SeatingPlace allocate() {
        if (seatingPlaceAvailability == SeatingPlaceAvailability.AVAILABLE)
            return new SeatingPlace(rowName, number, pricingCategory, SeatingPlaceAvailability.ALLOCATED);
        return this;
    }

    @Override
    public String toString() {
        return rowName + number;
    }

    boolean isSameLocation(SeatingPlace seatingPlace) {
        return seatingPlace.rowName().equals(rowName()) && seatingPlace.number() == number();
    }
}
