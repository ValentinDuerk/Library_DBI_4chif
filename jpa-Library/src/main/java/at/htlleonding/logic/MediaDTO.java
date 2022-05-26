package at.htlleonding.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediaDTO {
    private Integer id;

    private List<AuthorDTO> authorDTOs = new ArrayList<>();

    private List<TopicDTO> topicDTOs = new ArrayList<>();

    private GenreDTO genreDTO;

    private LocalDate publicationDate;

    public MediaDTO() {
    }

    public MediaDTO(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AuthorDTO> getAuthorDTOs() {
        return authorDTOs;
    }

    public List<String> getAuthorLastNames() {
        List<String> authors = new ArrayList<>();
        for (var authorDTO : authorDTOs) {
            authors.add(authorDTO.getLastName());
        }
        return authors;
    }

    public void setAuthorDTOs(List<AuthorDTO> authorDTOs) {
        this.authorDTOs = authorDTOs;
    }

    public void addAuthorDTO(AuthorDTO authorDTO) {
        authorDTOs.add(authorDTO);
    }

    public List<TopicDTO> getTopicDTOs() {
        return topicDTOs;
    }

    public void addTopicDTO(TopicDTO topicDTO) {
        topicDTOs.add(topicDTO);
    }


    public List<String> getTopics() {
        List<String> topics = new ArrayList<>();
        for (var topicDTO : topicDTOs) {
            topics.add(topicDTO.getKeyword());
        }
        return topics;
    }

    public void setTopicDTOs(List<TopicDTO> topicDTOs) {
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
