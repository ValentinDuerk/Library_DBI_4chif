package at.htlleonding.logic;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MediaDTO {

    private Set<AuthorDTO> authorDTOs = new HashSet<>();

    private Set<TopicDTO> topicDTOs = new HashSet<>();

    private GenreDTO genreDTO;

    private LocalDate publicationDate;

    public MediaDTO() {
    }

    public MediaDTO(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public GenreDTO getGenreDTO() {
        return genreDTO;
    }

    public String getGenreKeyword() {
        return genreDTO.getKeyword();
    }

    public void setGenreDTO(GenreDTO genreDTO) {
        this.genreDTO = genreDTO;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
