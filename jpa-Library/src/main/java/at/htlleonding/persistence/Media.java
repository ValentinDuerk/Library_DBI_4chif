package at.htlleonding.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MEDIA_TYPE")
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany()
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany()
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    private Genre genre;

    @Column
    private LocalDate publicationDate;

    @OneToMany(mappedBy = "media")
    private Set<Publication> publications = new HashSet<>();

    public Media() {
    }

    public Media(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return id;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Set<Publication> getPublications() {
        return publications;
    }
}