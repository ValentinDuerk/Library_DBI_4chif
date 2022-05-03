package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class AudioBookDTO extends MediaDTO {
    public AudioBookDTO() {
        super();
    }

    public AudioBookDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
