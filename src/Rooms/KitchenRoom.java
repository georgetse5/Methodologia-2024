package Rooms;

public class KitchenRoom extends Room {


    public KitchenRoom(String name, String description, int xCoordinate, int yCoordinate) {
        super(name, description, xCoordinate,yCoordinate);
    }

    public void testRoom() {
        System.out.println("This is the test room message");
    }

    public void AnotherTestMethod() {
        System.out.println("(HallOfFameMessage): This is the other test room message");
    }

}