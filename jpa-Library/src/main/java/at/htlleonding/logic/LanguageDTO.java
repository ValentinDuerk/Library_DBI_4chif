package at.htlleonding.logic;

public class LanguageDTO {
    private Integer id;

    private String keyword;

    public LanguageDTO(String keyword) {
        this.keyword = keyword;
    }

    public LanguageDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
