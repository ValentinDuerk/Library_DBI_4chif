package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String keyWord;

    @OneToMany(mappedBy = "language")
    private Set<Publication> publications = new HashSet<>();

    public Language() {

    }

    public Language(Integer id, String keyWord, Set<Publication> publications) {
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

    public Set<Publication> getPublications() {
        return publications;
    }
}