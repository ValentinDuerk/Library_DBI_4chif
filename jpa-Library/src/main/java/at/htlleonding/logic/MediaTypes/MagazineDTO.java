package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class MagazineDTO extends MediaDTO {
    public MagazineDTO() {
        super();
    }

    public MagazineDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
