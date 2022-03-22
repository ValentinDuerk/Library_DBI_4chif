package at.htlleonding.persistence;

import javax.persistence.*;

@Entity
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String purchaseDate;

    @ManyToOne
    @JoinColumn(name = "bookShelfId")
    private BookShelf bookShelf;

    @ManyToOne
    @JoinColumn(name = "publicationId")
    private Publication publication;

    // specimen state enum
}
