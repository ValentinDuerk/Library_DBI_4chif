package at.htlleonding.persistence;

import at.htlleonding.persistence.Author;
import at.htlleonding.persistence.Book;
import at.htlleonding.persistence.LibraryRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTest
public class LibraryRepositoryTest {
    @Inject
    LibraryRepository target;

    private void createSampleData() {
        var author1 = new Author("Franz", "Franzes", LocalDate.of(1980, 01, 01));
        var author2 = new Author("Johann", "Johnson", LocalDate.of(1971, 3, 20));

//        var b = new Book("Das Leben des Franzes");

        var genre1 = new Genre("Biographie");
        var genre2 = new Genre("Philosophie");

        var topic1 = new Topic("Leben");
        var topic2 = new Topic("Glaube");

        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");

        var publisher1 = new Publisher("Heinz Verlag");
        var publisher2 = new Publisher("Meier Verlag");
    }
}
