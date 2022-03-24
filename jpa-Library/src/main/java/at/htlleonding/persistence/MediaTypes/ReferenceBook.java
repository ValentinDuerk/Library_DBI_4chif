package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("RB")
public class ReferenceBook extends Media {
    public ReferenceBook() {
    }

    public ReferenceBook(LocalDate publicationDate) {
        super(publicationDate);
    }
}
