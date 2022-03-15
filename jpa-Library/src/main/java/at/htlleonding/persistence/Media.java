package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.Date;
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
    private Date publicationDate;

    @OneToMany
    private Set<Publication> publication = new HashSet<>();

    public Media() {
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
