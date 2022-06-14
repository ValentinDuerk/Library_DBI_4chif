package at.htlleonding.logic;

import at.htlleonding.logic.MediaTypes.*;
import at.htlleonding.logic.xmlDTOs.AuthorDTO;
import at.htlleonding.logic.xmlDTOs.AuthorWorkDTO;
import at.htlleonding.logic.xmlDTOs.WorkPublicationDTO;
import at.htlleonding.logic.xmlDTOs.WorksOfAuthorsDTO;
import at.htlleonding.persistence.MediaTypes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WorksOfAuthorsXmlTest {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Inject
    LibraryLogic target;

    @Test
    public void createWorksOfAuthorDTO_serializeToXml_deserialize_checkObjects() {
        var worksOfAuthors = new WorksOfAuthorsDTO();

        var author1 = new AuthorDTO("John", "Doe", LocalDate.parse("01.01.2001", dtf), LocalDate.parse("31.12.2020", dtf));
        var work1OfAuthor1 = new AuthorWorkDTO("Johns Adventures", "fiction");
        work1OfAuthor1.getPublications().add(new WorkPublicationDTO("Self", LocalDate.parse("10.01.2015", dtf), "PaperBook"));
        work1OfAuthor1.getPublications().add(new WorkPublicationDTO("audiofan", LocalDate.parse("10.01.2015", dtf), "AudioBook"));

        var work2OfAuthor1 = new AuthorWorkDTO("Johns Tractor Guide", "how-to");
        work2OfAuthor1.getPublications().add(new WorkPublicationDTO("Self", LocalDate.parse("10.01.2015", dtf), "PaperBook"));
        work2OfAuthor1.getPublications().add(new WorkPublicationDTO("audiofan", LocalDate.parse("10.01.2015", dtf), "AudioBook"));

        author1.getWorks().add(work1OfAuthor1);
        author1.getWorks().add(work2OfAuthor1);


        var author2 = new AuthorDTO("Mary", "Doe", LocalDate.parse("01.02.2001", dtf), LocalDate.parse("01.12.2020", dtf));
        var work1OfAuthor2 = new AuthorWorkDTO("Mary and Johns Adventures", "fiction");
        work1OfAuthor2.getPublications().add(new WorkPublicationDTO("Self", LocalDate.parse("10.01.2015", dtf), "PaperBook"));
        work1OfAuthor2.getPublications().add(new WorkPublicationDTO("audiofan", LocalDate.parse("10.01.2015", dtf), "AudioBook"));

        var work2OfAuthor2 = new AuthorWorkDTO("Marys Cooking Guide", "how-to");
        work2OfAuthor2.getPublications().add(new WorkPublicationDTO("Self", LocalDate.parse("10.01.2015", dtf), "PaperBook"));
        work2OfAuthor2.getPublications().add(new WorkPublicationDTO("audiofan", LocalDate.parse("10.01.2015", dtf), "AudioBook"));

        author2.getWorks().add(work1OfAuthor2);
        author2.getWorks().add(work2OfAuthor2);


        worksOfAuthors.getAuthors().add(author1);
        worksOfAuthors.getAuthors().add(author2);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        StringBuilder sb = new StringBuilder();
        try {
            sb.append(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(worksOfAuthors));
            var result = sb.toString();
            assertTrue(result.contains("John"));
            assertTrue(result.contains("Johns Adventures"));
            System.out.println(result);

            Reader reader = new StringReader(result);
            var result2 = (WorksOfAuthorsDTO)xmlMapper.readValue(reader, WorksOfAuthorsDTO.class);
            Assertions.assertEquals("Doe", result2.getAuthors().get(0).getLastName());
            Assertions.assertEquals("Johns Adventures", result2.getAuthors().get(0).getWorks().get(0).getTitle());
        }
        catch (Exception e) {
            fail(e);
        }
    }

    private void createWorks() {
        var genreDTO1 = new GenreDTO("fiction");
        var genreDTO2 = new GenreDTO("how-to");
        var authorDTO1 = new at.htlleonding.logic.AuthorDTO("John", "Doe", LocalDate.parse("01.01.2001", dtf), LocalDate.parse("31.12.2020", dtf));
        var authorDTO2 = new at.htlleonding.logic.AuthorDTO("Mary", "Doe", LocalDate.parse("01.02.2001", dtf), LocalDate.parse("01.12.2020", dtf));
        var publisherDTO1 = new PublisherDTO("Self");
        var publisherDTO2 = new PublisherDTO("audiofan");


        var mediaPaperBook1OfAuthor1 = new BookDTO();
        var mediaAudioBook1OfAuthor1 = new AudioBookDTO();

        mediaPaperBook1OfAuthor1.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaPaperBook1OfAuthor1.setGenreDTO(genreDTO1);
        mediaPaperBook1OfAuthor1.addAuthorDTO(authorDTO1);

        mediaAudioBook1OfAuthor1.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaAudioBook1OfAuthor1.setGenreDTO(genreDTO1);
        mediaAudioBook1OfAuthor1.addAuthorDTO(authorDTO1);

        var publicationOfPaperBook1OfAuthor1 = new PublicationDTO("Johns Adventures", false);
        publicationOfPaperBook1OfAuthor1.setPublisherDTO(publisherDTO1);
        publicationOfPaperBook1OfAuthor1.setMediaDTO(mediaPaperBook1OfAuthor1);

        var publicationOfAudioBook1OfAuthor1 = new PublicationDTO("Johns Adventures", false);
        publicationOfAudioBook1OfAuthor1.setPublisherDTO(publisherDTO2);
        publicationOfAudioBook1OfAuthor1.setMediaDTO(mediaAudioBook1OfAuthor1);

        target.addPublication(publicationOfPaperBook1OfAuthor1);
        target.addPublication(publicationOfAudioBook1OfAuthor1);


        var mediaPaperBook2OfAuthor1 = new BookDTO();
        var mediaAudioBook2OfAuthor1 = new AudioBookDTO();

        mediaPaperBook2OfAuthor1.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaPaperBook2OfAuthor1.setGenreDTO(genreDTO2);
        mediaPaperBook2OfAuthor1.addAuthorDTO(authorDTO1);

        mediaAudioBook2OfAuthor1.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaAudioBook2OfAuthor1.setGenreDTO(genreDTO2);
        mediaAudioBook2OfAuthor1.addAuthorDTO(authorDTO1);

        var publicationOfPaperBook2OfAuthor1 = new PublicationDTO("Johns Tractor Guide", false);
        publicationOfPaperBook2OfAuthor1.setPublisherDTO(publisherDTO1);
        publicationOfPaperBook2OfAuthor1.setMediaDTO(mediaPaperBook2OfAuthor1);

        var publicationOfAudioBook2OfAuthor1 = new PublicationDTO("Johns Tractor Guide", false);
        publicationOfAudioBook2OfAuthor1.setPublisherDTO(publisherDTO2);
        publicationOfAudioBook2OfAuthor1.setMediaDTO(mediaAudioBook2OfAuthor1);

        target.addPublication(publicationOfPaperBook2OfAuthor1);
        target.addPublication(publicationOfAudioBook2OfAuthor1);




        var mediaPaperBook1OfAuthor2 = new BookDTO();
        var mediaAudioBook1OfAuthor2 = new AudioBookDTO();

        mediaPaperBook1OfAuthor2.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaPaperBook1OfAuthor2.setGenreDTO(genreDTO1);
        mediaPaperBook1OfAuthor2.addAuthorDTO(authorDTO2);

        mediaAudioBook1OfAuthor2.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaAudioBook1OfAuthor2.setGenreDTO(genreDTO1);
        mediaAudioBook1OfAuthor2.addAuthorDTO(authorDTO2);

        var publicationOfPaperBook1OfAuthor2 = new PublicationDTO("Mary and Johns Adventures", false);
        publicationOfPaperBook1OfAuthor2.setPublisherDTO(publisherDTO1);
        publicationOfPaperBook1OfAuthor2.setMediaDTO(mediaPaperBook1OfAuthor2);

        var publicationOfAudioBook1OfAuthor2 = new PublicationDTO("Mary and Johns Adventures", false);
        publicationOfAudioBook1OfAuthor2.setPublisherDTO(publisherDTO2);
        publicationOfAudioBook1OfAuthor2.setMediaDTO(mediaAudioBook1OfAuthor2);

        target.addPublication(publicationOfPaperBook1OfAuthor2);
        target.addPublication(publicationOfAudioBook1OfAuthor2);


        var mediaPaperBook2OfAuthor2 = new BookDTO();
        var mediaAudioBook2OfAuthor2 = new AudioBookDTO();

        mediaPaperBook2OfAuthor2.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaPaperBook2OfAuthor2.setGenreDTO(genreDTO2);
        mediaPaperBook2OfAuthor2.addAuthorDTO(authorDTO2);

        mediaAudioBook2OfAuthor2.setPublicationDate(LocalDate.parse("10.01.2015", dtf));
        mediaAudioBook2OfAuthor2.setGenreDTO(genreDTO2);
        mediaAudioBook2OfAuthor2.addAuthorDTO(authorDTO2);

        var publicationOfPaperBook2OfAuthor2 = new PublicationDTO("Marys Cooking Guide", false);
        publicationOfPaperBook2OfAuthor2.setPublisherDTO(publisherDTO1);
        publicationOfPaperBook2OfAuthor2.setMediaDTO(mediaPaperBook2OfAuthor2);

        var publicationOfAudioBook2OfAuthor2 = new PublicationDTO("Marys Cooking Guide", false);
        publicationOfAudioBook2OfAuthor2.setPublisherDTO(publisherDTO2);
        publicationOfAudioBook2OfAuthor2.setMediaDTO(mediaAudioBook2OfAuthor2);

        target.addPublication(publicationOfPaperBook2OfAuthor2);
        target.addPublication(publicationOfAudioBook2OfAuthor2);
    }

    @Test
    @TestTransaction
    public void createWorks_serializeToXml_checkWithXsd() {
        createWorks();

        target.flushAndClear();

        var publications = target.getAllPublications();
        Assertions.assertEquals(8, publications.size());

        var publication1 = publications.get(0);
        var publication2 = publications.get(1);
        var publication3 = publications.get(2);
        var publication4 = publications.get(3);
        var publication5 = publications.get(4);
        var publication6 = publications.get(5);
        var publication7 = publications.get(6);
        var publication8 = publications.get(7);

        var a1 = publication1.getMediaDTO().getAuthorDTOs().get(0);
        var a2 = publication5.getMediaDTO().getAuthorDTOs().get(0);


        var worksOfAuthors = new WorksOfAuthorsDTO();

        var author1 = new AuthorDTO(a1.getFirstName(), a1.getLastName(), a1.getDateBirth(), a1.getDateDeath());
        var work1OfAuthor1 = new AuthorWorkDTO(publication1.getTitle(), publication1.getMediaDTO().getGenre());
        work1OfAuthor1.getPublications().add(new WorkPublicationDTO(publication1.getPublisher(), publication1.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication1.getMediaDTO())));
        work1OfAuthor1.getPublications().add(new WorkPublicationDTO(publication2.getPublisher(), publication2.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication2.getMediaDTO())));

        var work2OfAuthor1 = new AuthorWorkDTO(publication3.getTitle(), publication3.getMediaDTO().getGenre());
        work2OfAuthor1.getPublications().add(new WorkPublicationDTO(publication3.getPublisher(), publication3.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication3.getMediaDTO())));
        work2OfAuthor1.getPublications().add(new WorkPublicationDTO(publication4.getPublisher(), publication4.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication4.getMediaDTO())));

        author1.getWorks().add(work1OfAuthor1);
        author1.getWorks().add(work2OfAuthor1);


        var author2 = new AuthorDTO(a2.getFirstName(), a2.getLastName(), a2.getDateBirth(), a2.getDateDeath());
        var work1OfAuthor2 = new AuthorWorkDTO(publication5.getTitle(), publication5.getMediaDTO().getGenre());
        work1OfAuthor2.getPublications().add(new WorkPublicationDTO(publication5.getPublisher(), publication5.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication5.getMediaDTO())));
        work1OfAuthor2.getPublications().add(new WorkPublicationDTO(publication6.getPublisher(), publication6.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication6.getMediaDTO())));

        var work2OfAuthor2 = new AuthorWorkDTO(publication7.getTitle(), publication7.getMediaDTO().getGenre());
        work2OfAuthor2.getPublications().add(new WorkPublicationDTO(publication7.getPublisher(), publication7.getMediaDTO().getPublicationDate(),getMediaTypeAsString(publication7.getMediaDTO())));
        work2OfAuthor2.getPublications().add(new WorkPublicationDTO(publication8.getPublisher(), publication8.getMediaDTO().getPublicationDate(), getMediaTypeAsString(publication8.getMediaDTO())));

        author2.getWorks().add(work1OfAuthor2);
        author2.getWorks().add(work2OfAuthor2);

        worksOfAuthors.getAuthors().add(author1);
        worksOfAuthors.getAuthors().add(author2);

        String xml = target.convertToXml(worksOfAuthors);


        String xsdFile = "xsd/report.xsd";
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(new Source[] {
                    new StreamSource(new File(xsdFile))
            });
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

        StringReader stringReader = new StringReader(xml);
        try {
            schema.newValidator().validate(new StreamSource(stringReader));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
        stringReader.close();
    }

    private String getMediaTypeAsString(MediaDTO mediaDTO) {
        if(mediaDTO instanceof BookDTO)
            return "PaperBook";
        else if(mediaDTO instanceof AudioBookDTO)
            return "AudioBook";
        else if(mediaDTO instanceof EBookDTO)
            return "EBook";
        else if(mediaDTO instanceof MagazineDTO)
            return "Magazine";
        else if(mediaDTO instanceof NewspaperDTO)
            return "NewsPaper";
        else if(mediaDTO instanceof ReferenceBookDTO)
            return "ReferenceBook";
        return "";
    }
}
