package at.htlleonding.logic;

import at.htlleonding.persistence.SpecimenState;

import java.time.LocalDate;

public class SpecimenDTO {
    private SpecimenState currentState;

    private LocalDate purchase;

    private PublicationDTO publicationDTO;

    private SalePositionDTO salePositionDTO;

    private LendOutDTO lendOutDTO;

    public SpecimenDTO(SpecimenState currentState, LocalDate purchase) {
        this.currentState = currentState;
        this.purchase = purchase;
    }

    public SpecimenDTO(){

    }

    public SalePositionDTO getSalePositionDTO() {
        return salePositionDTO;
    }

    public void setSalePositionDTO(SalePositionDTO salePositionDTO) {
        this.salePositionDTO = salePositionDTO;
    }

    public LendOutDTO getLendOutDTO() {
        return lendOutDTO;
    }

    public void setLendOutDTO(LendOutDTO lendOutDTO) {
        this.lendOutDTO = lendOutDTO;
    }

    public SpecimenState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SpecimenState currentState) {
        this.currentState = currentState;
    }

    public LocalDate getPurchase() {
        return purchase;
    }

    public void setPurchase(LocalDate purchase) {
        this.purchase = purchase;
    }

    public PublicationDTO getPublicationDTO() {
        return publicationDTO;
    }

    public void setPublicationDTO(PublicationDTO publicationDTO) {
        this.publicationDTO = publicationDTO;
    }
}
