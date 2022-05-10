package at.htlleonding.logic;

public class SalePositionDTO {
    private Integer prize;

    private SpecimenDTO specimenDTO;

    private SaleDTO saleDTO;

    public SalePositionDTO() {
    }

    public SalePositionDTO(Integer prize) {
        this.prize = prize;
    }

    public SaleDTO getSaleDTO() {
        return saleDTO;
    }

    public void setSaleDTO(SaleDTO saleDTO) {
        this.saleDTO = saleDTO;
    }

    public SpecimenDTO getSpecimenDTO() {
        return specimenDTO;
    }

    public void setSpecimenDTO(SpecimenDTO specimenDTO) {
        this.specimenDTO = specimenDTO;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }
}
