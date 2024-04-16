import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Item> inventory;

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

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println(name + " picked up " + item.getName());
    }

    public void removeItemFromInventory(Item item) {
        if (inventory.remove(item)) {
            System.out.println(name + " dropped " + item.getName());
        } else {
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
