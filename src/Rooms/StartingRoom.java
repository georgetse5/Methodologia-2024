package Rooms;

public class StartingRoom extends Room {


    public StartingRoom(String name, String description) {
        super(name, description);
    }

    public void startingRoomMessage() {
        System.out.println("This is not the game prologue but is a also a starting message");
    }

}

