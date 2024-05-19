package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestSmith_2 extends Quest {

    String requiredItem;
    private boolean itemFound;

    Colors color = new Colors();

    public ExamineQuestSmith_2(String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;
    }

    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "The suspicious Note" + color.reset());
        System.out.println("Find more information about the sender of that Note\n");
    }

    public void checkItems(Player player) {
        System.out.println(requiredItem);
        ArrayList<Item> inventory = player.getInventory();
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(requiredItem)) {
                itemFound = true;
                this.complete();
                System.out.println("Required item found: " + requiredItem);
            } else {
                System.out.println("You have not find the correct information");
            }
        }
    }

    public void completeQuest(Player player) {
        if (itemFound) {
            System.out.println("Quest Completed: The suspicious Note");
            super.complete();
        } else {
            System.out.println("You haven't found the required information yet. Keep looking!");
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}
