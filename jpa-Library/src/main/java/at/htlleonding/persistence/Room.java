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
    private Integer roomNumber;

    @Column
    private Integer floor;

    @OneToMany(mappedBy = "room")
    private Set<BookShelf> bookShelves = new HashSet<BookShelf>();

    public Room() {
    }

    public Room(Integer roomNumber, Integer floor) {
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Set<BookShelf> getBookShelves() {
        return bookShelves;
    }
}
