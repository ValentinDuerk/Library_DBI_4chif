package at.htlleonding.persistence.Persons;

import at.htlleonding.persistence.Person;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Person {
    @Column
    private double salary;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String eMail, String telNumber, double salary) {
        super(firstName, lastName, eMail, telNumber);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
