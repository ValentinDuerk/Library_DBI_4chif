package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("RB")
public class ReferenceBook extends Media {
    public ReferenceBook() {
    }

    public ReferenceBook(LocalDate publicationDate) {
        super(publicationDate);
    }
}
