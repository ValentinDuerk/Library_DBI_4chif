package at.htlleonding.logic;

public class PublisherDTO {
    private Integer id;

    private String name;

    public PublisherDTO(String name) {
        this.name = name;
    }

    public PublisherDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
