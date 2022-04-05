package at.htlleonding.logic;

public class GenreDTO {
    private String keyword;

    public GenreDTO(String keyword) {
        this.keyword = keyword;
    }

    public GenreDTO() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
