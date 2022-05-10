package at.htlleonding.logic;

import java.util.HashSet;
import java.util.Set;

public class CustomerDTO extends PersonDTO {
    private boolean isEmployee;

    private Set<SaleDTO> saleDTOs = new HashSet<>();

    private Set<LendOutDTO> lendOutDTOs = new HashSet<>();

    private Set<ReservationDTO> reservationDTOs = new HashSet<>();

    public CustomerDTO(String firstName, String lastName, String eMail, String telNumber, boolean isEmployee) {
        super(firstName, lastName, eMail, telNumber);
        this.isEmployee = isEmployee;
    }

    public CustomerDTO() {
    }

    public Set<SaleDTO> getSaleDTOs() {
        return saleDTOs;
    }

    public Set<LendOutDTO> getLendOutDTOs() {
        return lendOutDTOs;
    }

    public Set<ReservationDTO> getReservationDTOs() {
        return reservationDTOs;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }
}
