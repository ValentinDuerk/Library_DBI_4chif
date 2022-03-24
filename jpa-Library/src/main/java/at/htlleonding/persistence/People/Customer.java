package at.htlleonding.persistence.People;

import at.htlleonding.persistence.LendOut;
import at.htlleonding.persistence.Reservation;
import at.htlleonding.persistence.Sale;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends Person {
    @Column
    private boolean isEmployee;

    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<LendOut> lendOuts = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales = new HashSet<>();

    public Customer(String firstName, String lastName, String eMail, String telNumber, boolean isEmployee) {
        super(firstName, lastName, eMail, telNumber);
        this.isEmployee = isEmployee;
    }

    public Customer() {
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Set<LendOut> getLendOuts() {
        return lendOuts;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
