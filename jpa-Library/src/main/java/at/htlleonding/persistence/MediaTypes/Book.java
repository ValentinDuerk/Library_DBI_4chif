package at.htlleonding.persistence.MediaTypes;

import at.htlleonding.persistence.Media;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("B")
public class Book extends Media {
    public Book() {
    }

    public Book(LocalDate publicationDate) {
        super(publicationDate);
    }
}
