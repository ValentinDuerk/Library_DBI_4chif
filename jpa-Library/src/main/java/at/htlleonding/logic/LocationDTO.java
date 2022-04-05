package at.htlleonding.logic;

public class LocationDTO {
    private String name;

    public LocationDTO(String name) {
        this.name = name;
    }

    public LocationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
