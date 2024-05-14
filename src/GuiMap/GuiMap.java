package GuiMap;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


public class GuiMap extends JFrame {
    private static boolean instanceCreated;

    public GuiMap() {
        setTitle("Game Map");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL imageUrl = new URL("https://imgtr.ee/images/2024/05/01/16f2bfcaf0d349e8500e61b6091da47b.jpeg");

                    ImageIcon mapImage = new ImageIcon(imageUrl);
                    Image image = mapImage.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);


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
