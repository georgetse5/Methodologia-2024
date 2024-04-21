package Test;

import Item.Item;
import Player.Player;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player("John");
    Item item = new Item("Key", "A small shiny key");

    @Test
    public void testAddItemToInventory() {
        player.addItemToInventory(item);
        assertTrue(player.getInventory().contains(item));
    }

    @Test
    public void testRemoveItemToInventory() {
        player.removeItemFromInventory(item);
        // Gia arxh elegxei an den yparxei kapoio antikeimeno mesa sto inventory
        assertFalse(player.getInventory().contains(item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentItemFromInventory() {
        player.removeItemFromInventory(item);
    }
}