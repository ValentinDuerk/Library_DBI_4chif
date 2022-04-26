package at.htlleonding.logic;

import java.time.LocalDate;

public class ReservationDTO {
    private boolean isStillReserved;

    private LocalDate reservationDate;

    public ReservationDTO() {
    }

    public ReservationDTO(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        isStillReserved = true;
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