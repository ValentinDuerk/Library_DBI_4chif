package at.htlleonding.logic;

public class MediaTypeDTO {
    private String designation;

    private Integer price;

    public MediaTypeDTO(String designation, Integer price) {
        this.designation = designation;
        this.price = price;
    }

    public MediaTypeDTO() {
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
