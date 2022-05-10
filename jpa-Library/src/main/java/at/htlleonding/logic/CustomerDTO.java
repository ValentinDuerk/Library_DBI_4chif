package at.htlleonding.logic;

import java.util.HashSet;
import java.util.Set;

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
