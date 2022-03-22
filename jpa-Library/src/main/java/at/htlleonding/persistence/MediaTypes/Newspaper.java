package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;

@Entity
@DiscriminatorValue("N")
public class Newspaper extends Media {
}
