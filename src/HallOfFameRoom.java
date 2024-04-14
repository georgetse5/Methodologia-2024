
public class HallOfFameRoom extends Room {
    boolean hasOven;
    boolean isDoorToLivingRoomLocked;


    public HallOfFameRoom(String name, String description, boolean hasOven, boolean isDoorToLivingRoomLocked) {
        super(name, description);
        this.hasOven = hasOven;
        this.isDoorToLivingRoomLocked = isDoorToLivingRoomLocked;
    }


    public void toggleDoorToMainRoom() {
        isDoorToLivingRoomLocked = !isDoorToLivingRoomLocked;
    }


    public boolean isDoorToMainRoomLocked() {
        return isDoorToLivingRoomLocked;
    }
}

// This is a testing class for a Random Room

