using Value.Shared;

namespace SeatsSuggestions;

public class AuditoriumSeatingArrangement(Dictionary<string, Row> rows) : Value.ValueType<AuditoriumSeatingArrangement>
{
    public IReadOnlyDictionary<string, Row> Rows => rows;

    public SeatingOptionIsSuggested SuggestSeatingOptionFor(int partyRequested, PricingCategory pricingCategory)
    {
        foreach (var row in rows.Values)
        {
            var seatOptionsSuggested = row.SuggestSeatingOption(partyRequested, pricingCategory);

            if (seatOptionsSuggested.MatchExpectation()) return seatOptionsSuggested;
        }

        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return [new DictionaryByValue<string, Row>(Rows.ToDictionary())];
    }

    public AuditoriumSeatingArrangement Allocate(List<SeatingPlace> seatingPlacesSuggested)
    {
        var newRows = new Dictionary<string, Row>(Rows.ToDictionary());

        foreach (var seatingPlaceSuggested in seatingPlacesSuggested)
        {
            newRows[seatingPlaceSuggested.RowName] = newRows[seatingPlaceSuggested.RowName].Allocate(seatingPlaceSuggested);
        }

        return new AuditoriumSeatingArrangement(newRows);
    }
}