//Represents one menu item with a name and price.

public class Item {
    private String name;
    private double price;

    // Constructor
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Get item name
    public String getName() {
        return name;
    }

    // Get item price
    public double getPrice() {
        return price;
    }

    // Display item in the menu
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}