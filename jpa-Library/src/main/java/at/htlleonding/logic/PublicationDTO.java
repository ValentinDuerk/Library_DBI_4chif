package at.htlleonding.logic;

public class PublicationDTO {
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if(publisherDTO != null)
            return publisherDTO.getName();
        else
            return null;
    }

    public void setPublisherDTO(PublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }

    public LanguageDTO getLanguageDTO() {
        return languageDTO;
    }

    public String getLanguage() {
        if(languageDTO != null)
            return languageDTO.getKeyword();
        else
            return null;
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
