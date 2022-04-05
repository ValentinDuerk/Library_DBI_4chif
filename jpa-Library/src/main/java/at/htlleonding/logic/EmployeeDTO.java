package at.htlleonding.logic;

public class EmployeeDTO extends PersonDTO {
    private double salary;

    public EmployeeDTO(String firstName, String lastName, String eMail, String telNumber, double salary) {
        super(firstName, lastName, eMail, telNumber);
        this.salary = salary;
    }

    public EmployeeDTO() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
