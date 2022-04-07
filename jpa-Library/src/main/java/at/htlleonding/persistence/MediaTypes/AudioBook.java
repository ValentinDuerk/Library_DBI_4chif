package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("AB")
public class AudioBook extends Media {
    public AudioBook() {
        super();
    }

    public AudioBook(LocalDate publicationDate) {
        super(publicationDate);
    }
}
