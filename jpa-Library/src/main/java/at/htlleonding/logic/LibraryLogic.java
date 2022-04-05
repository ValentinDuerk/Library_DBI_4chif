package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    LibraryRepository libraryRepository;

    ModelMapper mapper = new ModelMapper();

    public void flushAndClear() {
        libraryRepository.flushAndClear();
    }

    public void addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        mapper.map(authorDTO, author);
        libraryRepository.add(author);
    }

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

    public AuthorDTO getAuthor(String firstName, String lastName) {
        AuthorDTO authorDTO = new AuthorDTO();
        mapper.map(libraryRepository.getAuthorByFirstNameAndLastName(firstName, lastName), authorDTO);
        return authorDTO;
    }
}
