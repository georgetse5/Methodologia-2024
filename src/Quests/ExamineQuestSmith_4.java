package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestSmith_4 extends Quest {

    String requiredItem;
    private boolean itemFound;

    Colors color = new Colors();

    public ExamineQuestSmith_4(String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;

    }

    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "Find Photos" + color.reset());
        System.out.println(color.gold() + "Find the photos\n" + color.reset());
        System.out.println(color.gold() + "Mrs Natali: " + color.reset() + "You may start from Secret Room \n");
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
                System.out.println(color.red() + "You have not find the correct information" + color.reset());
            }
        }
    }

    public void completeQuest(Player player) {
        if (itemFound) {
            System.out.println(color.blue() + "Quest Completed: " + color.gold() + "Find Photos" + color.reset());
            super.complete();
            player.addProgressPoints(20);
        } else {
            System.out.println(color.red() + "You haven't found the required information yet. Keep looking!" + color.reset());
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}

