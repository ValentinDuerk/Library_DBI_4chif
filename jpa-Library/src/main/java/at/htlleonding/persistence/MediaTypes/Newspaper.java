package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("N")
public class Newspaper extends Media {
    public Newspaper() {
        super();
    }

    public Newspaper(LocalDate publicationDate) {
        super(publicationDate);
    }
}
