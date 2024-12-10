namespace SeatsSuggestions;

public class SeatingPlace(
    string rowName,
    int number,
    PricingCategory pricingCategory,
    SeatingPlaceAvailability seatingPlaceAvailability) : Value.ValueType<SeatingPlace> 
{
    public string RowName { get; } = rowName;
    public int Number { get; } = number;
    public PricingCategory PricingCategory { get; } = pricingCategory;
    private SeatingPlaceAvailability SeatingPlaceAvailability { get; set; } = seatingPlaceAvailability;

    public bool IsAvailable()
    {
        return SeatingPlaceAvailability == SeatingPlaceAvailability.Available;
    }

    public override string ToString()
    {
        return $"{RowName}{Number}";
    }

    public bool MatchCategory(PricingCategory pricingCategory)
    {
        if (pricingCategory == PricingCategory.Ignored) return true;
        
        return PricingCategory == pricingCategory;
    }

    public SeatingPlace Allocate()
    {
        if (SeatingPlaceAvailability == SeatingPlaceAvailability.Available)
        {
            return new SeatingPlace(RowName, Number, PricingCategory, SeatingPlaceAvailability.Allocated);
        }
        return this;
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return [RowName, Number, PricingCategory, SeatingPlaceAvailability];
    }

    public bool IsSameLocation(SeatingPlace seatingPlace)
    {
        return this.Number == seatingPlace.Number && this.RowName == seatingPlace.RowName;
    }
}