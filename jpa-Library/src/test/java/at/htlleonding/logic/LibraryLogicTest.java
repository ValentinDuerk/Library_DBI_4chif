package at.htlleonding.logic;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@QuarkusTest
class LibraryLogicTest {

    @Inject
    LibraryLogic target;

    private void createAuthorDTOs() {
        AuthorDTO authorDTO1 = new AuthorDTO("Franz", "Franzes", LocalDate.of(1980, 01, 01));
        AuthorDTO authorDTO2 = new AuthorDTO("Johann", "Johnson", LocalDate.of(1971, 3, 20));
        AuthorDTO authorDTO3 = new AuthorDTO("Fritz", "Franzes", LocalDate.of(1971, 3, 20));

        this.target.addAuthor(authorDTO1);
        this.target.addAuthor(authorDTO2);
        this.target.addAuthor(authorDTO3);
    }

    @TestTransaction
    @Test
    public void createAuthorDTOs_getAllAuthors_get3AuthorDTOs() {
        createAuthorDTOs();

        target.flushAndClear();

        List<AuthorDTO> authorDTOs = target.getAllAuthors();
        Assertions.assertNotNull(authorDTOs);
        Assertions.assertEquals(3, authorDTOs.size());
    }
    @TestTransaction
    @Test
    public void createAuthorDTOs_getAuthor_getFranzFranzes() {
        createAuthorDTOs();

        target.flushAndClear();

        AuthorDTO authorDTO = target.getAuthor("Franz", "Franzes");
        Assertions.assertNotNull(authorDTO);
        Assertions.assertEquals("Franz", authorDTO.getFirstName());
        Assertions.assertEquals("Franzes", authorDTO.getLastName());
    }
}