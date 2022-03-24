package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "location")
    private Set<Person> persons = new HashSet<>();

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
