package at.htlleonding.logic;

public class PublicationDTO {

    private String title;

    private MediaDTO mediaDTO;

    private PublisherDTO publisherDTO;

    private LanguageDTO languageDTO;

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

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public void setMediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
    }

    public PublisherDTO getPublisherDTO() {
        return publisherDTO;
    }

    public String getPublisher() {
        return publisherDTO.getName();
    }

    public void setPublisherDTO(PublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }

    public LanguageDTO getLanguageDTO() {
        return languageDTO;
    }

    public String getLanguage() {
        return languageDTO.getKeyword();
    }

    public void setLanguageDTO(LanguageDTO languageDTO) {
        this.languageDTO = languageDTO;
    }

    public boolean isTranslation() {
        return isTranslation;
    }

    public void setTranslation(boolean translation) {
        isTranslation = translation;
    }
}
