package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BookShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "bookshelf")
    private Set<Specimen> specimen = new HashSet<Specimen>();
}
