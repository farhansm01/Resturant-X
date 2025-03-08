package ManageMenuItem;

import MenuItem.Item;
import java.util.ArrayList;

public class MenuItemManager {
    private Item[] items;
    private int itemCount;

    public MenuItemManager() {
        items = new Item[60];
        itemCount = 0;
    }

    public MenuItemManager(int size) {
        items = new Item[size];
        itemCount = 0;
    }

    public void clear() {
        for (int i = 0; i < itemCount; i++) {
            items[i] = null;
        }
        itemCount = 0;
    }

    public Item getById(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null && items[i].getItemId().equals(id)) {
                System.out.println("Item Found");
                return items[i];
            }
        }
        System.out.println("No Item Found With This ID.");
        return null;
    }

    public void insert(Item it) {
        if (itemCount < items.length) {
            items[itemCount] = it;
            itemCount++;
            System.out.println("Item Successfully Inserted.");
        } else {
            System.out.println("Item Insertion Failed. Array is full.");
        }
    }

    public void deleteById(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null && items[i].getItemId().equals(id)) {
                items[i] = null;
                // Shift remaining items to fill the gap
                for (int j = i; j < itemCount - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[itemCount - 1] = null; // Clear the last element
                itemCount--;
                System.out.println("Item Deleted.");
                return;
            }
        }
        System.out.println("No Item Found with this ID.");
    }

    public void showAll() {
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                items[i].showItemInfo();
            }
        }
    }

    public String getAllAsString() {
        StringBuilder allItems = new StringBuilder();
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                allItems.append(items[i].getItemAsString()).append("\n");
            }
        }
        return allItems.toString();
    }

    public void viewAvailableMenuItems() {
        boolean foundAvailable = false;
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null && items[i].isAvailable()) {
                items[i].showItemInfo();
                foundAvailable = true;
            }
        }
        if (!foundAvailable) {
            System.out.println("No available menu items at the moment.");
        }
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            if (items[i] != null) {
                list.add(items[i]);
            }
        }
        return list;
    }
}