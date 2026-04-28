import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

/*
Manages the current order using a HashMap-style structure.
It stores item quantities, calculates totals, and supports undo.
*/

public class Order {
    private LinkedHashMap<String, OrderItem> orderItems;
    private Stack<Item> undoStack;

    // Constructor
    public Order() {
        orderItems = new LinkedHashMap<>();
        undoStack = new Stack<>();
    }

    // Copy constructor
    public Order(Order other) {
        orderItems = new LinkedHashMap<>();
        undoStack = new Stack<>();

        for (OrderItem orderItem : other.getOrderItems()) {
            orderItems.put(orderItem.getName(), new OrderItem(orderItem));
        }
    }

    // Add item to the order
    public void addItem(Item item) {
        if (orderItems.containsKey(item.getName())) {
            orderItems.get(item.getName()).addOne();
        } else {
            orderItems.put(item.getName(), new OrderItem(item));
        }

        undoStack.push(item);
    }

    // Remove one quantity of an item
    public void removeItem(String itemName) {
        if (orderItems.containsKey(itemName)) {
            OrderItem orderItem = orderItems.get(itemName);
            orderItem.removeOne();

            if (orderItem.getQuantity() == 0) {
                orderItems.remove(itemName);
            }
        }
    }

    // Undo the last added item
    public boolean undoLastAdd() {
        if (undoStack.isEmpty()) {
            return false;
        }

        Item lastItem = undoStack.pop();
        removeItem(lastItem.getName());
        return true;
    }

    /*
    Author: Esteban Pereira das Neves

    Algorithm: Recursive Total Calculation

    This method calculates the total price of the order recursively.
    Time Complexity: O(n)
    */
    public double calculateTotal() {
        ArrayList<OrderItem> items = getOrderItems();
        return calculateTotalRecursive(items, 0);
    }

    private double calculateTotalRecursive(ArrayList<OrderItem> items, int index) {
        if (index >= items.size()) {
            return 0;
        }

        return items.get(index).getSubtotal() + calculateTotalRecursive(items, index + 1);
    }

    /*
    Author: Esteban Pereira das Neves

    Algorithm: Recursive Item Counter

    This method counts the total number of food items in the order.
    Time Complexity: O(n)
    */
    public int countItemsRecursive() {
        ArrayList<OrderItem> items = getOrderItems();
        return countItemsRecursive(items, 0);
    }

    private int countItemsRecursive(ArrayList<OrderItem> items, int index) {
        if (index >= items.size()) {
            return 0;
        }

        return items.get(index).getQuantity() + countItemsRecursive(items, index + 1);
    }

    // Clear the whole order
    public void clearOrder() {
        orderItems.clear();
        undoStack.clear();
    }

    // Check if order is empty
    public boolean isEmpty() {
        return orderItems.isEmpty();
    }

    // Get order items as an ArrayList
    public ArrayList<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems.values());
    }

    // Create receipt text
    public String getReceiptText() {
        String receipt = "Restaurant Ordering System Receipt\n\n";

        for (OrderItem orderItem : getOrderItems()) {
            receipt += orderItem.toString() + "\n";
        }

        receipt += "\nTotal Items: " + countItemsRecursive();
        receipt += "\nTotal: $" + String.format("%.2f", calculateTotal());

        return receipt;
    }
