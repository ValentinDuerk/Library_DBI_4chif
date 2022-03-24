package at.htlleonding.persistence.MediaTypes;
import at.htlleonding.persistence.Media;
import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
public class Book extends Media {

}
