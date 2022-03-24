package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("EB")
public class EBook extends Media {
    public EBook() {
    }

    public EBook(LocalDate publicationDate) {
        super(publicationDate);
    }
}
