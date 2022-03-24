package at.htlleonding.persistence;

import at.htlleonding.persistence.People.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "publicationId")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column
    private boolean isStillReserved;

    @Column
    private LocalDate reservationDate;

    public Reservation() {

    }

    public Reservation(boolean isStillReserved, LocalDate reservationDate) {
        this.isStillReserved = isStillReserved;
        this.reservationDate = reservationDate;
    }

    public Integer getId() {
        return id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isStillReserved() {
        return isStillReserved;
    }

    public void setStillReserved(boolean stillReserved) {
        isStillReserved = stillReserved;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
