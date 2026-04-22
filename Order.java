import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;

    // Constructor
    public Order() {
        items = new ArrayList<>();
    }

    // Add item
    public void addItem(Item item) {
        items.add(item);
    }

    // Remove item
    public void removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.remove(i);
                break;
            }
        }
    }

    // Calculate total
    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Display order
    public void displayOrder() {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    // Getter
    public ArrayList<Item> getItems() {
        return items;
    }
}