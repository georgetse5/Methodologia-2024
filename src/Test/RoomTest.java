package Test;

import Rooms.*;
import Item.Item;
import Player.Player;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {

    Player player = new Player("John");
    Item item = new Item("Key", "A small shiny key", true);
    Room livingRoom = new Room("Living Room", "A cozy living room");
    Room startingRoom = new Room("Starting Room", "A cozy starting room");

    Room hall_1 = new Hall_1("Hall", "First Room");
    Room hall_2 = new Hall_2("First floor hall", "Another long hallway");
    Room hall_3 = new Hall_3 ("Second floor hall", "A room with 4 doors");
    Room library = new Library("Library" , "A well persevered book collection");
    Room office = new Office("Office", "Seems like an ordinary office, you feel a draft behind the east bookcase");

    @Test
    public void testEmptyItemsList() {
        assertTrue(livingRoom.getItems().isEmpty());
    }

    @Test
    public void testNonEmptyItemsList() {
        startingRoom.addItem(item);
        assertFalse(startingRoom.getItems().isEmpty());
    }

    @Test
    public void testHall2DiretionExist() {

        hall_2.addExit("downstairs", hall_1);
        hall_2.addExit("north", library);
        hall_2.addExit("south", office);
        hall_2.addExit("upstairs", hall_3);

        assertTrue(hall_2.getExit().containsKey("downstairs"));
    }

    @Test
    public void testHall2DiretionNotExist() {

        hall_2.addExit("downstairs", hall_1);
        hall_2.addExit("north", library);
        hall_2.addExit("south", office);
        hall_2.addExit("upstairs", hall_3);

        assertFalse(hall_2.getExit().containsKey(("west")));
    }
}