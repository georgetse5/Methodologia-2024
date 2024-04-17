package Rooms;

public class KitchenRoom extends Room {


    public KitchenRoom(String name, String description) {
        super(name, description);
    }

    public void testRoom() {
        System.out.println("This is the test room message");
    }

    public void AnotherTestMethod() {
        System.out.println("(HallOfFameMessage): This is the other test room message");
    }

}