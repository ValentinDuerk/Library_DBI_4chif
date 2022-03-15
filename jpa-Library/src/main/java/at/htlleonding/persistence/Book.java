package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @ManyToMany()
    private Set<Topic> topics = new HashSet<>();

    @ManyToOne
    private Genre genre;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setGenre(Genre g) {
        genre = g;
    }

    public Genre getGenre() {
        return genre;
    }
}
