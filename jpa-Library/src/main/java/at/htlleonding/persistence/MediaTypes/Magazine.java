package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("M")
public class Magazine extends Media {
    public Magazine() {
    }

    public Magazine(LocalDate publicationDate) {
        super(publicationDate);
    }
}
