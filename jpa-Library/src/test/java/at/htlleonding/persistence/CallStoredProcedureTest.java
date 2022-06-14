package at.htlleonding.persistence;

import at.htlleonding.persistence.MediaTypes.Book;
import at.htlleonding.persistence.MediaTypes.Magazine;
import at.htlleonding.persistence.People.Customer;
import at.htlleonding.persistence.People.Employee;
import at.htlleonding.persistence.People.Person;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTest
public class CallStoredProcedureTest {
    @Inject
    LibraryRepository target;

    Author[] authors = new Author[] {
            new Author("Franz", "Franzes", LocalDate.of(1980, 01, 01)),
            new Author("Johann", "Johnson", LocalDate.of(1971, 3, 20)),
            new Author("Fritz", "Franzes", LocalDate.of(1971, 3, 20))
    };

    Genre[] genres = new Genre[] {
            new Genre("Sachbuch"),
            new Genre("Biographie")
    };

    Topic[] topics = new Topic[] {
            new Topic("Thermodynamik"),
            new Topic("Optik"),
            new Topic("Gravitation"),
            new Topic("Leben"),
            new Topic("Glaube")
    };

    Media[] medias = new Media[] {
            new Magazine(),
            new Book()
    };

    Language[] languages = new Language[] {
            new Language("Deutsch"),
            new Language("Englisch")
    };

    Publisher[] publishers = new Publisher[] {
            new Publisher("Heinz Verlag"),
            new Publisher("Meier Verlag")
    };

    Publication[] publications = new Publication[] {
            new Publication("Die Welt der Teilchen", false),
            new Publication("Das Leben von Johann Johnson", false),
            new Publication("The Life of Johann Johnson", true)
    };

    Room[] rooms = new Room[] {
            new Room(4,1),
            new Room(3,2),
            new Room(5,2)
    };

    BookShelf[] bookShelves = new BookShelf[] {
            new BookShelf(),
            new BookShelf(),
            new BookShelf()
    };

    Specimen[] specimens = new Specimen[] {
            new Specimen(),
            new Specimen(),
            new Specimen(),
            new Specimen(),
            new Specimen(),
            new Specimen(),
            new Specimen(),
    };

    Location[] locations = new Location[] {
            new Location("Linz"),
            new Location("Leonding"),
            new Location("Enns")
    };

    Person[] persons = new Person[] {
            new Employee("Rick", "Richard", "rick@hotmail.com", "660", 1800),
            new Customer("Clara", "Charlotte", "clara@hotmail.com", "660", false),
            new Customer("Elisa", "Elisabeth", "elisa@hotmail.com", "660", false),
    };

    Reservation[] reservations = new Reservation[] {
            new Reservation(LocalDate.of(2022, 5, 5))
    };

