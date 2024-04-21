package Player;

import Item.Item;
import Rooms.Room;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Item> inventory;
    private Room currentRoom;

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_GOLD = "\u001B[33m";
    private String ANSI_CYAN = "\u001B[36m";
    private String ANSI_BLUE = "\u001B[34m";

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println("You have picked up " + ANSI_GOLD + item.getName() + ANSI_RESET);
    }

    public void removeItemFromInventory(Item item) {
        if (!inventory.remove(item)) {
            throw new IllegalArgumentException("Item not found in inventory");
        }
        System.out.println("You dropped " + item.getName());
    }

    public void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println(name + "'s inventory is empty");
        } else {
            System.out.println(name + "'s inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}
