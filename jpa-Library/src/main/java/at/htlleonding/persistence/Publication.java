package at.htlleonding.persistence;

import javax.persistence.*;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "publicationId")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "languageId")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "mediaId")
    private Media media;

    @Column(nullable = false)
    private boolean isTranslation;
}