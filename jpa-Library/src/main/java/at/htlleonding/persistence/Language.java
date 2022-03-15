package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String keyWord;

    @OneToMany(mappedBy = "language")
    private List<Publication> publications = new ArrayList<Publication>();

    public Language() {

    }

    public Language(Integer id, String keyWord, List<Publication> publications) {
        this.id = id;
        this.keyWord = keyWord;
        this.publications = publications;
    }

    public Integer getId() {
        return id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}