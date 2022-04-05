package at.htlleonding.logic;

public class CustomerDTO extends PersonDTO {
    private boolean isEmployee;

    public CustomerDTO(String firstName, String lastName, String eMail, String telNumber, boolean isEmployee) {
        super(firstName, lastName, eMail, telNumber);
        this.isEmployee = isEmployee;
    }

    public CustomerDTO() {
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }
}
