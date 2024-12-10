package org.octo.seatingplacessuggestions;

import org.junit.jupiter.api.Test;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.Row;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlaceAvailability;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RowTest {
    @Test
    public void be_a_Value_Type() {
        var a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        // Two different instances with same values should be equals
        Row rowFirstInstance = new Row("A", Arrays.asList(a1, a2));
        Row rowSecondInstance = new Row("A", Arrays.asList(a1, a2));
        assertThat(rowSecondInstance).isEqualTo(rowFirstInstance);

        // Should not mutate existing instance
        var a3 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        Row newRowWithNewSeatAdded = rowSecondInstance.addSeatingPlace(a3);
        assertThat(newRowWithNewSeatAdded).isNotEqualTo(rowFirstInstance);
    }
}