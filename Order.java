import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;

    // Constructor
    public Order() {
        items = new ArrayList<>();
    }

    // Add item to order
    public void addItem(Item item) {
        items.add(item);
    }

    // Get all items
    public ArrayList<Item> getItems() {
        return items;
    }
}