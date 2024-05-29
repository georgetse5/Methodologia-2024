package Rooms;

public class StartingRoom extends Room {


    public StartingRoom(String name, String description, int xCoordinate, int yCoordinate) {
        super(name, description,xCoordinate, yCoordinate);
    }

    public void startingRoomMessage() {
        System.out.println("(startingRoomMessage): This is not the game prologue but is a also a starting message");
    }

}

