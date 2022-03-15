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
}
