package Quests;

import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestSmith extends Quest {

    String requiredItem;
    private boolean itemFound;

    public ExamineQuestSmith(String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;

    }

    @Override
    public void startQuest() {
        System.out.println("Quest Accepted: Examine Mr Smith's past");
        System.out.println("Find information and search the rumors about Mr Smiths hidden past");
        System.out.println("Mr Anderson: You may start from Mr Smith's office and the library");
    }

    public void checkItems(Player player) {
        ArrayList<Item> inventory = player.getInventory();
        if (inventory.contains(requiredItem)) {
            itemFound = true;
            System.out.println("Required item found: " + requiredItem);
        } else {
            System.out.println("You have not find the correct information");
        }
    }

    public void completeQuest(Player player) {
        if (itemFound) {
            System.out.println("Quest Completed: Examine Mr Smith's past");
            super.complete();
        } else {
            System.out.println("You haven't found the required information yet. Keep looking!");
        }
    }

    public boolean isItemFound() {
        return itemFound;
    }
}
