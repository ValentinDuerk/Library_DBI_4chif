package at.htlleonding.logic;

public class LanguageDTO {
    private String keyword;

    public LanguageDTO(String keyword) {
        this.keyword = keyword;
    }

    public LanguageDTO() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
