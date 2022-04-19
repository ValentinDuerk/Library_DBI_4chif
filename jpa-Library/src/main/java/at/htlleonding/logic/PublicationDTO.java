package at.htlleonding.logic;

public class PublicationDTO {

    private String title;

    private boolean isTranslation;

    public PublicationDTO() {

    }

    public PublicationDTO(String title, boolean isTranslation) {
        this.title = title;
        this.isTranslation = isTranslation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTranslation() {
        return isTranslation;
    }

    public void setTranslation(boolean translation) {
        isTranslation = translation;
    }
}
