package at.htlleonding.logic;

import java.time.LocalDate;

public class MediaDTO {
    private LocalDate publicationDate;

    public MediaDTO() {
    }

    public MediaDTO(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
