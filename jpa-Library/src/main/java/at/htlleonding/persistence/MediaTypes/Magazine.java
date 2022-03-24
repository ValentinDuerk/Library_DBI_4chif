package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
public class Magazine extends Media {
}
