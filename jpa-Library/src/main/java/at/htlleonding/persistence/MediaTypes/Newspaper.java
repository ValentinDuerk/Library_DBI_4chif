package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("N")
public class Newspaper extends Media {
    public Newspaper() {
    }

    public Newspaper(LocalDate publicationDate) {
        super(publicationDate);
    }
}
