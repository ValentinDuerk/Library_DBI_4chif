package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class ReferenceBookDTO extends MediaDTO {
    public ReferenceBookDTO() {
        super();
    }

    public ReferenceBookDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
