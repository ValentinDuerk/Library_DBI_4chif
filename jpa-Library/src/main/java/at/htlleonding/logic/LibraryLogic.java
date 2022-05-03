package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import com.arjuna.ats.jta.exceptions.NotImplementedException;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    LibraryRepository libraryRepository;

    ModelMapper mapper = new ModelMapper();

    @Transactional
    public void flushAndClear() {
        libraryRepository.flushAndClear();
    }

    @Transactional
    public void addAuthor(AuthorDTO authorDTO) {
        Author author = libraryRepository.getAuthorByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());
        if(author == null) {
            author = new Author();
            mapper.map(authorDTO, author);
            libraryRepository.add(author);
        }
    }

    @Transactional
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = libraryRepository.getAllAuthors();
        List<AuthorDTO> authorDTOs = new ArrayList<>();

        for (var author : authors) {
            AuthorDTO authorDTO = new AuthorDTO();
            mapper.map(author, authorDTO);
            authorDTOs.add(authorDTO);
        }
        return authorDTOs;
    }

    @Transactional
    public AuthorDTO getAuthor(String firstName, String lastName) {
        AuthorDTO authorDTO = new AuthorDTO();
        mapper.map(libraryRepository.getAuthorByFirstNameAndLastName(firstName, lastName), authorDTO);
        return authorDTO;
    }

    @Transactional
    public void AddMedia(LocalDate publicationDate, GenreDTO genreDTO, TopicDTO[] topicDTOs, AuthorDTO[] authorDTOs) {
        if(genreDTO == null || topicDTOs.length == 0 || authorDTOs.length == 0)
            return;

        addGenre(genreDTO);
        Genre genre = libraryRepository.getGenre(genreDTO.getKeyword());

        Topic[] topics = new Topic[topicDTOs.length];
        for (int i = 0; i < topicDTOs.length;i++) {
            addTopic(topicDTOs[i]);
            topics[i] = libraryRepository.getTopic(topicDTOs[i].getKeyword());
        }

        Author[] authors = new Author[authorDTOs.length];
        for (int i = 0; i < authorDTOs.length;i++) {
            addAuthor(authorDTOs[i]);
            authors[i] = libraryRepository.getAuthorByFirstNameAndLastName(authorDTOs[i].getFirstName(), authorDTOs[i].getLastName());
        }

        Media media = libraryRepository.getMediaByGenreTopicsAuthorsAndPublicationDate(genre.getId(),
                Arrays.stream(topics).mapToInt(x -> x.getId()).toArray(),
                Arrays.stream(authors).mapToInt(x -> x.getId()).toArray(), publicationDate);

        if(media == null) {
            media = new Media(publicationDate);

            libraryRepository.add(media, genre);

            for (var topic : topics) {
                libraryRepository.add(media, topic);
            }

            for (var author : authors) {
                libraryRepository.add(media, author);
            }
        }
    }

    private void addGenre(GenreDTO genreDTO) {
        throw new NotImplementedException();
    }

    private void addTopic(TopicDTO topicDTO) {
        throw new NotImplementedException();
    }

}
