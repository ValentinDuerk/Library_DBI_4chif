package at.htlleonding.persistence;
import javax.persistence.*;

@Entity
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private Integer price;

    public MediaType(String designation, Integer price) {
        this.designation = designation;
        this.price = price;
    }

    public MediaType() {
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
