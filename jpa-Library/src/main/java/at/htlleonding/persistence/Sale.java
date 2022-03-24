package at.htlleonding.persistence;

import at.htlleonding.persistence.Persons.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "sale")
    private Set<SalePosition> salePositions = new HashSet<>();

    @Column
    private LocalDate saleDate;

    @ManyToOne
    @JoinColumn(name = "saleEmployeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer costumer;

    public Sale() {

    }

    public Sale(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getId() {
        return id;
    }
    public Set<SalePosition> getSalePositions() {
        return salePositions;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCostumer() {
        return costumer;
    }

    public void setCostumer(Customer costumer) {
        this.costumer = costumer;
    }
}
