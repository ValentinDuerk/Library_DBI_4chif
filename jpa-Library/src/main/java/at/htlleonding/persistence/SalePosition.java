package at.htlleonding.persistence;

import javax.persistence.*;

@Entity
public class SalePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer prize;

    @OneToOne
    @JoinColumn(name = "specimenId")
    private Specimen specimen;

    @ManyToOne
    @JoinColumn(name = "saleId")
    private Sale sale;

    public SalePosition() {
    }

    public SalePosition(Integer prize) {
        this.prize = prize;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
