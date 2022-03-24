package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;

@Entity
@DiscriminatorValue("RB")
public class ReferenceBook extends Media {
}
