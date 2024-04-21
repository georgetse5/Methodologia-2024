package Container;

import Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Item> items;
    private boolean requiresKey;
    private String key;

    public Container(boolean requiresKey, String key) {
        this.items = new ArrayList<>();
        this.requiresKey = requiresKey;
        this.key = key;
    }

    public boolean requiresKey() {
        return requiresKey;
    }

    public void setAsUnlocked() {
        requiresKey = false;
    }

    public String getKey() {
        return key;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }
}

