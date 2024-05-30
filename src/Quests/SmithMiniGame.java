package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SmithMiniGame extends Quest {

    String requiredItem;
    private boolean itemFound;

    Colors color = new Colors();

    public SmithMiniGame (String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;
    }

    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "Examine the box" + color.reset());
        System.out.println(color.gold() + "Mr Anderson: " + color.reset() + "I heard about a hidden box in the cellar. Maybe if you bring it here we can work on that.\n");

        // Add the logic for starting the quest
        System.out.println(color.red() + "Starting the Smith Mini Game Quest!" + color.reset() + "\n");
        // Add more logic specific to this quest
    }

    public void checkItems(Player player) {
        System.out.println(requiredItem);
        ArrayList<Item> inventory = player.getInventory();
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(requiredItem)) {
                itemFound = true;
                this.complete();
//                System.out.println("Required item found: " + requiredItem);

            } else {
                System.out.println(color.red() + "You have not find the correct item" + color.reset());
            }
        }
    }

    public void completeQuest(Player player) {
            player.getInventory().remove(requiredItem);
            super.complete();
            player.addProgressPoints(20);
            startMiniGame();
    }
    public void startMiniGame() {
        System.out.println("Starting mini-game...");
        // Create and show the mini-game JFrame
        LightsOutPuzzle miniGameFrame = new LightsOutPuzzle();
        miniGameFrame.showGame();
    }

    public class LightsOutPuzzle extends JFrame {
        private JButton[][] buttons;
        private boolean[][] lights;
        private JLabel npcDialogue;

        public LightsOutPuzzle() {
            setTitle("Lights Out Puzzle");
            setSize(400, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            npcDialogue = new JLabel("<html><font color='gold'>Anderson:</font> Oh, I see the infamous 'Lights Out Puzzle'.<br>Clicking a light will toggle it and its adjacent lights.</html>");
            npcDialogue.setHorizontalAlignment(SwingConstants.CENTER);
            npcDialogue.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            npcDialogue.setForeground(Color.DARK_GRAY);
            add(npcDialogue, BorderLayout.NORTH);

            JPanel gamePanel = new JPanel(new GridLayout(3, 3));
            buttons = new JButton[3][3];
            lights = new boolean[3][3];

            initializeButtons(gamePanel);
            add(gamePanel, BorderLayout.CENTER);
            setVisible(true);
        }

        private void initializeButtons(JPanel gamePanel) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setBackground(Color.YELLOW);
                    lights[i][j] = true;
                    final int x = i;
                    final int y = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            toggleLights(x, y);
                            checkWinCondition();
                        }
                    });
                    gamePanel.add(buttons[i][j]);
                }
            }
        }

        private void toggleLights(int x, int y) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i == 0 || j == 0) && (x + i >= 0 && x + i < 3) && (y + j >= 0 && y + j < 3)) {
                        lights[x + i][y + j] = !lights[x + i][y + j];
                        buttons[x + i][y + j].setBackground(lights[x + i][y + j] ? Color.YELLOW : Color.BLACK);
                    }
                }
            }
        }

        private void checkWinCondition() {
            boolean allLightsOff = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (lights[i][j]) {
                        allLightsOff = false;
                        break;
                    }
                }
            }
            if (allLightsOff) {
                JOptionPane.showMessageDialog(this, "You win! All lights are off.");
                dispose();
                System.out.println("Quest completed: " + getName());
            }
        }

        public void showGame() {
            setVisible(true);
        }

        public void main(String[] args) {
            LightsOutPuzzle lightsOutPuzzle = new LightsOutPuzzle();
            lightsOutPuzzle.showGame();
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}