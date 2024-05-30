package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestJohn_2 extends Quest {

    String requiredItem;
    private boolean itemFound;

    Colors color = new Colors();

    public ExamineQuestJohn_2(String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;

    }


    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "Go speak with Mr.Anderson" + color.reset());
        System.out.println(color.gold() + "Find information about the woman in the picture from Mr.Anderson\n" + color.reset());
        super.complete();
        setQuestAccepted();
        complete();
    }

    public void checkItems(Player player) {
        ArrayList<Item> inventory = player.getInventory();
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(requiredItem)) {
                itemFound = true;
//                System.out.println("Required item found: " + requiredItem);
            } else {
                System.out.println(color.red() + "You have not find the correct information" + color.reset());
            }
        }
    }

    public  void completeQuest(Player player) {
        if (itemFound) {
            System.out.println(color.blue() + "Quest Completed: " + color.gold() + "Go speak with Mr.Anderson" + color.reset());
            System.out.println(color.red() + "You should go back to john\n" + color.reset());
            player.addProgressPoints(20);
            this.complete();
        } else {
            System.out.println(color.red() + "You haven't found the required information yet. Keep looking!" + color.reset());
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}
