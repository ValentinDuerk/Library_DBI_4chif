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

    public Set<AuthorDTO> getAuthorDTOs() {
        return authorDTOs;
    }

    public void setAuthorDTOs(Set<AuthorDTO> authorDTOs) {
        this.authorDTOs = authorDTOs;
    }

    public Set<TopicDTO> getTopicDTOs() {
        return topicDTOs;
    }

    public Set<String> getTopics() {
        Set<String> topics = new HashSet<>();
        for (var topicDTO : topicDTOs) {
            topics.add(topicDTO.getKeyword());
        }
        return topics;
    }

    public void setTopicDTOs(Set<TopicDTO> topicDTOs) {
        this.topicDTOs = topicDTOs;
    }

    public GenreDTO getGenreDTO() {
        return genreDTO;
    }

    public String getGenre() {
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
