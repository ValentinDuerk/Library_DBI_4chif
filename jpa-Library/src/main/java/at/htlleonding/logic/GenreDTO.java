package at.htlleonding.logic;

public class GenreDTO {
    private Integer id;

    private String keyword;

    public GenreDTO(String keyword) {
        this.keyword = keyword;
    }

    public GenreDTO() {
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
