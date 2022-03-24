package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("RB")
public class ReferenceBook extends Media {
    public ReferenceBook() {
    }

    public ReferenceBook(Date publicationDate) {
        super(publicationDate);
    }
}
