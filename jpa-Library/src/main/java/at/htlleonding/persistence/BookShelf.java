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
    @JoinColumn(name = "roomId")
    private Room room;

    @OneToMany(mappedBy = "bookShelf")
    private Set<Specimen> specimen = new HashSet<Specimen>();

    public BookShelf() {
    }

    public Integer getId() {
        return id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public Set<Specimen> getSpecimen() {
        return specimen;
    }
}
