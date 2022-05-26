package at.htlleonding.logic;

import at.htlleonding.persistence.SpecimenState;

import java.time.LocalDate;
import java.util.List;

public class SpecimenDTO {
    private Integer id;

    private SpecimenState specimenState;

    private LocalDate purchaseDate;

    private PublicationDTO publicationDTO;

    public SpecimenDTO(SpecimenState currentState, LocalDate purchase) {
        this.specimenState = currentState;
        this.purchaseDate = purchase;
    }

    public SpecimenDTO(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpecimenState getSpecimenState() {
        return specimenState;
    }

    public void setSpecimenState(SpecimenState specimenState) {
        this.specimenState = specimenState;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public PublicationDTO getPublicationDTO() {
        return publicationDTO;
    }

    public void setPublicationDTO(PublicationDTO publicationDTO) {
        this.publicationDTO = publicationDTO;
    }

    public String getTitle() {
        if(publicationDTO != null)
            return publicationDTO.getTitle();
        else
            return null;
    }

    public String getLanguage() {
        if(publicationDTO != null)
            return publicationDTO.getLanguage();
        else
            return null;    }

    public String getPublisher() {
        if(publicationDTO != null)
            return publicationDTO.getPublisher();
        else
            return null;    }

    public List<String> getAuthorsLastNames() {
        if(publicationDTO != null && publicationDTO.getMediaDTO() != null)
            return publicationDTO.getMediaDTO().getAuthorLastNames();
        else
            return null;
    }
}
