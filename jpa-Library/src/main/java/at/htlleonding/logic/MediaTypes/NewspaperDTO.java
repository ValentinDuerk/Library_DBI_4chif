package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class NewspaperDTO extends MediaDTO {
    public NewspaperDTO() {
        super();
    }

    public NewspaperDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
