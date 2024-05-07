import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SaveGame {
    public static void save(GameData gameData, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameData);
            objectOut.close();
            System.out.println("Game data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game data: " + e.getMessage());
        }
    }
}