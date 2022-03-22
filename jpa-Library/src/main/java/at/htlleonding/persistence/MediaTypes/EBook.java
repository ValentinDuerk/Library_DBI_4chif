package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;

@Entity
@DiscriminatorValue("EB")
public class EBook extends Media {
}
