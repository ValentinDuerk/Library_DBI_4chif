package at.htlleonding.persistence;

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

        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        target.add(language1);
        target.add(language2);

        var publisher1 = new Publisher("Heinz Verlag");
        var publisher2 = new Publisher("Meier Verlag");
        target.add(publisher1);
        target.add(publisher2);

        var publication1 = new Publication("1990", false);
        var publication2 = new Publication("Das Leben von Johann Johnson", false);
        var publication3 = new Publication("The Life of Johann Johnson", true);
        target.add(publication1, language1);
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
    public void createSampleData_getPublication_get1990() {
        createSampleData();

        target.flushAndClear();

        var publication = this.target.getPublication("1990", "Deutsch");
        Assertions.assertNotNull(publication);
        Assertions.assertEquals("1990", publication.getTitle());
        Assertions.assertEquals(false, publication.isTranslation());
    }
}
