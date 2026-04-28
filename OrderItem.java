public class OrderItem {
    private Item item;
    private int quantity;

    // Constructor
    public OrderItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    // Copy constructor
    public OrderItem(OrderItem other) {
        this.item = other.item;
        this.quantity = other.quantity;
    }

    // Increase quantity
    public void addOne() {
        quantity++;
    }

    // Decrease quantity
    public void removeOne() {
        if (quantity > 0) {
            quantity--;
        }
    }

    // Get item
    public Item getItem() {
        return item;
    }

    // Get quantity
    public int getQuantity() {
        return quantity;
    }

    // Get name
    public String getName() {
        return item.getName();
    }

    // Get subtotal for this item
    public double getSubtotal() {
        return item.getPrice() * quantity;
    }

    // Display order item
    public String toString() {
        return item.getName() + " x" + quantity + " - $" + String.format("%.2f", getSubtotal());
    }
}