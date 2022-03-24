package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "bookShelfId")
    private BookShelf bookShelf;

    @ManyToOne
    @JoinColumn(name = "publicationId")
    private Publication publication;

    @OneToOne(mappedBy = "specimen")
    private SalePosition salePosition;

    @Enumerated(EnumType.STRING)
    private SpecimenState specimenState;

    public Specimen() {
    }

    public Specimen(Date purchaseDate, SpecimenState specimenState) {
        this.purchaseDate = purchaseDate;
        this.specimenState = specimenState;
    }

    public Integer getId() {
        return id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BookShelf getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Publication getPublication() {
        return publication;
    }

    public SpecimenState getSpecimenState() {
        return specimenState;
    }

    public void setSpecimenState(SpecimenState specimenState) {
        this.specimenState = specimenState;
    }

    public SalePosition getSalePosition() {
        return salePosition;
    }

    public void setSalePosition(SalePosition salePosition) {
        this.salePosition = salePosition;
    }
}
