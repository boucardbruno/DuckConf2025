namespace SeatsSuggestions;

/// <summary>
///     Occurs when a Suggestion is made.
/// </summary>
public class SuggestionIsMade(SeatingOptionIsSuggested seatingOptionIsSuggested)
{
    private readonly List<SeatingPlace> _suggestedSeats = seatingOptionIsSuggested.Seats;

    public int PartyRequested { get; } = seatingOptionIsSuggested.PartyRequested;
    public PricingCategory PricingCategory { get; } = seatingOptionIsSuggested.PricingCategory;

    public IReadOnlyList<SeatingPlace> SuggestedSeats => _suggestedSeats;

    public IEnumerable<string> SeatNames()
    {
        return _suggestedSeats.Select(s => s.ToString());
    }

    public bool MatchExpectation()
    {
        return _suggestedSeats.Count == PartyRequested;
    }
}