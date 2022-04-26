package at.htlleonding.logic;

public class RoomDTO {
    private Integer roomNumber;

    private Integer floor;

    public RoomDTO() {
    }

    public RoomDTO(Integer roomNumber, Integer floor) {
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
