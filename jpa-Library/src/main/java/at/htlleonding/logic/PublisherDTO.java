package at.htlleonding.logic;

public class PublisherDTO {
    private String name;

    public PublisherDTO(String name) {
        this.name = name;
    }

    public PublisherDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
