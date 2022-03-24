package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("M")
public class Magazine extends Media {
    public Magazine() {
    }

    public Magazine(Date publicationDate) {
        super(publicationDate);
    }
}
