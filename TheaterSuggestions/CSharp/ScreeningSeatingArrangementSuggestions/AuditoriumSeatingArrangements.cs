using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;

namespace SeatsSuggestions;

public class AuditoriumSeatingArrangements(
    AuditoriumLayoutRepository auditoriumLayoutRepository,
    ReservationsProvider reservationsProvider)
{
    public AuditoriumSeatingArrangement FindByShowId(string showId)
    {
        return Adapt(auditoriumLayoutRepository.GetAuditoriumLayoutFor(showId),
            reservationsProvider.GetReservedSeats(showId));
    }

    private static AuditoriumSeatingArrangement Adapt(AuditoriumDto auditoriumDto, ReservedSeatsDto reservedSeatsDto)
    {
        var rows = new Dictionary<string, Row>();

        foreach (var rowDto in auditoriumDto.Rows)
        foreach (var seatDto in rowDto.Value)
        {
            var rowName = ExtractRowName(seatDto.Name);
            var number = ExtractNumber(seatDto.Name);
            var pricingCategory = ConvertCategory(seatDto.Category);

            var isReserved = reservedSeatsDto.ReservedSeats.Contains(seatDto.Name);

            if (!rows.ContainsKey(rowName)) rows[rowName] = new Row(rowName, new List<SeatingPlace>());

            rows[rowName].SeatingPlaces.Add(new SeatingPlace(rowName, number, pricingCategory,
                isReserved ? SeatingPlaceAvailability.Reserved : SeatingPlaceAvailability.Available));
        }

        return new AuditoriumSeatingArrangement(rows);
    }

    private static PricingCategory ConvertCategory(int seatDtoCategory)
    {
        return (PricingCategory)seatDtoCategory;
    }

    private static int ExtractNumber(string name)
    {
        return int.Parse(name.Substring(1));
    }

    private static string ExtractRowName(string name)
    {
        return name[0].ToString();
    }
}