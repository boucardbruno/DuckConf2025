using System.Linq;
using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class AuditoriumSeatingArrangementShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var auditoriumLayoutAdapter =
            new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
        var showIdWithoutReservationYet = "18";
        var auditoriumSeatingFirstInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance 
        var seatingPlace = auditoriumSeatingSecondInstance.Rows.Values.First().SeatingPlaces.First().Allocate();
        Check.That(seatingPlace).IsInstanceOf<SeatingPlace>();
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);
    }    
}