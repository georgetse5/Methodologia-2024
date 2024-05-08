package GuiMap;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GuiMap extends JFrame {
    private static boolean instanceCreated;

    public GuiMap() {
        setTitle("Game Map");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Χρησιμοποιήστε DISPOSE_ON_CLOSE αντί για EXIT_ON_CLOSE
        setSize(900, 800);
        setLocationRelativeTo(null);

        // Δημιουργία του JPanel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL imageUrl = new URL("https://imgtr.ee/images/2024/05/01/16f2bfcaf0d349e8500e61b6091da47b.jpeg");

                    ImageIcon mapImage = new ImageIcon(imageUrl); // Φορτώστε την εικόνα
                    Image image = mapImage.getImage(); // Πάρτε την εικόνα από το ImageIcon
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Σχεδίαση της εικόνας στο JPanel


//                System.out.println("Image loaded: " + mapImage.getImageLoadStatus());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        };


        add(panel);
    }

    public void showMap() {
        setVisible(true);
    }

    public static boolean isInstanceCreated() {
        return instanceCreated;
    }
}
