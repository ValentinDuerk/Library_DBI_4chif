package at.htlleonding.persistence;

import at.htlleonding.persistence.MediaTypes.Book;
import at.htlleonding.persistence.MediaTypes.Magazine;
import at.htlleonding.persistence.People.Customer;
import at.htlleonding.persistence.People.Employee;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;

@QuarkusTest
public class LibraryRepositoryTest {
    @Inject
    LibraryRepository target;

    private void createSampleData() {
        var author1 = new Author("Franz", "Franzes", LocalDate.of(1980, 01, 01));
        var author2 = new Author("Johann", "Johnson", LocalDate.of(1971, 3, 20));
        var author3 = new Author("Fritz", "Franzes", LocalDate.of(1971, 3, 20));
        target.add(author1);
        target.add(author2);
        target.add(author3);

        var genre1 = new Genre("Sachbuch");
        var genre2 = new Genre("Biographie");
        target.add(genre1);
        target.add(genre2);

        var topic1 = new Topic("Thermodynamik");
        var topic2 = new Topic("Optik");
        var topic3 = new Topic("Gravitation");
        var topic4 = new Topic("Leben");
        var topic5 = new Topic("Glaube");
        target.add(topic1);
        target.add(topic2);
        target.add(topic3);
        target.add(topic4);
        target.add(topic5);

        var magazine = new Magazine();
        target.add(magazine, author1);
        target.add(magazine, author2);
        target.add(magazine, genre1);
        target.add(magazine, topic1);
        target.add(magazine, topic2);
        target.add(magazine, topic3);

        var book = new Book();
        target.add(book, author2);
        target.add(book, genre2);
        target.add(book, topic4);
        target.add(book, topic5);

        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        target.add(language1);
        target.add(language2);

        var publisher1 = new Publisher("Heinz Verlag");
        var publisher2 = new Publisher("Meier Verlag");
        target.add(publisher1);
        target.add(publisher2);

        var publication1 = new Publication("Die Welt der Teilchen", false);
        var publication2 = new Publication("Das Leben von Johann Johnson", false);
        var publication3 = new Publication("The Life of Johann Johnson", true);

        target.add(publication1, magazine);
        target.add(publication1, language1);
        target.add(publication1, publisher1);

        target.add(publication2, book);
        target.add(publication2, language1);
        target.add(publication2, publisher1);

        target.add(publication3, book);
        target.add(publication3, language2);
        target.add(publication3, publisher2);


        var room1 = new Room(4,1);
        var room2 = new Room(3,2);
        var room3 = new Room(5,2);

        var bookShelf1 = new BookShelf();
        var bookShelf2 = new BookShelf();
        var bookShelf3 = new BookShelf();

        target.add(room1, bookShelf1);
        target.add(room2, bookShelf2);
        target.add(room3, bookShelf3);

        var specimen1 = new Specimen();
        var specimen2 = new Specimen();
        var specimen3 = new Specimen();
        var specimen4 = new Specimen();
        var specimen5 = new Specimen();
        var specimen6 = new Specimen();
        var specimen7 = new Specimen();

        target.add(specimen1, publication1);
        target.add(specimen2, publication1);
        target.add(specimen3, publication1);
        target.add(specimen1, bookShelf1);
        target.add(specimen2, bookShelf1);
        target.add(specimen3, bookShelf1);

        target.add(specimen4, publication2);
        target.add(specimen5, publication2);
        target.add(specimen4, bookShelf2);
        target.add(specimen5, bookShelf2);

        target.add(specimen6, publication3);
        target.add(specimen7, publication3);
        target.add(specimen6, bookShelf3);
        target.add(specimen7, bookShelf3);


        var location1 = new Location("Linz");
        var location2 = new Location("Leonding");
        var location3 = new Location("Enns");
        target.add(location1);
        target.add(location2);
        target.add(location3);

        var employee1 = new Employee("Rick", "Richard", "rick@hotmail.com", "660", 1800);
        target.add(employee1, location1);

        var customer1 = new Customer("Clara", "Charlotte", "clara@hotmail.com", "660", false);
        var customer2 = new Customer("Elisa", "Elisabeth", "elisa@hotmail.com", "660", false);

        target.add(customer1, location2);
        target.add(customer2, location3);

        var reservation1 = new Reservation(LocalDate.of(2022, 5, 5));
        target.add(reservation1, customer1);
        target.add(publication3, reservation1);

        var lendOut1 = new LendOut(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        target.add(lendOut1, customer1);
        target.add(specimen4, lendOut1);

        var mediaType1 = new MediaType("Magazin", 10);
        var mediaType2 = new MediaType("Buch", 20);
        target.add(mediaType1);
        target.add(mediaType2);

        var sale1 = new Sale(LocalDate.of(2022, 3,24));
        target.add(sale1, employee1);
        target.add(sale1, customer1);

        var salePosition1 = new SalePosition(mediaType1.getPrice());
        var salePosition2 = new SalePosition(mediaType2.getPrice());
        target.add(specimen1, salePosition1);
        target.add(specimen5, salePosition2);
        target.add(sale1, salePosition1);
        target.add(sale1, salePosition2);

        var auditTrail1 = new AuditTrail("dbUser01", LocalDate.of(2022,1,10), "Genre", Action.Insert,
                5, null, "Roman");
        var auditTrail2 = new AuditTrail("dbUser02", LocalDate.of(2022,1,20), "Language", Action.Update,
                2, "Englisch", "Englisch - GB");
        var auditTrail3 = new AuditTrail("dbUser03", LocalDate.of(2022,1,30), "Topic", Action.Delete,
                3, "Gravitation", null);
        target.add(auditTrail1);
        target.add(auditTrail2);
        target.add(auditTrail3);
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllAuthors_getThreeAuthors() {
        createSampleData();

        target.flushAndClear();

        var authors = this.target.getAllAuthors();
        Assertions.assertNotNull(authors);
        Assertions.assertEquals(3, authors.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getAuthorByFirstNameAndLastName_getFranzFranzes() {
        createSampleData();

        target.flushAndClear();

        var author = this.target.getAuthorByFirstNameAndLastName("Franz", "Franzes");
        Assertions.assertNotNull(author);
        Assertions.assertEquals("Franz", author.getFirstName());
        Assertions.assertEquals("Franzes", author.getLastName());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllGenres_getTwoGenres() {
        createSampleData();

        target.flushAndClear();

        var genres = this.target.getAllGenres();
        Assertions.assertNotNull(genres);
        Assertions.assertEquals(2, genres.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getGenre_getSachbuch() {
        createSampleData();

        target.flushAndClear();

        var genre = this.target.getGenre("Sachbuch");
        Assertions.assertNotNull(genre);
        Assertions.assertEquals("Sachbuch", genre.getKeyword());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllTopics_getFiveTopics() {
        createSampleData();

        target.flushAndClear();

        var topics = this.target.getAllTopics();
        Assertions.assertNotNull(topics);
        Assertions.assertEquals(5, topics.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getTopic_getOptik() {
        createSampleData();

        target.flushAndClear();

        var topic = this.target.getTopic("Optik");
        Assertions.assertNotNull(topic);
        Assertions.assertEquals("Optik", topic.getKeyword());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllMedia_getTwoMedia() {
        createSampleData();

        target.flushAndClear();

        var media = this.target.getAllMedia();
        Assertions.assertNotNull(media);
        Assertions.assertEquals(2, media.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getMediaByAuthorAndGenre_getMediaFromFranzesWithGenreSachbuch() {
        createSampleData();

        target.flushAndClear();

        var media = this.target.getMediaByAuthorAndGenre("Franzes", "Sachbuch");
        Assertions.assertNotNull(media);
        Assertions.assertEquals(1, media.size());
    }


    @TestTransaction
    @Test
    public void createSampleData_getAllLanguages_getTwoLanguages() {
        createSampleData();

        target.flushAndClear();

        var languages = this.target.getAllLanguages();
        Assertions.assertNotNull(languages);
        Assertions.assertEquals(2, languages.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getLanguage_getEnglisch() {
        createSampleData();

        target.flushAndClear();

        var language = this.target.getLanguage("Englisch");
        Assertions.assertNotNull(language);
        Assertions.assertEquals("Englisch", language.getKeyword());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllPublisher_getTwoPublisher() {
        createSampleData();

        target.flushAndClear();

        var publisher = this.target.getAllPublisher();
        Assertions.assertNotNull(publisher);
        Assertions.assertEquals(2, publisher.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getPublisher_getHeinzVerlag() {
        createSampleData();

        target.flushAndClear();

        var publisher = this.target.getPublisher("Heinz Verlag");
        Assertions.assertNotNull(publisher);
        Assertions.assertEquals("Heinz Verlag", publisher.getName());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllPublications_getThreePublications() {
        createSampleData();

        target.flushAndClear();

        var publications = this.target.getAllPublications();
        Assertions.assertNotNull(publications);
        Assertions.assertEquals(3, publications.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getPublication_getDieWeltDerTeilchen() {
        createSampleData();

        target.flushAndClear();

        var publication = this.target.getPublication("Die Welt der Teilchen", "Deutsch");
        Assertions.assertNotNull(publication);
        Assertions.assertEquals("Die Welt der Teilchen", publication.getTitle());
        Assertions.assertEquals(false, publication.isTranslation());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllRooms_getThreeRooms() {
        createSampleData();

        target.flushAndClear();

        var rooms = this.target.getAllRooms();
        Assertions.assertNotNull(rooms);
        Assertions.assertEquals(3, rooms.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getRoom_getRoomNumberThreeOnFloorTwo() {
        createSampleData();

        target.flushAndClear();

        var room = this.target.getRoom(3,2);
        Assertions.assertNotNull(room);
        Assertions.assertEquals(3, room.getNumber());
        Assertions.assertEquals(2, room.getFloor());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllBookShelves_getThreeBookShelves() {
        createSampleData();

        target.flushAndClear();

        var bookShelves = this.target.getAllBookShelves();
        Assertions.assertNotNull(bookShelves);
        Assertions.assertEquals(3, bookShelves.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getBookShelf_getBookShelfFromRoomFiveOnFloorTwo() {
        createSampleData();

        target.flushAndClear();

        var room = this.target.getRoom(5,2);
        Assertions.assertNotNull(room);
        Assertions.assertEquals(5, room.getNumber());
        Assertions.assertEquals(2, room.getFloor());

        var bookShelves = this.target.getBookShelvesFromRoom(room.getId());
        Assertions.assertNotNull(bookShelves);
        Assertions.assertEquals(1, bookShelves.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllSpecimen_getSevenSpecimen() {
        createSampleData();

        target.flushAndClear();

        var specimen = this.target.getAllSpecimen();
        Assertions.assertNotNull(specimen);
        Assertions.assertEquals(7, specimen.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getSpecimenByPublication_getRoomNumberThreeOnFloorTwo() {
        createSampleData();

        target.flushAndClear();

        var publication = this.target.getPublication("Das Leben von Johann Johnson", "Deutsch");
        Assertions.assertNotNull(publication);
        Assertions.assertEquals("Das Leben von Johann Johnson", publication.getTitle());
        Assertions.assertEquals(false, publication.isTranslation());

        var specimens = this.target.getSpecimensByPublication(publication.getId());
        Assertions.assertNotNull(specimens);
        Assertions.assertEquals(2, specimens.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllLocations_getThreeLocations() {
        createSampleData();

        target.flushAndClear();

        var locations = this.target.getAllLocations();
        Assertions.assertNotNull(locations);
        Assertions.assertEquals(3, locations.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getLocationByName_getLinz() {
        createSampleData();

        target.flushAndClear();

        var location = this.target.getLocationByName("Linz");
        Assertions.assertNotNull(location);
        Assertions.assertEquals("Linz", location.getName());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllEmployees_getOneEmployee() {
        createSampleData();

        target.flushAndClear();

        var employees = this.target.getAllEmployees();
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(1, employees.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getEmployeeByFirstNameAndLastName_getRickRichard() {
        createSampleData();

        target.flushAndClear();

        var employee = this.target.getEmployeeByFirstNameAndLastName("Rick", "Richard");
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Rick", employee.getFirstName());
        Assertions.assertEquals("Richard", employee.getLastName());
        Assertions.assertEquals("rick@hotmail.com", employee.getEmail());
        Assertions.assertEquals("660", employee.getTelNumber());
        Assertions.assertEquals(1800, employee.getSalary());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllCustomers_getTwoCustomers() {
        createSampleData();

        target.flushAndClear();

        var customers = this.target.getAllCustomer();
        Assertions.assertNotNull(customers);
        Assertions.assertEquals(2, customers.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getCustomerByFirstNameAndLastName_getElisaElisabeth() {
        createSampleData();

        target.flushAndClear();

        var customer = this.target.getCustomerByFirstNameAndLastName("Elisa", "Elisabeth");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Elisa", customer.getFirstName());
        Assertions.assertEquals("Elisabeth", customer.getLastName());
        Assertions.assertEquals("elisa@hotmail.com", customer.getEmail());
        Assertions.assertEquals("660", customer.getTelNumber());
        Assertions.assertEquals(false, customer.isEmployee());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllReservations_getOneReservation() {
        createSampleData();

        target.flushAndClear();

        var reservations = this.target.getAllReservations();
        Assertions.assertNotNull(reservations);
        Assertions.assertEquals(1, reservations.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getReservationsOfPublicationByCustomer_getOneReservation() {
        createSampleData();

        target.flushAndClear();

        var publication = this.target.getPublication("The Life of Johann Johnson", "Englisch");
        Assertions.assertNotNull(publication);
        Assertions.assertEquals("The Life of Johann Johnson", publication.getTitle());
        Assertions.assertEquals(true, publication.isTranslation());

        var customer = this.target.getCustomerByFirstNameAndLastName("Clara", "Charlotte");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Clara", customer.getFirstName());
        Assertions.assertEquals("Charlotte", customer.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customer.getEmail());
        Assertions.assertEquals("660", customer.getTelNumber());
        Assertions.assertEquals(false, customer.isEmployee());

        var reservations = this.target.getReservationsOfPublicationByCustomer(publication.getId(), customer.getId());
        Assertions.assertNotNull(reservations);
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals(LocalDate.of(2022, 5, 5), reservations.get(0).getReservationDate());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllLendOuts_getOneLendOut() {
        createSampleData();

        target.flushAndClear();

        var lendOuts = this.target.getAllLendOuts();
        Assertions.assertNotNull(lendOuts);
        Assertions.assertEquals(1, lendOuts.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getLendOutsByCustomer_getOneLendOut() {
        createSampleData();

        target.flushAndClear();

        var customer = this.target.getCustomerByFirstNameAndLastName("Clara", "Charlotte");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Clara", customer.getFirstName());
        Assertions.assertEquals("Charlotte", customer.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customer.getEmail());
        Assertions.assertEquals("660", customer.getTelNumber());
        Assertions.assertEquals(false, customer.isEmployee());

        var lendOuts = this.target.getLendOutsByCustomer(customer.getId());
        Assertions.assertNotNull(lendOuts);
        Assertions.assertEquals(1, lendOuts.size());
        Assertions.assertEquals(LocalDate.of(2022, 3,24), lendOuts.get(0).getLendOutDate());
        Assertions.assertEquals(LocalDate.of(2022, 6, 1), lendOuts.get(0).getReturnDate());


    }

    @TestTransaction
    @Test
    public void createSampleData_getAllMediaTypes_getTwoMediaTypes() {
        createSampleData();

        target.flushAndClear();

        var mediaTypes = this.target.getAllMediaTypes();
        Assertions.assertNotNull(mediaTypes);
        Assertions.assertEquals(2, mediaTypes.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getMediaTypeByDesignation_getMagazin() {
        createSampleData();

        target.flushAndClear();

        var mediaType = this.target.getMediaTypeByDesignation("Magazin");
        Assertions.assertNotNull(mediaType);
        Assertions.assertEquals("Magazin", mediaType.getDesignation());
        Assertions.assertEquals(10, mediaType.getPrice());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllSales_getOneSale() {
        createSampleData();

        target.flushAndClear();

        var sales = this.target.getAllSales();
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(1, sales.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getSalesByEmployee_getOneSale() {
        createSampleData();

        target.flushAndClear();

        var employee = this.target.getEmployeeByFirstNameAndLastName("Rick", "Richard");
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Rick", employee.getFirstName());
        Assertions.assertEquals("Richard", employee.getLastName());
        Assertions.assertEquals("rick@hotmail.com", employee.getEmail());
        Assertions.assertEquals("660", employee.getTelNumber());
        Assertions.assertEquals(1800, employee.getSalary());

        var sales = this.target.getSalesByEmployee(employee.getId());
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(1, sales.size());
        Assertions.assertEquals(LocalDate.of(2022, 3,24), sales.get(0).getSaleDate());
    }
    @TestTransaction
    @Test
    public void createSampleData_getSalesByCustomer_getOneSale() {
        createSampleData();

        target.flushAndClear();

        var customer = this.target.getCustomerByFirstNameAndLastName("Clara", "Charlotte");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Clara", customer.getFirstName());
        Assertions.assertEquals("Charlotte", customer.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customer.getEmail());
        Assertions.assertEquals("660", customer.getTelNumber());
        Assertions.assertEquals(false, customer.isEmployee());

        var sales = this.target.getSalesByCustomer(customer.getId());
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(1, sales.size());
        Assertions.assertEquals(LocalDate.of(2022, 3,24), sales.get(0).getSaleDate());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllSalePositions_getTwoSalePositions() {
        createSampleData();

        target.flushAndClear();

        var salePositions = this.target.getAllSalePositions();
        Assertions.assertNotNull(salePositions);
        Assertions.assertEquals(2, salePositions.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getSalePositionsBySale_getTwoSalePositions() {
        createSampleData();

        target.flushAndClear();

        var customer = this.target.getCustomerByFirstNameAndLastName("Clara", "Charlotte");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Clara", customer.getFirstName());
        Assertions.assertEquals("Charlotte", customer.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customer.getEmail());
        Assertions.assertEquals("660", customer.getTelNumber());
        Assertions.assertEquals(false, customer.isEmployee());

        var sales = this.target.getSalesByCustomer(customer.getId());
        Assertions.assertNotNull(sales);
        Assertions.assertEquals(1, sales.size());
        Assertions.assertEquals(LocalDate.of(2022, 3,24), sales.get(0).getSaleDate());

        var salePositions = this.target.getSalePositionsBySale(sales.get(0).getId());
        Assertions.assertNotNull(salePositions);
        Assertions.assertEquals(2, salePositions.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllAuditTrails_getThreeAuditTrails() {
        createSampleData();

        target.flushAndClear();

        var auditTrails = this.target.getAllAuditTrails();
        Assertions.assertNotNull(auditTrails);
        Assertions.assertEquals(3, auditTrails.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getAuditTrailsByUser_getOneAuditTrail() {
        createSampleData();

        target.flushAndClear();

        var auditTrails = this.target.getAuditTrailsByUser("dbUser03");
        Assertions.assertNotNull(auditTrails);
        Assertions.assertEquals(1, auditTrails.size());
        Assertions.assertEquals("dbUser03", auditTrails.get(0).getUser());
        Assertions.assertEquals(LocalDate.of(2022,1,30), auditTrails.get(0).getDate());
        Assertions.assertEquals("Topic", auditTrails.get(0).getTable());
        Assertions.assertEquals(Action.Delete, auditTrails.get(0).getAction());
        Assertions.assertEquals(3, auditTrails.get(0).getContentId());
        Assertions.assertEquals("Gravitation", auditTrails.get(0).getOldValue());
        Assertions.assertNull(auditTrails.get(0).getNewValue());
    }

    @TestTransaction
    @Test
    public void createSampleData_removeSampleData_noData() {
        var author1 = new Author("Franz", "Franzes", LocalDate.of(1980, 01, 01));
        var author2 = new Author("Johann", "Johnson", LocalDate.of(1971, 3, 20));
        var author3 = new Author("Fritz", "Franzes", LocalDate.of(1971, 3, 20));
        target.add(author1);
        target.add(author2);
        target.add(author3);

        var genre1 = new Genre("Sachbuch");
        var genre2 = new Genre("Biographie");
        target.add(genre1);
        target.add(genre2);

        var topic1 = new Topic("Thermodynamik");
        var topic2 = new Topic("Optik");
        var topic3 = new Topic("Gravitation");
        var topic4 = new Topic("Leben");
        var topic5 = new Topic("Glaube");
        target.add(topic1);
        target.add(topic2);
        target.add(topic3);
        target.add(topic4);
        target.add(topic5);

        var magazine = new Magazine();
        target.add(magazine, author1);
        target.add(magazine, author2);
        target.add(magazine, genre1);
        target.add(magazine, topic1);
        target.add(magazine, topic2);
        target.add(magazine, topic3);

        var book = new Book();
        target.add(book, author2);
        target.add(book, genre2);
        target.add(book, topic4);
        target.add(book, topic5);

        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        target.add(language1);
        target.add(language2);

        var publisher1 = new Publisher("Heinz Verlag");
        var publisher2 = new Publisher("Meier Verlag");
        target.add(publisher1);
        target.add(publisher2);

        var publication1 = new Publication("Die Welt der Teilchen", false);
        var publication2 = new Publication("Das Leben von Johann Johnson", false);
        var publication3 = new Publication("The Life of Johann Johnson", true);

        target.add(publication1, magazine);
        target.add(publication1, language1);
        target.add(publication1, publisher1);

        target.add(publication2, book);
        target.add(publication2, language1);
        target.add(publication2, publisher1);

        target.add(publication3, book);
        target.add(publication3, language2);
        target.add(publication3, publisher2);


        var room1 = new Room(4,1);
        var room2 = new Room(3,2);
        var room3 = new Room(5,2);

        var bookShelf1 = new BookShelf();
        var bookShelf2 = new BookShelf();
        var bookShelf3 = new BookShelf();

        target.add(room1, bookShelf1);
        target.add(room2, bookShelf2);
        target.add(room3, bookShelf3);

        var specimen1 = new Specimen();
        var specimen2 = new Specimen();
        var specimen3 = new Specimen();
        var specimen4 = new Specimen();
        var specimen5 = new Specimen();
        var specimen6 = new Specimen();
        var specimen7 = new Specimen();

        target.add(specimen1, publication1);
        target.add(specimen2, publication1);
        target.add(specimen3, publication1);
        target.add(specimen1, bookShelf1);
        target.add(specimen2, bookShelf1);
        target.add(specimen3, bookShelf1);

        target.add(specimen4, publication2);
        target.add(specimen5, publication2);
        target.add(specimen4, bookShelf2);
        target.add(specimen5, bookShelf2);

        target.add(specimen6, publication3);
        target.add(specimen7, publication3);
        target.add(specimen6, bookShelf3);
        target.add(specimen7, bookShelf3);


        var location1 = new Location("Linz");
        var location2 = new Location("Leonding");
        var location3 = new Location("Enns");
        target.add(location1);
        target.add(location2);
        target.add(location3);

        var employee1 = new Employee("Rick", "Richard", "rick@hotmail.com", "660", 1800);
        target.add(employee1, location1);

        var customer1 = new Customer("Clara", "Charlotte", "clara@hotmail.com", "660", false);
        var customer2 = new Customer("Elisa", "Elisabeth", "elisa@hotmail.com", "660", false);

        target.add(customer1, location2);
        target.add(customer2, location3);

        var reservation1 = new Reservation(LocalDate.of(2022, 5, 5));
        target.add(reservation1, customer1);
        target.add(publication3, reservation1);

        var lendOut1 = new LendOut(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        target.add(lendOut1, customer1);
        target.add(specimen4, lendOut1);

        var mediaType1 = new MediaType("Magazin", 10);
        var mediaType2 = new MediaType("Buch", 20);
        target.add(mediaType1);
        target.add(mediaType2);

        var sale1 = new Sale(LocalDate.of(2022, 3,24));
        target.add(sale1, employee1);
        target.add(sale1, customer1);

        var salePosition1 = new SalePosition(mediaType1.getPrice());
        var salePosition2 = new SalePosition(mediaType2.getPrice());
        target.add(specimen1, salePosition1);
        target.add(specimen5, salePosition2);
        target.add(sale1, salePosition1);
        target.add(sale1, salePosition2);

        var auditTrail1 = new AuditTrail("dbUser01", LocalDate.of(2022,1,10), "Genre", Action.Insert,
                5, null, "Roman");
        var auditTrail2 = new AuditTrail("dbUser02", LocalDate.of(2022,1,20), "Language", Action.Update,
                2, "Englisch", "Englisch - GB");
        var auditTrail3 = new AuditTrail("dbUser03", LocalDate.of(2022,1,30), "Topic", Action.Delete,
                3, "Gravitation", null);
        target.add(auditTrail1);
        target.add(auditTrail2);
        target.add(auditTrail3);

        target.remove(auditTrail1);
        target.remove(auditTrail2);
        target.remove(auditTrail3);

        target.remove(salePosition1);
        target.remove(salePosition2);

        target.remove(sale1);

        target.remove(mediaType1);
        target.remove(mediaType2);

        target.remove(lendOut1);

        target.remove(reservation1);

        target.remove(customer1);
        target.remove(customer2);

        target.remove(employee1);

        target.remove(location1);
        target.remove(location2);
        target.remove(location3);

        target.remove(specimen1);
        target.remove(specimen2);
        target.remove(specimen3);
        target.remove(specimen4);
        target.remove(specimen5);
        target.remove(specimen6);
        target.remove(specimen7);

        target.remove(bookShelf1);
        target.remove(bookShelf2);
        target.remove(bookShelf3);

        target.remove(room1);
        target.remove(room2);
        target.remove(room3);

        target.remove(publication1);
        target.remove(publication2);
        target.remove(publication3);

        target.remove(publisher1);
        target.remove(publisher2);

        target.remove(language1);
        target.remove(language2);

        target.remove(book);

        target.remove(magazine);

        target.remove(topic1);
        target.remove(topic2);
        target.remove(topic3);
        target.remove(topic4);
        target.remove(topic5);

        target.remove(genre1);
        target.remove(genre2);

        target.remove(author1);
        target.remove(author2);
        target.remove(author3);

        target.flushAndClear();

        Assertions.assertEquals(0, target.getAllAuthors().size());
        Assertions.assertEquals(0, target.getAllGenres().size());
        Assertions.assertEquals(0, target.getAllTopics().size());
        Assertions.assertEquals(0, target.getAllMedia().size());
        Assertions.assertEquals(0, target.getAllLanguages().size());
        Assertions.assertEquals(0, target.getAllPublisher().size());
        Assertions.assertEquals(0, target.getAllPublications().size());
        Assertions.assertEquals(0, target.getAllRooms().size());
        Assertions.assertEquals(0, target.getAllBookShelves().size());
        Assertions.assertEquals(0, target.getAllSpecimen().size());
        Assertions.assertEquals(0, target.getAllLocations().size());
        Assertions.assertEquals(0, target.getAllEmployees().size());
        Assertions.assertEquals(0, target.getAllCustomer().size());
        Assertions.assertEquals(0, target.getAllReservations().size());
        Assertions.assertEquals(0, target.getAllLendOuts().size());
        Assertions.assertEquals(0, target.getAllMediaTypes().size());
        Assertions.assertEquals(0, target.getAllSales().size());
        Assertions.assertEquals(0, target.getAllSalePositions().size());
        Assertions.assertEquals(0, target.getAllAuditTrails().size());
    }
}
