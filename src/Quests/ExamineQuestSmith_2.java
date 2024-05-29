package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestSmith_2 extends Quest {

    String requiredItem;
    private boolean itemFound;
    private int turnsLeft = 15;
    private int questStartTurn;
    private Player player;

    Colors color = new Colors();

    public ExamineQuestSmith_2(String name, String description, String requiredItem, Player player) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;
        this.player = player;
    }

    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "The suspicious Note" + color.reset());
        System.out.println(color.gold() + "Find more information about the sender of that Note\n" +
                                          "Mr Anderson suspects an old guest in smith's mansion\n" +
                                          "search his room to find more info" + color.reset());
        System.out.println("Turns left: " + turnsLeft);
        questStartTurn = player.getPlayerTurn();
    }

    public void checkItems(Player player) {
        int currentTurn = player.getPlayerTurn();
        turnsLeft = turnsLeft - (currentTurn - questStartTurn);
        questStartTurn = currentTurn;

        if (turnsLeft > 0) {
            System.out.println(requiredItem);
            ArrayList<Item> inventory = player.getInventory();
            for (Item item : inventory) {
                if (item.getName().equalsIgnoreCase(requiredItem)) {
                    itemFound = true;
                    this.complete();
                    player.addProgressPoints(20);
//                System.out.println("Required item found: " + requiredItem);
                } else {
                    System.out.println(color.gold() + "You have not find the correct information\n" + color.reset());
                    System.out.println("You have left " + turnsLeft);
                }
            }
        } else {
            System.out.println("It seems you are late with the quest and the info we need may will be lost\nYou may take that quest again, later.");
            this.loseQuest();
            turnsLeft = 15;
            // Edw 8a klh8ei h me8odos gia thn meiwsh twn pontwn
        }
    }

    public void completeQuest(Player player) {
        if (itemFound) {
            System.out.println(color.blue() + "Quest Completed: " + color.gold() + "The suspicious Note" + color.reset());
            super.complete();
        } else {
            System.out.println(color.gold() + "You haven't found the required information yet. Keep looking!\n" + color.reset());
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}
