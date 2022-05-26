package at.htlleonding.persistence;

import at.htlleonding.persistence.People.Customer;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate returnDate;

    @Column
    private boolean stillLendOut;

    @Column
    private Integer extensions;

    public LendOut() {
    }

    public LendOut(LocalDate lendOutDate, LocalDate returnDate) {
        this.lendOutDate = lendOutDate;
        this.returnDate = returnDate;
    }

    public LendOut(LocalDate lendOutDate) {
        this.lendOutDate = lendOutDate;
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
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getExtensions() {
        return extensions;
    }

    public void setExtensions(Integer extensions) {
        this.extensions = extensions;
    }

    public boolean isStillLendOut() {
        return stillLendOut;
    }

    public void setStillLendOut(boolean stillLendOut) {
        this.stillLendOut = stillLendOut;
    }
}
