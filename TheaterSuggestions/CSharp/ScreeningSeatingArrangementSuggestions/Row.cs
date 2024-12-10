using Value;

namespace SeatsSuggestions;

public class Row(string name, List<SeatingPlace> seats) : ValueType<Row>
{
    public string Name { get; } = name;
    public List<SeatingPlace> SeatingPlaces { get; } = seats;

    public SeatingOptionIsSuggested SuggestSeatingOption(int partyRequested, PricingCategory pricingCategory)
    { 
        var seatAllocation = new SeatingOptionIsSuggested(partyRequested, pricingCategory);
        
        foreach (var seat in SeatingPlaces)
        {
            if (seat.IsAvailable() && seat.MatchCategory(pricingCategory))
            {
                seatAllocation.AddSeat(seat);
                if (seatAllocation.MatchExpectation()) return seatAllocation;
            }
        }
        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    public Row AddSeatingPlace(SeatingPlace seatingPlace)
    {
        return new Row(Name, [..SeatingPlaces, seatingPlace]);
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return [Name, new ListByValue<SeatingPlace>(SeatingPlaces)];
    }

    public Row Allocate(SeatingPlace seatingPlacesSuggested)
    {
        var seatingPlaces = new List<SeatingPlace>();
        foreach (var seatingPlace in SeatingPlaces)
        {
            if (seatingPlacesSuggested.IsSameLocation(seatingPlace))
            {
                seatingPlaces.Add(seatingPlacesSuggested.Allocate());
            }
            else
            {
                seatingPlaces.Add(seatingPlace);
            }
        }
        return new Row(Name, seatingPlaces);
    }
}