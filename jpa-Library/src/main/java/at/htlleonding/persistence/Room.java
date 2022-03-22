package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer number;

    @Column
    private Integer floor;

    @OneToMany(mappedBy = "room")
    private Set<BookShelf> bookShelves = new HashSet<BookShelf>();
}
