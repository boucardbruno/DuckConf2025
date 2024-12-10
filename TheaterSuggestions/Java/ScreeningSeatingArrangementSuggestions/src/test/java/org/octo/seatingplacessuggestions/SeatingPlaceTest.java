package org.octo.seatingplacessuggestions;

import org.junit.jupiter.api.Test;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlaceAvailability;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeatingPlaceTest {
    @Test
    public void Be_a_Value_Type() {
        var firstInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var secondInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        // Two different instances with same values should be equals
        assertThat(secondInstance).isEqualTo(firstInstance);

        // Should not mutate existing instance
        SeatingPlace seatingPlaceAllocated = secondInstance.allocate();
        assertThat(secondInstance).isEqualTo(firstInstance);
        assertThat(seatingPlaceAllocated).isNotEqualTo(firstInstance);
    }
}
