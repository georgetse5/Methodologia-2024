import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class LoadSave {
    public static GameData load(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            GameData gameData = (GameData) objectIn.readObject();
            objectIn.close();
            return gameData;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game data: " + e.getMessage());
            return null;
        }
    }
}