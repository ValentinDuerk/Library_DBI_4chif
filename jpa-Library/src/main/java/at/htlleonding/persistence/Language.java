package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String keyword;

    @OneToMany(mappedBy = "language")
    private Set<Publication> publications = new HashSet<>();

    public Language() {

    }

    public Language(String keyWord) {
        this.keyword = keyWord;
    }

    public Integer getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<Publication> getPublications() {
        return publications;
    }
}