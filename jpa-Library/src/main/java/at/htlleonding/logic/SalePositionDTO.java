package at.htlleonding.logic;

public class SalePositionDTO {
    private Integer prize;

    public SalePositionDTO() {
    }

    public SalePositionDTO(Integer prize) {
        this.prize = prize;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }
}
