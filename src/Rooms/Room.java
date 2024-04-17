package Rooms;

import Item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Room {
    String name;
    String description;
    String direction;
    Map<String, Room> exits;
    private Vector items;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        items = new Vector();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addExit(String direction, Room destination) {
        exits.put(direction, destination);
    }


    public Map<String, Room> getExit() {
        return exits;
    }

    public void addItem(Item i) {
        items.addElement(i);
    }

    public void removeItem(Item i) {
        if (items.contains(i)) {
            items.removeElement(i);
        }
    }

    public Vector getItems() {
        return (Vector) items;
    }

}

