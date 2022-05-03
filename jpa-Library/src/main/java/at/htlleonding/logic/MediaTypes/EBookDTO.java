package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class EBookDTO extends MediaDTO {
    public EBookDTO() {
        super();
    }

    public EBookDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
