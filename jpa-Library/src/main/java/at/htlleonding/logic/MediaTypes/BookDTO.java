package at.htlleonding.logic.MediaTypes;

import at.htlleonding.logic.MediaDTO;

import java.time.LocalDate;


public class BookDTO extends MediaDTO {
    public BookDTO() {
        super();
    }

    public BookDTO(LocalDate publicationDate) {
        super(publicationDate);
    }
}
