package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
