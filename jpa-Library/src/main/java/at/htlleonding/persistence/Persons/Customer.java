package at.htlleonding.persistence.Persons;

import at.htlleonding.persistence.Person;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends Person {
    @Column
    private boolean isEmployee;

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
}
