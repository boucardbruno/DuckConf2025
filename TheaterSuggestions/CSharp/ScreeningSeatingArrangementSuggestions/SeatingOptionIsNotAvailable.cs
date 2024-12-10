namespace SeatsSuggestions;

internal class SeatingOptionIsNotAvailable(int partyRequested, PricingCategory pricingCategory)
    : SeatingOptionIsSuggested(partyRequested,
        pricingCategory);