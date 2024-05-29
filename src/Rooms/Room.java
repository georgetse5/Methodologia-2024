package Rooms;

import Item.Item;
import Container.Container;
import NPC.*;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable {
    String name;
    String description;
    int xCoordinate;
    int yCoordinate;
    Map<String, Room> exits;
    private Vector items;
    private List<Container> containers;
    private List<NPC> npcs;


    public Room(String name, String description, int xCoordinate, int yCoordinate) {
        this.name = name;
        this.description = description;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        exits = new HashMap<>();
        items = new Vector();
        containers = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
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

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void removeContainer(Container container) {
        containers.remove(container);
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

}

