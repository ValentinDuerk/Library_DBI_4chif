package at.htlleonding.persistence;

import at.htlleonding.persistence.MediaTypes.Book;
import at.htlleonding.persistence.MediaTypes.EBook;
import at.htlleonding.persistence.MediaTypes.Magazine;
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
        target.add(author1);
        target.add(author2);

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

    }

    @TestTransaction
    @Test
    public void createSampleData_getAllAuthors_getTwoAuthors() {
        createSampleData();

        target.flushAndClear();

        var authors = this.target.getAllAuthors();
        Assertions.assertNotNull(authors);
        Assertions.assertEquals(2, authors.size());
    }
    @TestTransaction
    @Test
    public void createSampleData_getAuthorByLastname_getFranzes() {
        createSampleData();

        target.flushAndClear();

        var author = this.target.getAuthorByLastName("Franzes");
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

        var publication = this.target.getPublication("The Life of Johann Johnson", "Deutsch");
        Assertions.assertNotNull(publication);
        Assertions.assertEquals("The Life of Johann Johnson", publication.getTitle());
        Assertions.assertEquals(false, publication.isTranslation());

        var specimens = this.target.getSpecimensByPublication(publication.getId());
        Assertions.assertNotNull(specimens);
        Assertions.assertEquals(2, specimens.size());

    }
}
