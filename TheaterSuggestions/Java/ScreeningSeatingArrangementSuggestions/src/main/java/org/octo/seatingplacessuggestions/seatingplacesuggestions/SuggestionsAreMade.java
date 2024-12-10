package org.octo.seatingplacessuggestions.seatingplacesuggestions;

import java.util.*;

public class SuggestionsAreMade {
    private final String showId;
    private final int partyRequested;

    private final Map<PricingCategory, List<SuggestionIsMade>> forCategory = new HashMap<>();

    public SuggestionsAreMade(String showId, int partyRequested) {
        this.showId = showId;
        this.partyRequested = partyRequested;

        instantiateAnEmptyListForEveryPricingCategory();
    }

    public List<String> seatNames(PricingCategory pricingCategory) {
        return forCategory.get(pricingCategory).stream().map(SuggestionIsMade::seatNames).flatMap(Collection::stream).toList();
    }

    private void instantiateAnEmptyListForEveryPricingCategory() {
        for (PricingCategory pricingCategory : PricingCategory.values()) {
            forCategory.put(pricingCategory, new ArrayList<>());
        }
    }

    public void add(Iterable<SuggestionIsMade> suggestions) {
        suggestions.forEach(suggestionIsMade -> forCategory.get(suggestionIsMade.pricingCategory()).add(suggestionIsMade));
    }

    public boolean matchExpectations() {
        return forCategory.values().stream().flatMap(List::stream).anyMatch(SuggestionIsMade::matchExpectation);
    }

    public String showId() {
        return showId;
    }

    public int partyRequested() {
        return partyRequested;
    }
}
