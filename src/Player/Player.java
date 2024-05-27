package Player;

import Item.Item;
import Rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private ArrayList<Item> inventory;
    private Room currentRoom;
    private int progressPoints;

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_GOLD = "\u001B[33m";
    private String ANSI_CYAN = "\u001B[36m";
    private String ANSI_BLUE = "\u001B[34m";

    public Player() {
        this.name = "Player";
        this.inventory = new ArrayList<>();
        this.progressPoints = 0;
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.progressPoints = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addProgressPoints(int points) {
        this.progressPoints = progressPoints + points;
    }

    public int getProgressPoints() {
        return progressPoints;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println("You have picked up " + ANSI_GOLD + item.getName() + ANSI_RESET);
    }

    public void removeItemFromInventory(Item item) {
        try {
            if (inventory.remove(item)) {
                System.out.println("You dropped " + item.getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Item not found in inventory");
        }
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
