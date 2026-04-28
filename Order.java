import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;

    // Constructor
    public Order() {
        items = new ArrayList<>();
    }

    // Add item to the order
    public void addItem(Item item) {
        items.add(item);
    }

    // Remove item by position
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    // Calculate total price
    public double calculateTotal() {
        double total = 0;

        for (Item item : items) {
            total += item.getPrice();
        }

        return total;
    }

    // Clear all items
    public void clearOrder() {
        items.clear();
    }

    // Check if order is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Getter
    public ArrayList<Item> getItems() {
        return items;
    }
}