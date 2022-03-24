package at.htlleonding.persistence;

import at.htlleonding.persistence.Persons.Customer;

import javax.persistence.*;
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
    private Date lendOutDate;

    @Column
    private Date ReturnDate;

    @Column
    private Integer extensions;

    public LendOut() {
    }

    public LendOut(Date lendOutDate, Date returnDate, Integer extensions) {
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

    public Date getLendOutDate() {
        return lendOutDate;
    }

    public void setLendOutDate(Date lendOutDate) {
        this.lendOutDate = lendOutDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }

    public Integer getExtensions() {
        return extensions;
    }

    public void setExtensions(Integer extensions) {
        this.extensions = extensions;
    }
}