    LendOut[] lendOuts = new LendOut[] {
            new LendOut(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1))
    };

    MediaType[] mediaTypes = new MediaType[] {
            new MediaType("Magazin", 10),
            new MediaType("Buch", 20)
    };

    Sale[] sales = new Sale[] {
            new Sale(LocalDate.of(2022, 3,24))
    };

    SalePosition[] salePositions = new SalePosition[] {
            new SalePosition(mediaTypes[0].getPrice()),
            new SalePosition(mediaTypes[0].getPrice())
    };

    AuditTrail[] auditTrails = new AuditTrail[] {
            new AuditTrail("dbUser01", LocalDate.of(2022,1,10), "Genre", Action.Insert,
            5, null, "Roman"),
            new AuditTrail("dbUser02", LocalDate.of(2022,1,20), "Language", Action.Update,
            2, "Englisch", "Englisch - GB"),
            new AuditTrail("dbUser03", LocalDate.of(2022,1,30), "Topic", Action.Delete,
            3, "Gravitation", null)
    };

    private void createAuthors() {
        target.add(authors);
    }

    private void createGenres() {
        target.add(genres);
    }

    private void createTopics() {
        target.add(topics);
    }

    private void createMedias() {
        createAuthors();
        createGenres();
        createTopics();
        target.add(medias);

        target.add(medias[0], authors[0]);
        target.add(medias[0], authors[2]);
        target.add(medias[0], genres[0]);
        target.add(medias[0], topics[0]);
        target.add(medias[0], topics[1]);
        target.add(medias[0], topics[2]);

        target.add(medias[1], authors[1]);
        target.add(medias[1], genres[1]);
        target.add(medias[1], topics[3]);
        target.add(medias[1], topics[4]);
    }

    private void createLanguages() {
        target.add(languages);
    }

    private void createPublishers() {
        target.add(publishers);
    }

    private void createPublications() {
        createMedias();
        createLanguages();
        createPublishers();
        target.add(publications);

        target.add(publications[0], medias[0]);
        target.add(publications[0], languages[0]);
        target.add(publications[0], publishers[0]);

        target.add(publications[1], medias[1]);
        target.add(publications[1], languages[0]);
        target.add(publications[1], publishers[0]);

        target.add(publications[2], medias[1]);
        target.add(publications[2], languages[1]);
        target.add(publications[2], publishers[1]);
    }

    private void createRooms() {
        target.add(rooms);
    }

    private void createBookShelves() {
        createRooms();
        target.add(bookShelves);

        target.add(rooms[0], bookShelves[0]);
        target.add(rooms[1], bookShelves[1]);
        target.add(rooms[2], bookShelves[2]);
    }

    private void createSpecimens() {
        createPublications();
        createBookShelves();
        target.add(specimens);

        target.add(specimens[0], publications[0]);
        target.add(specimens[1], publications[0]);
        target.add(specimens[2], publications[0]);
        target.add(specimens[0], bookShelves[0]);
        target.add(specimens[1], bookShelves[0]);
        target.add(specimens[2], bookShelves[0]);

        target.add(specimens[3], publications[1]);
        target.add(specimens[4], publications[1]);
        target.add(specimens[3], bookShelves[1]);
        target.add(specimens[4], bookShelves[1]);

        target.add(specimens[5], publications[2]);
        target.add(specimens[6], publications[2]);
        target.add(specimens[5], bookShelves[2]);
        target.add(specimens[6], bookShelves[2]);
    }

    private void createLocations() {
        target.add(locations);
    }

    private void createPersons() {
        createLocations();
        target.add(persons);

        target.add(persons[0], locations[0]);

        target.add(persons[1], locations[1]);
        target.add(persons[2], locations[2]);
    }

    private void createReservations() {
        createPublications();
        createPersons();
        target.add(reservations);

        target.add(reservations[0], (Customer) persons[1]);
        target.add(publications[2], reservations[0]);
    }

    private void createLendOuts() {
        createPersons();
        createSpecimens();

        target.add(lendOuts[0], (Customer) persons[1]);
        target.add(specimens[3], lendOuts[0]);
    }

    private void createMediaTypes() {
        target.add(mediaTypes);
    }

    private void createSales() {
        createPersons();
        target.add(sales);

        target.add(sales[0], (Employee) persons[0]);
        target.add(sales[0], (Customer) persons[2]);
    }

    private void createSalePositions() {
        createSpecimens();
        createSales();
        target.add(salePositions);

        target.add(specimens[0], salePositions[0]);
        target.add(specimens[4], salePositions[1]);
        target.add(sales[0], salePositions[0]);
        target.add(sales[0], salePositions[1]);
    }

    private void createAuditTrails() {
        target.add(auditTrails);
    }

    @Test
    @Transactional
    public void createMedias_callStoredProcedure_returnResults() {
        createMedias();

        target.flushAndClear();

        var spq = target.entityManager.createStoredProcedureQuery("countMediaByAuthor");
        spq.registerStoredProcedureParameter("authorLastName", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("mediaCount", Integer.class, ParameterMode.OUT);

        spq.setParameter("authorLastName", "Franzes");
        spq.execute();

        var result = spq.getOutputParameterValue("mediaCount");

        Assertions.assertNotNull(result);
    }
}
