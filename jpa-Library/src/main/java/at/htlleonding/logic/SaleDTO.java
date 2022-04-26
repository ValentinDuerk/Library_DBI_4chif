package at.htlleonding.logic;

import java.time.LocalDate;

public class SaleDTO {
    private LocalDate saleDate;

    public SaleDTO() {
    }

    public SaleDTO(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
