package Container;

import Item.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {
    private List<Item> items;
    private String name;
    private boolean requiresKey;
    private String key;

    public Container(String name, boolean requiresKey, String key) {
        this.items = new ArrayList<>();
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}

