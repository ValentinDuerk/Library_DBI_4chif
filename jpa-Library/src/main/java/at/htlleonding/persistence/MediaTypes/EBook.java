package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("EB")
public class EBook extends Media {
    public EBook() {
        super();
    }

    public EBook(LocalDate publicationDate) {
        super(publicationDate);
    }
}
