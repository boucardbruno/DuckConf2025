namespace SeatsSuggestions;

/// <summary>
///     Occurs when a Suggestion that does not meet expectation is made.
/// </summary>
public class SuggestionsAreNotAvailable(string showId, int partyRequested) : SuggestionsAreMade(showId, partyRequested);