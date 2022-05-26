package at.htlleonding.logic;

import at.htlleonding.logic.MediaTypes.*;
import at.htlleonding.persistence.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@QuarkusTest
class LibraryLogicTest {

    @Inject
    LibraryLogic target;

    AuthorDTO[] authorDTOs = new AuthorDTO[] {
            new AuthorDTO("Franz", "Franzes", LocalDate.of(1980, 01, 01)),
            new AuthorDTO("Johann", "Johnson", LocalDate.of(1971, 3, 20)),
            new AuthorDTO("Fritz", "Fitz", LocalDate.of(1971, 3, 20))
    };

    GenreDTO[] genreDTOs = new GenreDTO[] {
            new GenreDTO("Sachbuch"),
            new GenreDTO("News")
    };

    TopicDTO[] topicDTOs = new TopicDTO[] {
            new TopicDTO("Thermodynamik"),
            new TopicDTO("Optik"),
            new TopicDTO("Gravitation"),
            new TopicDTO("Corona"),
            new TopicDTO("Glaube"),
            new TopicDTO("Technik")
    };

    MediaDTO[] mediaDTOs = new MediaDTO[] {
            new MagazineDTO(),
            new BookDTO(),
            new EBookDTO(),
            new AudioBookDTO(),
            new NewspaperDTO(),
            new ReferenceBookDTO()
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
            new PublicationDTO("Wieder ab in den Lockdown", false),
            new PublicationDTO("Das Leben von Johann Johnson", false),
            new PublicationDTO("The Life of Johann Johnson", true),
            new PublicationDTO("Moleküle der Chemie", false),
            new PublicationDTO("Die Kunst des Unit-Testen", false)
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

    PersonDTO[] personDTOs = new PersonDTO[] {
            new EmployeeDTO("Rick", "Richard", "rick@hotmail.com", "660", 1800),
            new CustomerDTO("Clara", "Charlotte", "clara@hotmail.com", "660", false),
            new CustomerDTO("Elisa", "Elisabeth", "elisa@hotmail.com", "660", false),
    };

    ReservationDTO[] reservationDTOs = new ReservationDTO[] {
            new ReservationDTO(LocalDate.of(2022, 5, 5))
    };

    LendOutDTO[] lendOutDTOs = new LendOutDTO[] {
            new LendOutDTO(),
            new LendOutDTO(),
            new LendOutDTO(),
            new LendOutDTO(),
            new LendOutDTO(),
            new LendOutDTO()
    };

    MediaTypeDTO[] mediaTypeDTOs = new MediaTypeDTO[] {
            new MediaTypeDTO("Magazin", 10),
            new MediaTypeDTO("Buch", 20),
            new MediaTypeDTO("E-Buch", 5),
            new MediaTypeDTO("AudioBuch", 2),
            new MediaTypeDTO("Zeitung", 2)
    };

    SaleDTO[] saleDTOs = new SaleDTO[] {
            new SaleDTO()
    };

    SalePositionDTO[] salePositionDTOs = new SalePositionDTO[] {
            new SalePositionDTO(),
            new SalePositionDTO(),
            new SalePositionDTO(),
    };


    private void createPaperBookWithOneAuthorAndMakeItRentable() {
        mediaDTOs[1].setPublicationDate(LocalDate.of(2000,1,1));

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
        mediaDTOs[1].setPublicationDate(LocalDate.of(2000,1,1));

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
        mediaDTOs[1].setPublicationDate(LocalDate.of(2000,1,1));

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

    private void createNewspaperWithOneAuthorAndMakeItRentable() {
        mediaDTOs[4].setPublicationDate(LocalDate.of(2021,11,22));
        mediaDTOs[4].addAuthorDTO(authorDTOs[2]);
        mediaDTOs[4].setGenreDTO(genreDTOs[1]);
        mediaDTOs[4].addTopicDTO(topicDTOs[3]);

        publicationDTOs[1].setMediaDTO(mediaDTOs[4]);
        publicationDTOs[1].setPublisherDTO(publisherDTOs[1]);
        publicationDTOs[1].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[3].setPublicationDTO(publicationDTOs[1]);
        specimenDTOs[3].setPurchaseDate(LocalDate.of(2021, 11, 23));

        target.addSpecimen(specimenDTOs[3]);
        target.setSpecimenState(specimenDTOs[3].getId(), SpecimenState.MagazineStock);
    }

    private void createAudioBookWithOneAuthorAndMakeItRentable() {
        mediaDTOs[3].setPublicationDate(LocalDate.of(2015,1,1));

        mediaDTOs[3].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[3].setGenreDTO(genreDTOs[0]);
        mediaDTOs[3].addTopicDTO(topicDTOs[4]);

        publicationDTOs[2].setMediaDTO(mediaDTOs[3]);
        publicationDTOs[2].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[2].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[4].setPublicationDTO(publicationDTOs[2]);
        specimenDTOs[4].setPurchaseDate(LocalDate.of(2016, 1, 1));

        target.addSpecimen(specimenDTOs[4]);
        target.setSpecimenState(specimenDTOs[4].getId(), SpecimenState.MagazineStock);
    }

    private void createEBookWithOneAuthorAndMakeItRentable() {
        mediaDTOs[2].setPublicationDate(LocalDate.of(2015,1,1));

        mediaDTOs[2].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[2].setGenreDTO(genreDTOs[0]);
        mediaDTOs[2].addTopicDTO(topicDTOs[4]);

        publicationDTOs[3].setMediaDTO(mediaDTOs[2]);
        publicationDTOs[3].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[3].setLanguageDTO(languageDTOs[1]);

        specimenDTOs[5].setPublicationDTO(publicationDTOs[3]);
        specimenDTOs[5].setPurchaseDate(LocalDate.of(2016, 1, 1));

        target.addSpecimen(specimenDTOs[5]);
        target.setSpecimenState(specimenDTOs[5].getId(), SpecimenState.MagazineStock);
    }

    private void createMagazineWithOneAuthorAndMakeItRentable() {
        mediaDTOs[0].setPublicationDate(LocalDate.of(2022,1,1));

        mediaDTOs[0].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[0].setGenreDTO(genreDTOs[0]);

        publicationDTOs[4].setMediaDTO(mediaDTOs[0]);
        publicationDTOs[4].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[4].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[6].setPublicationDTO(publicationDTOs[4]);
        specimenDTOs[6].setPurchaseDate(LocalDate.of(2022, 01, 10));

        target.addSpecimen(specimenDTOs[6]);
        target.setSpecimenState(specimenDTOs[6].getId(), SpecimenState.MagazineStock);
    }


    private void createRentableItemAndLendOut() {
        createPaperBookWithOneAuthorAndMakeItRentable();
        lendOutDTOs[0] = new LendOutDTO(LocalDate.now());

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
    }

    private void createThreeRentableItemsAndLendOutOne() {
        createThreeCopiesOfPaperBookWithThreeAuthorsAndMakeItRentable();
        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
    }

    private void createThreeRentableItemsAndTryOutToLendOutFour() {
        createThreeCopiesOfPaperBookWithThreeAuthorsAndMakeItRentable();

        // Created above three rentable copies of a paper book and underneath one which is not rentable
        specimenDTOs[3].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[3].setPurchaseDate(LocalDate.of(2020, 01, 01));
        target.addSpecimen(specimenDTOs[3]);
        target.setSpecimenState(specimenDTOs[3].getId(), SpecimenState.PresentStock);


        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        lendOutDTOs[1] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        lendOutDTOs[2] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        lendOutDTOs[3] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        lendOutDTOs[1].setSpecimenDTO(specimenDTOs[1]);
        lendOutDTOs[1].setCustomerDTO((CustomerDTO) personDTOs[1]);

        lendOutDTOs[2].setSpecimenDTO(specimenDTOs[2]);
        lendOutDTOs[2].setCustomerDTO((CustomerDTO) personDTOs[1]);

        lendOutDTOs[3].setSpecimenDTO(specimenDTOs[3]);
        lendOutDTOs[3].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
        target.addLendOut(lendOutDTOs[1]);
        target.addLendOut(lendOutDTOs[2]);
        target.addLendOut(lendOutDTOs[3]);
    }

    private void createOneItemOfEachMediaAndLendOutAllSix() {
        mediaDTOs[0].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[0].setGenreDTO(genreDTOs[0]);
        mediaDTOs[0].addTopicDTO(topicDTOs[0]);

        mediaDTOs[1].addAuthorDTO(authorDTOs[2]);
        mediaDTOs[1].setGenreDTO(genreDTOs[1]);

        mediaDTOs[2].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[2].setGenreDTO(genreDTOs[0]);
        mediaDTOs[2].addTopicDTO(topicDTOs[4]);

        mediaDTOs[3].addAuthorDTO(authorDTOs[1]);
        mediaDTOs[3].setGenreDTO(genreDTOs[0]);
        mediaDTOs[3].addTopicDTO(topicDTOs[4]);

        mediaDTOs[4].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[4].setGenreDTO(genreDTOs[0]);

        mediaDTOs[5].addAuthorDTO(authorDTOs[0]);
        mediaDTOs[5].setGenreDTO(genreDTOs[0]);
        mediaDTOs[5].addTopicDTO(topicDTOs[5]);

        publicationDTOs[0].setMediaDTO(mediaDTOs[0]);
        publicationDTOs[0].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[0].setLanguageDTO(languageDTOs[0]);

        publicationDTOs[1].setMediaDTO(mediaDTOs[1]);
        publicationDTOs[1].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[1].setLanguageDTO(languageDTOs[0]);

        publicationDTOs[2].setMediaDTO(mediaDTOs[2]);
        publicationDTOs[2].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[2].setLanguageDTO(languageDTOs[0]);

        publicationDTOs[3].setMediaDTO(mediaDTOs[3]);
        publicationDTOs[3].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[3].setLanguageDTO(languageDTOs[0]);

        publicationDTOs[4].setMediaDTO(mediaDTOs[4]);
        publicationDTOs[4].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[4].setLanguageDTO(languageDTOs[0]);

        publicationDTOs[5].setMediaDTO(mediaDTOs[5]);
        publicationDTOs[5].setPublisherDTO(publisherDTOs[0]);
        publicationDTOs[5].setLanguageDTO(languageDTOs[0]);

        specimenDTOs[0].setPurchaseDate(LocalDate.of(2019,1,1));
        specimenDTOs[1].setPurchaseDate(LocalDate.of(2019,1,1));
        specimenDTOs[2].setPurchaseDate(LocalDate.of(2019,1,1));
        specimenDTOs[3].setPurchaseDate(LocalDate.of(2019,1,1));
        specimenDTOs[4].setPurchaseDate(LocalDate.of(2019,1,1));
        specimenDTOs[5].setPurchaseDate(LocalDate.of(2019,1,1));

        specimenDTOs[0].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[1].setPublicationDTO(publicationDTOs[1]);
        specimenDTOs[2].setPublicationDTO(publicationDTOs[2]);
        specimenDTOs[3].setPublicationDTO(publicationDTOs[3]);
        specimenDTOs[4].setPublicationDTO(publicationDTOs[4]);
        specimenDTOs[5].setPublicationDTO(publicationDTOs[5]);

        target.addSpecimen(specimenDTOs[0]);
        target.addSpecimen(specimenDTOs[1]);
        target.addSpecimen(specimenDTOs[2]);
        target.addSpecimen(specimenDTOs[3]);
        target.addSpecimen(specimenDTOs[4]);
        target.addSpecimen(specimenDTOs[5]);
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[1].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[2].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[3].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[4].getId(), SpecimenState.MagazineStock);
        target.setSpecimenState(specimenDTOs[5].getId(), SpecimenState.MagazineStock);

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[0].setLendOutDate(LocalDate.of(2022,1,1));
        lendOutDTOs[1].setSpecimenDTO(specimenDTOs[1]);
        lendOutDTOs[1].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[1].setLendOutDate(LocalDate.of(2022,1,1));
        lendOutDTOs[2].setSpecimenDTO(specimenDTOs[2]);
        lendOutDTOs[2].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[2].setLendOutDate(LocalDate.of(2022,1,1));
        lendOutDTOs[3].setSpecimenDTO(specimenDTOs[3]);
        lendOutDTOs[3].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[3].setLendOutDate(LocalDate.of(2022,1,1));
        lendOutDTOs[4].setSpecimenDTO(specimenDTOs[4]);
        lendOutDTOs[4].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[4].setLendOutDate(LocalDate.of(2022,1,1));
        lendOutDTOs[5].setSpecimenDTO(specimenDTOs[5]);
        lendOutDTOs[5].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[5].setLendOutDate(LocalDate.of(2022,1,1));

        target.addLendOut(lendOutDTOs[0]);
        target.addLendOut(lendOutDTOs[1]);
        target.addLendOut(lendOutDTOs[2]);
        target.addLendOut(lendOutDTOs[3]);
        target.addLendOut(lendOutDTOs[4]);
        target.addLendOut(lendOutDTOs[5]);
    }

    private void createItemAndSetForSaleAndTryToLendOut() {
        createPaperBookWithOneAuthorAndMakeItRentable();
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.SoldStock);

        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
    }

