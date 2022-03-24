package at.htlleonding.persistence;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateBirth;

    @ManyToMany
    private Set<Media> media = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }
    public Integer getId() {
        return id;
    }

    public Author() {
    }

    public Author(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dob;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() { return id + " " + getFullName(); }
}
