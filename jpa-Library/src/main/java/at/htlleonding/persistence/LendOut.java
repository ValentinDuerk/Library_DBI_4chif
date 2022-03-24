package at.htlleonding.persistence;

import at.htlleonding.persistence.Persons.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class LendOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "specimenId")
    private Specimen specimen;

    @Column
    private LocalDate lendOutDate;

    @Column
    private LocalDate ReturnDate;

    @Column
    private Integer extensions;

    public LendOut() {
    }

    public LendOut(LocalDate lendOutDate, LocalDate returnDate, Integer extensions) {
        this.lendOutDate = lendOutDate;
        ReturnDate = returnDate;
        this.extensions = extensions;
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public LocalDate getLendOutDate() {
        return lendOutDate;
    }

    public void setLendOutDate(LocalDate lendOutDate) {
        this.lendOutDate = lendOutDate;
    }

    public LocalDate getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        ReturnDate = returnDate;
    }

    public Integer getExtensions() {
        return extensions;
    }

    public void setExtensions(Integer extensions) {
        this.extensions = extensions;
    }
}
