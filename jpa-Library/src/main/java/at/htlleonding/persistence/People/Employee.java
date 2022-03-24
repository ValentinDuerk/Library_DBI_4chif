package at.htlleonding.persistence.People;

import at.htlleonding.persistence.Sale;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Person {
    @Column
    private double salary;

    @OneToMany(mappedBy = "employee")
    private Set<Sale> sales = new HashSet<>();

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

    public Set<Sale> getSales() {
        return sales;
    }
}
