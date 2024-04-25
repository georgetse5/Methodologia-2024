package Test;

import Item.Item;
import Player.Player;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player("John");
    Item key = new Item("Key", "A small shiny key", true);
    Item teddyBear = new Item("Teddy Bear", "A fluffy bear", false);
    Item brokenWatch = new Item("Broken Watch", "A vintage broken watch", false);

    @Test
    public void testAddItemToInventory() {
        player.addItemToInventory(key);
        assertTrue(player.getInventory().contains(key));
    }

    @Test
    public void testRemoveItemToInventory() {
        player.addItemToInventory(key);
        player.addItemToInventory(brokenWatch);

        System.out.println(player.getInventory());
        player.removeItemFromInventory(key);

        System.out.println(player.getInventory());
        assertFalse(player.getInventory().contains(key));
    }

    @Test
    public void testRemoveNonExistentItemFromInventory() {
            player.addItemToInventory(teddyBear);
            player.addItemToInventory(brokenWatch);
        try {
            player.removeItemFromInventory(key);
        } catch (IllegalArgumentException e) {
            assertEquals("Item not found in inventory", e.getMessage());
        }
    }
}