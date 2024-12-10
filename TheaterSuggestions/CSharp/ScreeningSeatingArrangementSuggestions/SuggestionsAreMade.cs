namespace SeatsSuggestions;

/// <summary>
///     Occurs when a bunch of Suggestion are made.
/// </summary>
public class SuggestionsAreMade
{
    public SuggestionsAreMade(string showId, int partyRequested)
    {
        ShowId = showId;
        PartyRequested = partyRequested;

        InstantiateAnEmptyListForEveryPricingCategory();
    }

    public string ShowId { get; }
    public int PartyRequested { get; }

    private Dictionary<PricingCategory, List<SuggestionIsMade>> ForCategory { get; } = new();

    public IEnumerable<string> SeatNames(PricingCategory pricingCategory)
    {
        return ForCategory[pricingCategory].SelectMany(s => s.SeatNames());
    }

    private void InstantiateAnEmptyListForEveryPricingCategory()
    {
        foreach (PricingCategory pricingCategory in Enum.GetValues(typeof(PricingCategory)))
            ForCategory[pricingCategory] = new List<SuggestionIsMade>();
    }

    public void Add(IEnumerable<SuggestionIsMade> suggestions)
    {
        foreach (var suggestionMade in suggestions) ForCategory[suggestionMade.PricingCategory].Add(suggestionMade);
    }

    public bool MatchExpectations()
    {
        return ForCategory.SelectMany(s => s.Value).Any(x => x.MatchExpectation());
    }
}