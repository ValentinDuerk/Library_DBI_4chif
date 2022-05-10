package at.htlleonding.logic;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO extends PersonDTO {
    private double salary;

    private Set<SaleDTO> saleDTOs = new HashSet<>();

    public EmployeeDTO(String firstName, String lastName, String eMail, String telNumber, double salary) {
        super(firstName, lastName, eMail, telNumber);
        this.salary = salary;
    }

    public EmployeeDTO() {
    }

    public Set<SaleDTO> getSaleDTOs() {
        return saleDTOs;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
