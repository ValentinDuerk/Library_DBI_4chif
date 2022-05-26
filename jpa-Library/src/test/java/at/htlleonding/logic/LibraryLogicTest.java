package at.htlleonding.logic;

import at.htlleonding.logic.MediaTypes.*;
import at.htlleonding.persistence.*;
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

//    private void createAuthorDTOs() {
//        AuthorDTO authorDTO1 = new AuthorDTO("Franz", "Franzes", LocalDate.of(1980, 01, 01));
//        AuthorDTO authorDTO2 = new AuthorDTO("Johann", "Johnson", LocalDate.of(1971, 3, 20));
//        AuthorDTO authorDTO3 = new AuthorDTO("Fritz", "Franzes", LocalDate.of(1971, 3, 20));
//
//        this.target.addAuthor(authorDTO1);
//        this.target.addAuthor(authorDTO2);
//        this.target.addAuthor(authorDTO3);
//    }
//
//    @TestTransaction
//    @Test
//    public void createAuthorDTOs_getAllAuthors_get3AuthorDTOs() {
//        createAuthorDTOs();
//
//        target.flushAndClear();
//
//        List<AuthorDTO> authorDTOs = target.getAllAuthors();
//        Assertions.assertNotNull(authorDTOs);
//        Assertions.assertEquals(3, authorDTOs.size());
//    }
//    @TestTransaction
//    @Test
//    public void createAuthorDTOs_getAuthor_getFranzFranzes() {
//        createAuthorDTOs();
//
//        target.flushAndClear();
//
//        AuthorDTO authorDTO = target.getAuthor("Franz", "Franzes");
//        Assertions.assertNotNull(authorDTO);
//        Assertions.assertEquals("Franz", authorDTO.getFirstName());
//        Assertions.assertEquals("Franzes", authorDTO.getLastName());
//    }

    AuthorDTO[] authorDTOs = new AuthorDTO[] {
            new AuthorDTO("Franz", "Franzes", LocalDate.of(1980, 01, 01)),
            new AuthorDTO("Johann", "Johnson", LocalDate.of(1971, 3, 20)),
            new AuthorDTO("Fritz", "Fitz", LocalDate.of(1971, 3, 20))
    };

    GenreDTO[] genreDTOs = new GenreDTO[] {
            new GenreDTO("Sachbuch"),
            new GenreDTO("Biographie")
    };

    TopicDTO[] topicDTOs = new TopicDTO[] {
            new TopicDTO("Thermodynamik"),
            new TopicDTO("Optik"),
            new TopicDTO("Gravitation"),
            new TopicDTO("Leben"),
            new TopicDTO("Glaube")
    };

    MediaDTO[] mediaDTOs = new MediaDTO[] {
            new MagazineDTO(),
            new BookDTO(LocalDate.of(2000,1,1)),
            new EBookDTO(),
            new AudioBookDTO(),
            new NewspaperDTO()
    };

    LanguageDTO[] languageDTOs = new LanguageDTO[] {
            new LanguageDTO("Deutsch"),
            new LanguageDTO("Englisch")
    };

    PublisherDTO[] publisherDTOs = new PublisherDTO[] {
            new PublisherDTO("Heinz Verlag"),
            new PublisherDTO("Meier Verlag")
    };

    PublicationDTO[] publicationDTOs = new PublicationDTO[] {
            new PublicationDTO("Die Welt der Teilchen", false),
            new PublicationDTO("Das Leben von Johann Johnson", false),
            new PublicationDTO("The Life of Johann Johnson", true)
    };

    SpecimenDTO[] specimenDTOs = new SpecimenDTO[] {
            new SpecimenDTO(),
            new SpecimenDTO(),
            new SpecimenDTO(),
            new SpecimenDTO(),
            new SpecimenDTO(),
            new SpecimenDTO(),
            new SpecimenDTO(),
    };

    Location[] locations = new Location[] {
            new Location("Linz"),
            new Location("Leonding"),
            new Location("Enns")
    };

    PersonDTO[] personDTOs = new PersonDTO[] {
            new EmployeeDTO("Rick", "Richard", "rick@hotmail.com", "660", 1800),
            new CustomerDTO("Clara", "Charlotte", "clara@hotmail.com", "660", false),
            new CustomerDTO("Elisa", "Elisabeth", "elisa@hotmail.com", "660", false),
    };

    ReservationDTO[] reservationDTOs = new ReservationDTO[] {
            new ReservationDTO(LocalDate.of(2022, 5, 5))
    };

    LendOutDTO[] lendOutDTOs = new LendOutDTO[] {
            new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1))
    };

    MediaTypeDTO[] mediaTypeDTOs = new MediaTypeDTO[] {
            new MediaTypeDTO("Magazin", 10),
            new MediaTypeDTO("Buch", 20),
            new MediaTypeDTO("E-Buch", 5),
            new MediaTypeDTO("AudioBuch", 2),
            new MediaTypeDTO("Zeitung", 2)
    };

    SaleDTO[] saleDTOs = new SaleDTO[] {
            new SaleDTO(LocalDate.of(2022, 3,24))
    };

    SalePositionDTO[] salePositionDTOs = new SalePositionDTO[] {
            new SalePositionDTO(mediaTypeDTOs[0].getPrice()),
            new SalePositionDTO(mediaTypeDTOs[0].getPrice())
    };


    private void createPaperBookWithOneAuthorAndMakeItRentable() {
        mediaDTOs[1].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[1].setGenreDTO(genreDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[1]);
        mediaDTOs[1].addTopicDTO(topicDTOs[2]);

        publicationDTOs[0].setMediaDTO(mediaDTOs[1]);
        publicationDTOs[0].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[0].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[0].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[0].setPurchaseDate(LocalDate.of(2020, 01, 01));

        target.addSpecimen(specimenDTOs[0]);
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.MagazineStock);
    }

    private void createPaperBookWithThreeAuthorsAndMakeItRentable() {
        mediaDTOs[1].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[1].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[1].addAuthorDTO(authorDTOs[2]);
        mediaDTOs[1].setGenreDTO(genreDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[1]);
        mediaDTOs[1].addTopicDTO(topicDTOs[2]);

        publicationDTOs[0].setMediaDTO(mediaDTOs[1]);
        publicationDTOs[0].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[0].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[0].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[0].setPurchaseDate(LocalDate.of(2020, 01, 01));

        target.addSpecimen(specimenDTOs[0]);
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.MagazineStock);
    }

    private void createThreeCopiesOfPaperBookWithThreeAuthorsAndMakeItRentable() {
        mediaDTOs[1].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[1].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[1].addAuthorDTO(authorDTOs[2]);
        mediaDTOs[1].setGenreDTO(genreDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[0]);
        mediaDTOs[1].addTopicDTO(topicDTOs[1]);
        mediaDTOs[1].addTopicDTO(topicDTOs[2]);

        publicationDTOs[0].setMediaDTO(mediaDTOs[1]);
        publicationDTOs[0].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[0].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[0].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[1].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[2].setPublicationDTO(publicationDTOs[0]);

        specimenDTOs[0].setPurchaseDate(LocalDate.of(2020, 01, 01));
        specimenDTOs[1].setPurchaseDate(LocalDate.of(2020, 01, 01));
        specimenDTOs[2].setPurchaseDate(LocalDate.of(2020, 01, 01));

        target.addSpecimen(specimenDTOs[0]);
        target.addSpecimen(specimenDTOs[1]);
        target.addSpecimen(specimenDTOs[2]);
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[1].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[2].getId(), SpecimenState.MagazineStock);
    }

    private void createRentableItemAndLendOut() {
//        mediaDTOs[1].addAuthorDTO(authorDTOs[0]);
//        mediaDTOs[1].setGenreDTO(genreDTOs[0]);
//        mediaDTOs[1].addTopicDTO(topicDTOs[0]);
//        mediaDTOs[1].addTopicDTO(topicDTOs[1]);
//        mediaDTOs[1].addTopicDTO(topicDTOs[2]);
//
//        publicationDTOs[0].setMediaDTO(mediaDTOs[1]);
//        publicationDTOs[0].setPublisherDTO(publisherDTOs[0]);
//        publicationDTOs[0].setLanguageDTO(languageDTOs[0]);
//
//        specimenDTOs[0].setPublicationDTO(publicationDTOs[0]);
//
//        target.addSpecimen(specimenDTOs[0]);
//        target.SetSpecimenState(specimenDTOs[0].getId(), SpecimenState.MagazineStock);

        createPaperBookWithOneAuthorAndMakeItRentable();

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
    }

    /*
    Add rentable items to the library, of each media type, with multiple authors and attributes.
    Verify that these items can be rented.
    */
    @Test
    @TestTransaction
    public void addPaperBookWithOneAuthor_makeRentable_canBeRented()
    {
        createPaperBookWithOneAuthorAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Die Welt der Teilchen", "Heinz Verlag", "Deutsch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertEquals(LocalDate.of(2020, 01, 01), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("Die Welt der Teilchen", specimenDTO.getTitle());
        Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
        Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Franzes"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        createPaperBookWithThreeAuthorsAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Die Welt der Teilchen", "Heinz Verlag", "Deutsch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertEquals(LocalDate.of(2020, 01, 01), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("Die Welt der Teilchen", specimenDTO.getTitle());
        Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
        Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
        Assertions.assertEquals(3, specimenDTO.getAuthorsLastNames().size());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Franzes"));
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Johnson"));
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Fitz"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        createThreeCopiesOfPaperBookWithThreeAuthorsAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Die Welt der Teilchen", "Heinz Verlag", "Deutsch");

        Assertions.assertEquals(3, specimenDTOs.size());

        for (var specimenDTO : specimenDTOs) {
            Assertions.assertEquals(LocalDate.of(2020, 01, 01), specimenDTO.getPurchaseDate());
            Assertions.assertEquals("Die Welt der Teilchen", specimenDTO.getTitle());
            Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
            Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
            Assertions.assertEquals(3, specimenDTO.getAuthorsLastNames().size());
            Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Franzes"));
            Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Johnson"));
            Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Fitz"));

            Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
        }
    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }


    /*
     Add a library customer.
     Add a library employee.
     */
    @Test
    @TestTransaction
    public void addLibraryCustomer_isAvailable()
    {
        CustomerDTO customerDTO1 = new CustomerDTO("Clara", "Charlotte", "clara@hotmail.com", "660", false);
        target.addCustomer(customerDTO1);

        target.flushAndClear();

        CustomerDTO customerDTO2 = target.getCustomer("Clara", "Charlotte");

        Assertions.assertNotNull(customerDTO2);
        Assertions.assertEquals("Clara", customerDTO2.getFirstName());
        Assertions.assertEquals("Charlotte", customerDTO2.getLastName());
        Assertions.assertEquals("clara@hotmail.com", customerDTO2.getEmail());
        Assertions.assertEquals("660", customerDTO2.getTelNumber());
        Assertions.assertEquals(false, customerDTO2.isEmployee());
    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Rick", "Richard", "rick@hotmail.com", "660", 1800);
        target.addEmployee(employeeDTO1);

        target.flushAndClear();

        EmployeeDTO employeeDTO2 = target.getEmployee("Rick", "Richard");

        Assertions.assertNotNull(employeeDTO2);
        Assertions.assertEquals("Rick", employeeDTO2.getFirstName());
        Assertions.assertEquals("Richard", employeeDTO2.getLastName());
        Assertions.assertEquals("rick@hotmail.com", employeeDTO2.getEmail());
        Assertions.assertEquals("660", employeeDTO2.getTelNumber());
        Assertions.assertEquals(1800, employeeDTO2.getSalary());
    }

    /*
     Rent out, bring back, reserve and prolong.
     Verify state of rented items and customer's rent list.
    */
    @Test
    @TestTransaction
    public void customerRentsRentableItem_ItemIsRented()
    {
        createRentableItemAndLendOut();

        target.flushAndClear();

        LendOutDTO lendOutDTO = target.getLendOut(specimenDTOs[0].getId(), "Clara", "Charlotte", LocalDate.of(2022, 3,24));

        Assertions.assertNotNull(lendOutDTO);
        Assertions.assertEquals(LocalDate.of(2022, 3,24), lendOutDTO.getLendOutDate());
        Assertions.assertEquals(LocalDate.of(2022, 6, 1), lendOutDTO.getReturnDate());
        Assertions.assertEquals(1, lendOutDTO.getExtensions());
        Assertions.assertEquals("Clara", lendOutDTO.getCustomerFirstName());
        Assertions.assertEquals("Charlotte", lendOutDTO.getCustomerLastName());
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void rentOutItemToCustomerA_customerBmakesReservation_CustomerAreturnsItem_RentPossibleOnlyForCustomerB()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentThreeTimes_customerCanOnlyProlongTwoTimes_rentalEndDate6weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    /*
      - Declare a library item to be for sale, it cannot be rented anymore.
      - Sell one library item to a customer, create invoice. Item cannot be rented anymore.
      - Sell some items of multiple books.
     */
    @Test
    @TestTransaction
    public void setItemForSale_cannotBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {
        Assertions.fail("Not implemented yet");
    }
}