package Rooms;

import java.util.HashMap;
import java.util.Map;

public class Room {
    String name;
    String description;
    String direction;
    Map<String, Room> exits;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
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


    public Room getExit() {
        return exits.get(direction);
    }
}