    private void createOneItemForSaleAndOneItemForLendOutAndTryToLendOutBoth() {
        mediaDTOs[1].setPublicationDate(LocalDate.of(2000,1,1));

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
        specimenDTOs[1].setPublicationDTO(publicationDTOs[0]);
        specimenDTOs[1].setPurchaseDate(LocalDate.of(2020, 01, 01));


        target.addSpecimen(specimenDTOs[0]);
        target.addSpecimen(specimenDTOs[1]);

        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.SoldStock);
        target.setSpecimenState(specimenDTOs[1].getId(), SpecimenState.MagazineStock);

        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));
        lendOutDTOs[1] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);
        lendOutDTOs[1].setSpecimenDTO(specimenDTOs[1]);
        lendOutDTOs[1].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
        target.addLendOut(lendOutDTOs[1]);
    }

    private void createItemAndSetOnDisplayAndTryToLendOut() {
        mediaDTOs[1].setPublicationDate(LocalDate.of(2000,1,1));

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
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.PresentStock);

        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addLendOut(lendOutDTOs[0]);
    }

    private void createThreeItemsAndSellTwoOfThem() {
        createThreeCopiesOfPaperBookWithThreeAuthorsAndMakeItRentable();
        target.setSpecimenState(specimenDTOs[0].getId(), SpecimenState.SoldStock);
        target.setSpecimenState(specimenDTOs[1].getId(), SpecimenState.SoldStock);
        target.setSpecimenState(specimenDTOs[2].getId(), SpecimenState.SoldStock);

        saleDTOs[0] = new SaleDTO(LocalDate.of(2022, 3,24));
        saleDTOs[0].setEmployeeDTO((EmployeeDTO) personDTOs[0]);
        saleDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        target.addSale(saleDTOs[0]);

        salePositionDTOs[0] = new SalePositionDTO(mediaTypeDTOs[1].getPrice());
        salePositionDTOs[1] = new SalePositionDTO(mediaTypeDTOs[1].getPrice());

        salePositionDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        salePositionDTOs[0].setSaleDTO(saleDTOs[0]);
        salePositionDTOs[1].setSpecimenDTO(specimenDTOs[1]);
        salePositionDTOs[1].setSaleDTO(saleDTOs[0]);

        target.addSalePosition(salePositionDTOs[0]);
        target.addSalePosition(salePositionDTOs[1]);
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
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof BookDTO);

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
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof BookDTO);

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
            Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof BookDTO);

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
        createNewspaperWithOneAuthorAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Wieder ab in den Lockdown", "Meier Verlag", "Deutsch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof NewspaperDTO);

        Assertions.assertEquals(LocalDate.of(2021, 11, 23), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("Wieder ab in den Lockdown", specimenDTO.getTitle());
        Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
        Assertions.assertEquals("Meier Verlag", specimenDTO.getPublisher());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Fitz"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        createAudioBookWithOneAuthorAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Das Leben von Johann Johnson", "Heinz Verlag", "Deutsch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof AudioBookDTO);

        Assertions.assertEquals(LocalDate.of(2016, 1, 1), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("Das Leben von Johann Johnson", specimenDTO.getTitle());
        Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
        Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Johnson"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        createEBookWithOneAuthorAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("The Life of Johann Johnson", "Heinz Verlag", "Englisch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof EBookDTO);

        Assertions.assertEquals(LocalDate.of(2016, 1, 1), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("The Life of Johann Johnson", specimenDTO.getTitle());
        Assertions.assertEquals("Englisch", specimenDTO.getLanguage());
        Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Johnson"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        createMagazineWithOneAuthorAndMakeItRentable();

        target.flushAndClear();

        List<SpecimenDTO> specimenDTOs = target.getSpecimensByPublication("Moleküle der Chemie", "Heinz Verlag", "Deutsch");

        Assertions.assertEquals(1, specimenDTOs.size());

        SpecimenDTO specimenDTO = specimenDTOs.get(0);
        Assertions.assertTrue(specimenDTO.getPublicationDTO().getMediaDTO() instanceof MagazineDTO);

        Assertions.assertEquals(LocalDate.of(2022, 1, 10), specimenDTO.getPurchaseDate());
        Assertions.assertEquals("Moleküle der Chemie", specimenDTO.getTitle());
        Assertions.assertEquals("Deutsch", specimenDTO.getLanguage());
        Assertions.assertEquals("Heinz Verlag", specimenDTO.getPublisher());
        Assertions.assertTrue(specimenDTO.getAuthorsLastNames().contains("Franzes"));

        Assertions.assertEquals(SpecimenState.MagazineStock, specimenDTO.getSpecimenState());
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

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(1, lendOutDTOs.size());

        LendOutDTO lendOutDTO = lendOutDTOs.get(0);
        Assertions.assertEquals(LocalDate.now(), lendOutDTO.getLendOutDate());
        Assertions.assertEquals(LocalDate.now().plusDays(14), lendOutDTO.getReturnDate());
        Assertions.assertEquals(0, lendOutDTO.getExtensions());
        Assertions.assertEquals("Clara", lendOutDTO.getCustomerFirstName());
        Assertions.assertEquals("Charlotte", lendOutDTO.getCustomerLastName());
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        createThreeRentableItemsAndLendOutOne();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(1, lendOutDTOs.size());

        LendOutDTO lendOutDTO = lendOutDTOs.get(0);
        Assertions.assertEquals(LocalDate.of(2022, 3,24), lendOutDTO.getLendOutDate());
        Assertions.assertEquals(LocalDate.of(2022, 3,24).plusDays(14), lendOutDTO.getReturnDate());
        Assertions.assertEquals(0, lendOutDTO.getExtensions());
        Assertions.assertEquals("Clara", lendOutDTO.getCustomerFirstName());
        Assertions.assertEquals("Charlotte", lendOutDTO.getCustomerLastName());
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        createThreeRentableItemsAndTryOutToLendOutFour();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(3, lendOutDTOs.size());
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        createOneItemOfEachMediaAndLendOutAllSix();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(6, lendOutDTOs.size());

        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof MagazineDTO));
        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof BookDTO));
        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof EBookDTO));
        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof AudioBookDTO));
        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof NewspaperDTO));
        Assertions.assertTrue(lendOutDTOs.stream().anyMatch(x -> x.getSpecimenDTO().getPublicationDTO().getMediaDTO() instanceof ReferenceBookDTO));
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        createItemAndSetForSaleAndTryToLendOut();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(0, lendOutDTOs.size());
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        createItemAndSetOnDisplayAndTryToLendOut();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(0, lendOutDTOs.size());
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        createRentableItemAndLendOut();

        lendOutDTOs[1] = new LendOutDTO(LocalDate.of(2022, 3,30));

        lendOutDTOs[1].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[1].setCustomerDTO((CustomerDTO) personDTOs[2]);

        boolean result = target.addLendOut(lendOutDTOs[1]);

        Assertions.assertFalse(result);

        target.cancelLendOut(lendOutDTOs[0].getId());

        lendOutDTOs[1] = new LendOutDTO(LocalDate.of(2022, 3,30), LocalDate.of(2022, 6, 1));

        lendOutDTOs[1].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[1].setCustomerDTO((CustomerDTO) personDTOs[2]);

        result = target.addLendOut(lendOutDTOs[1]);

        Assertions.assertTrue(result);
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
        createPaperBookWithOneAuthorAndMakeItRentable();
        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        boolean result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertFalse(result);
        Assertions.assertEquals(true, Math.abs(ChronoUnit.WEEKS.between(lendOutDTOs[0].getLendOutDate(), lendOutDTOs[0].getReturnDate())) == 6);
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        createPaperBookWithOneAuthorAndMakeItRentable();
        lendOutDTOs[0] = new LendOutDTO(LocalDate.of(2022, 3,24), LocalDate.of(2022, 6, 1));

        lendOutDTOs[0].setSpecimenDTO(specimenDTOs[0]);
        lendOutDTOs[0].setCustomerDTO((CustomerDTO) personDTOs[1]);

        boolean result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        result = target.addLendOut(lendOutDTOs[0]);
        Assertions.assertTrue(result);

        target.addEmployee((EmployeeDTO) personDTOs[0]);
        target.addLendOutFromEmployee(personDTOs[0].getId(), lendOutDTOs[0]);

        Assertions.assertTrue(result);
        Assertions.assertEquals(true, Math.abs(ChronoUnit.WEEKS.between(lendOutDTOs[0].getLendOutDate(), lendOutDTOs[0].getReturnDate())) == 8);

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
        createItemAndSetForSaleAndTryToLendOut();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(0, lendOutDTOs.size());
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {
        createOneItemForSaleAndOneItemForLendOutAndTryToLendOutBoth();

        target.flushAndClear();

        List<LendOutDTO> lendOutDTOs = target.getCurrentLendOutsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(1, lendOutDTOs.size());
    }

    @Test
    @TestTransaction
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {
        createThreeItemsAndSellTwoOfThem();

        target.flushAndClear();

        List<SalePositionDTO> salePositionDTOs = target.getSalePositionsOfCustomer( "Clara", "Charlotte");

        Assertions.assertEquals(2, salePositionDTOs.size());
    }
}