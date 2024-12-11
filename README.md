## Vivez l'expérience d'une modélisation orientée Domain Driven Design

Atelier pour la **Duck Conf 2024**

Nous débuterons par une présentation de notre contexte métier, pour en suite plonger dans deux ateliers de découvertes :

- Example Mapping sur l’agrégat de la partie cœur Domain. Puis nous basculerons sur le Responsabiliy Mapping nous rappellerons les principes du Model-Driven Design
- Supple Design Deep Model Fort de ces nouvelles conséquences, vous serez prêt à coder votre modèle métier en mode collaboratif.

L’atelier de modélisation est disponible en Java, C#.

Venez avec votre laptop et votre IDE préféré et les librairies de test associées

## Modèliser le code au plus proche du modèle métier

### Phase 1 - Deep Modeling Coding

Deep Modeling aims to capture domain complexity in close collaboration with domain experts, discovering deep business rules and accurately representing complex concepts.

#### Conseils pour pratiquer le Deep Modeling:

**Collaborer étroitement avec les experts métier** : Utilisez des sessions d'**Event Storming** ou de **modélisation collaborative** pour obtenir une compréhension approfondie des concepts métier. Identifiez les événements critiques, les règles, et les sous-domaines.

**Modéliser des règles métier complexes** : Exprimez les règles métier à travers des Value Objects, Domain Services ou Policy Objects. Par exemple, si une règle exige que les places soient réservées dans un ordre spécifique, créez un objet qui encapsule cette règle et ses validations.

**Prototyper les règles critiques** : Pour les règles importantes, telles que les arrangements de places, construisez des prototypes ou des tests pour valider leur comportement avant de les intégrer au code principal.

En appliquant ensemble le **Supple Design** et le **Deep Modeling**, vous pouvez créer un modèle de domaine à la fois clair et capable de capturer la complexité réelle du domaine métier. Ces étapes offrent une approche structurée pour gérer la complexité et garantir que le modèle est aligné avec les besoins et les règles du domaine.

### 1ère règle - The middle of the row

La règle visant à placer les spectateurs vers le milieu de la rangée a pour objectif d’offrir une meilleure expérience visuelle et de maximiser le confort en les installant aussi près que possible du centre. Cette règle vise à garantir un confort visuel optimal et une expérience globale améliorée pour les spectateurs en concentrant l’occupation des sièges autour du centre de la rangée.

Nous proposons de traiter cette règle via un prototype afin de rester aussi indépendant que possible du code existant. Le nouveau code sera intégré une fois qu’il aura été testé et qu’il sera opérationnel.

**Avant de mettre en œuvre la règle du placement au milieu de la rangée, assurez-vous d’avoir vu son illustration avec l’expert du domaine.**

Commencez par une rangée de taille 10, correspondant à un cas pair.

##### In RowTest.java

```java
@Test
public void offer_seats_from_the_middle_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one() {
    int partySize = 2;

    SeatingPlace a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a3 = new SeatingPlace("A", 3, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a4 = new SeatingPlace("A", 4, PricingCategory.FIRST, SeatingPlaceAvailability.RESERVED);
    SeatingPlace a5 = new SeatingPlace("A", 5, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a6 = new SeatingPlace("A", 6, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a7 = new SeatingPlace("A", 7, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a8 = new SeatingPlace("A", 8, PricingCategory.FIRST, SeatingPlaceAvailability.RESERVED);
    SeatingPlace a9 = new SeatingPlace("A", 9, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a10 = new SeatingPlace("A", 10, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

    Row row = new Row("A", new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)));
    List<SeatingPlace> seatingPlaces = offerSeatsNearerTheMiddleOfTheRow(row).stream().limit(partySize).collect(Collectors.toList());
    assertThat(seatingPlaces).containsExactly(a5, a6);
}

// Deep Modeling: probing the code should start with a prototype.
public List<SeatingPlace> offerSeatsNearerTheMiddleOfTheRow(Row row) {
    // TODO: Implement your logic here
    return new ArrayList<>();
}
```

##### Dans RowShould.cs

```c#
[Test]
public void Offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one()
{
    var partySize = 2;

    var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);
    var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Reserved);
    var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);

    var row = new Row("A", new List<SeatingPlace> { a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 });

    var seatingPlaces = OfferSeatsNearerTheMiddleOfTheRow(row).Take(partySize);

    Check.That(seatingPlaces)
        .ContainsExactly(a5, a6);
}

// Deep Modeling: probing the code should start with a prototype.
private IEnumerable<SeatingPlace> OfferSeatsNearerTheMiddleOfTheRow(Row row)
{
    // Deep Modeling: probing the code should start with a prototype.
    return new List<SeatingPlace>();
}
```

You should complete the functionality by testing the case of an odd-sized row.

## Phase 2 - Refactoring to deeper insights

### Règle 2 - The adjacent seats

Les places doivent être attribuées de manière à ce que chaque spectateur ou groupe de spectateurs soit assis côte à côte, sans siège inoccupé entre eux.

La règle des sièges adjacents dans le placement en salle optimise l’occupation en veillant à ce que les groupes soient regroupés sans laisser d’espace, réduisant ainsi les sièges isolés et favorisant une disposition vers le centre de chaque rangée pour améliorer l’expérience des spectateurs.

**Avant de mettre en œuvre la règle des sièges adjacents, assurez-vous d’avoir vu son illustration avec l’expert du domaine.**

##### dans RowTest.java

```java
@Test
public void Offer_adjacent_seats_nearer_the_middle_of_the_row_when_the_middle_is_not_reserved()
{
    int partySize = 3;

    SeatingPlace a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.RESERVED);
    SeatingPlace a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.RESERVED);
    SeatingPlace a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.AVAILABLE);
    SeatingPlace a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.AVAILABLE);

    Row row = new Row("A",  new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 )));

    List<SeatingPlace> seatingPlaces =
            new OfferingSeatsNearerMiddleOfTheRow(row).offerSeatsNearerTheMiddleOfTheRow(5);
    
    List<SeatingPlace> seatingPlaces = OfferAdjacentSeats(seatsWithDistance, partySize);

    assertThat(seatingPlaces).containsExactly(a5, a6, a7);
}

private List<Seat> OfferAdjacentSeats(List<SeatWithDistance> seatsWithDistance, int partySize) {
    // Implement your prototype here
    return new ArrayList<>();
}
```

##### dans RowShould.cs

```c#
[Test]
public void Offer_adjacent_seats_nearer_the_middle_of_the_row_when_the_middle_is_not_reserved()
{
    var partySize = 3;

    var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);

    var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);

    var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
    var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);

    var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Reserved);
    var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
    var a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);

    var row = new Row("A", new List<SeatingPlace> { a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 });



    var seatingPlaces =
        OfferingSeatsNearerMiddleOfTheRow.OfferSeatsNearerTheMiddleOfTheRow(row);

    var seats =
        OfferAdjacentSeats(seatingPlaces, partySize);

    Check.That(seats).ContainsExactly(a5, a6, a7);
}
// Deep Modeling: probing the code should start with a prototype.
public IEnumerable<SeatingPlace> OfferAdjacentSeats(IEnumerable<SeatingPlace> seatingPlaces, int partySize)
{
    // Deep Modeling: probing the code should start with a prototype.
    return new List<SeatingPlace>();
}
```

**Pour finaliser la fonctionnalité, vous devez la tester avec d'autres configurations de rangées.**

