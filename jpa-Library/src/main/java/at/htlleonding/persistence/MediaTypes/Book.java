package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("B")
public class Book extends Media {
    public Book() {
    }

    public Book(LocalDate publicationDate) {
        super(publicationDate);
    }
}
