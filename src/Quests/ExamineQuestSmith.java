package Quests;

import Colors.Colors;
import Item.Item;
import Player.Player;

import java.util.ArrayList;

public class ExamineQuestSmith extends Quest {

    String requiredItem;
    private boolean itemFound;

    Colors color = new Colors();

    public ExamineQuestSmith(String name, String description, String requiredItem) {
        super(name, description);
        this.requiredItem = requiredItem;
        this.itemFound = false;

    }

    @Override
    public void startQuest() {
        System.out.println(color.cyan() + "Quest Accepted: " + color.gold() + "Examine Mr Smith's past" + color.reset());
        System.out.println(color.gold() + "Find information and search the rumors about Mr Smiths hidden past\n" + color.reset());
        System.out.println(color.gold() + "Mr Anderson: " + color.reset() + "You may start from Mr Smith's office and the library\n");
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
            System.out.println(color.blue() + "Quest Completed: " + color.gold() + "Examine Mr Smith's past" + color.reset());
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
