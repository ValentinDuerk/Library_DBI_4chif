package at.htlleonding.logic;

public class TopicDTO {
    private Integer id;

    private String keyword;

    public TopicDTO(String keyword) {
        this.keyword = keyword;
    }

    public TopicDTO() {
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
