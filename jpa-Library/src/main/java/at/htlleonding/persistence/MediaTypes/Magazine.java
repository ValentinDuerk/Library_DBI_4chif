package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("M")
public class Magazine extends Media {
    public Magazine() {
        super();
    }

    public Magazine(LocalDate publicationDate) {
        super(publicationDate);
    }
}
