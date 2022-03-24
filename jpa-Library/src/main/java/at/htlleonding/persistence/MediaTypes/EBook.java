package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("EB")
public class EBook extends Media {
    public EBook() {
    }

    public EBook(Date publicationDate) {
        super(publicationDate);
    }
}
