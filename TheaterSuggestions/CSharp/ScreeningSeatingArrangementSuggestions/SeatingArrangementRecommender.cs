namespace SeatsSuggestions;

public class SeatingArrangementRecommender(AuditoriumSeatingArrangements auditoriumSeatingArrangements)
{
    private const int NumberOfSuggestions = 3;

    public SuggestionsAreMade MakeSuggestions(string showId, int partyRequested)
    {
        var auditoriumSeating = auditoriumSeatingArrangements.FindByShowId(showId);

        var suggestionsMade = new SuggestionsAreMade(showId, partyRequested);

        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.First));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Second));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Third));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Ignored));

        if (suggestionsMade.MatchExpectations()) return suggestionsMade;

        return new SuggestionsAreNotAvailable(showId, partyRequested);
    }

    private static IEnumerable<SuggestionIsMade> GiveMeSuggestionsFor(AuditoriumSeatingArrangement auditoriumSeatingArrangement,
        int partyRequested,
        PricingCategory pricingCategory)
    {
        var foundedSuggestions = new List<SuggestionIsMade>();

        for (var i = 0; i < NumberOfSuggestions; i++)
        {
            var seatingOptionSuggested = auditoriumSeatingArrangement.SuggestSeatingOptionFor(partyRequested, pricingCategory);

            if (seatingOptionSuggested.MatchExpectation())
            {
                auditoriumSeatingArrangement = auditoriumSeatingArrangement.Allocate(seatingOptionSuggested.Seats);

                foundedSuggestions.Add(new SuggestionIsMade(seatingOptionSuggested));
            }
        }

        return foundedSuggestions;
    }
}