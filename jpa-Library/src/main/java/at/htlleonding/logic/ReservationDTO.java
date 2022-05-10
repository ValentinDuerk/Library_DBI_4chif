package at.htlleonding.logic;

import java.time.LocalDate;

public class ReservationDTO {
    private boolean isStillReserved;

    private LocalDate reservationDate;

    private CustomerDTO customerDTO;

    private PublicationDTO publicationDTO;

    public ReservationDTO() {
    }

    public ReservationDTO(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        isStillReserved = true;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public PublicationDTO getPublicationDTO() {
        return publicationDTO;
    }

    public void setPublicationDTO(PublicationDTO publicationDTO) {
        this.publicationDTO = publicationDTO;
    }

    public boolean isStillReserved() {
        return isStillReserved;
    }

    public void setStillReserved(boolean stillReserved) {
        isStillReserved = stillReserved;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}