package restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
This is the graphical interface for the Restaurant Ordering System.
The user can search, sort, add items, remove items, undo, place orders,
save receipts, and send orders to a kitchen queue.
*/

public class RestaurantOrderingGUI extends JFrame {
    private ArrayList<Item> menuItems;
    private Order currentOrder;
    private Queue<Order> kitchenQueue;

    private DefaultListModel<Item> menuListModel;
    private DefaultListModel<OrderItem> orderListModel;

    private JList<Item> menuList;
    private JList<OrderItem> orderList;

    private JLabel totalLabel;
    private JLabel itemCountLabel;
    private JLabel queueLabel;

    private JTextField searchField;

    public RestaurantOrderingGUI() {
        menuItems = MenuLoader.loadMenu("menu.csv");
        currentOrder = new Order();
        kitchenQueue = new LinkedList<>();

        setTitle("Restaurant Ordering System");
        setSize(950, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupGUI();

        setVisible(true);
    }

    private void setupGUI() {
        menuListModel = new DefaultListModel<>();
        orderListModel = new DefaultListModel<>();

        loadMenuList(menuItems);

        menuList = new JList<>(menuListModel);
        orderList = new JList<>(orderListModel);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JPanel menuPanel = createMenuPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel orderPanel = createOrderPanel();

        mainPanel.add(menuPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(orderPanel);

        add(mainPanel);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new BorderLayout());

        JLabel menuLabel = new JLabel("Menu", SwingConstants.CENTER);
        menuPanel.add(menuLabel, BorderLayout.NORTH);

        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        JPanel menuControlPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        searchField = new JTextField();

        JPanel searchButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton searchButton = new JButton("Search");
        JButton resetButton = new JButton("Show All");

        searchButtonPanel.add(searchButton);
        searchButtonPanel.add(resetButton);

        JButton sortPriceButton = new JButton("Sort by Price");

        menuControlPanel.add(searchField);
        menuControlPanel.add(searchButtonPanel);
        menuControlPanel.add(sortPriceButton);

        menuPanel.add(menuControlPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchMenu());
        resetButton.addActionListener(e -> showAllMenuItems());
        sortPriceButton.addActionListener(e -> sortMenuByPrice());

        return menuPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 5, 5));

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton undoButton = new JButton("Undo Last Add");
        JButton clearButton = new JButton("Clear Order");
        JButton placeOrderButton = new JButton("Place Order");
        JButton saveReceiptButton = new JButton("Save Receipt");
        JButton sendKitchenButton = new JButton("Send to Kitchen");
        JButton processKitchenButton = new JButton("Process Next Order");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(saveReceiptButton);
        buttonPanel.add(sendKitchenButton);
        buttonPanel.add(processKitchenButton);

        addButton.addActionListener(e -> addSelectedItem());
        removeButton.addActionListener(e -> removeSelectedItem());
        undoButton.addActionListener(e -> undoLastAdd());
        clearButton.addActionListener(e -> clearOrder());
        placeOrderButton.addActionListener(e -> placeOrder());
        saveReceiptButton.addActionListener(e -> saveReceipt());
        sendKitchenButton.addActionListener(e -> sendToKitchen());
        processKitchenButton.addActionListener(e -> processNextKitchenOrder());

        return buttonPanel;
    }

    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());

        orderPanel.add(new JLabel("Current Order", SwingConstants.CENTER), BorderLayout.NORTH);
        orderPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));

        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        itemCountLabel = new JLabel("Items: 0", SwingConstants.CENTER);
        queueLabel = new JLabel("Kitchen Queue: 0", SwingConstants.CENTER);

        infoPanel.add(totalLabel);
        infoPanel.add(itemCountLabel);
        infoPanel.add(queueLabel);

        orderPanel.add(infoPanel, BorderLayout.SOUTH);

        return orderPanel;
    }

    private void loadMenuList(ArrayList<Item> items) {
        menuListModel.clear();

        for (Item item : items) {
            menuListModel.addElement(item);
        }
    }

    /*
    Linear Search
    This method searches the menu by checking each item name.
    */
    
    private void searchMenu() {
        String searchText = searchField.getText().trim().toLowerCase();
        ArrayList<Item> results = new ArrayList<>();

        for (Item item : menuItems) {
            if (item.getName().toLowerCase().contains(searchText)) {
                results.add(item);
            }
        }

        loadMenuList(results);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No menu items found.");
        }
    }

    private void showAllMenuItems() {
        searchField.setText("");
        loadMenuList(menuItems);
    }

    /*
	Selection Sort
    This method sorts menu items from lowest price to highest price.
    */
    
    private void sortMenuByPrice() {
        ArrayList<Item> sortedItems = new ArrayList<>(menuItems);

        for (int i = 0; i < sortedItems.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < sortedItems.size(); j++) {
                if (sortedItems.get(j).getPrice() < sortedItems.get(minIndex).getPrice()) {
                    minIndex = j;
                }
            }

            Item temp = sortedItems.get(i);
            sortedItems.set(i, sortedItems.get(minIndex));
            sortedItems.set(minIndex, temp);
        }

        loadMenuList(sortedItems);
    }

    private void addSelectedItem() {
        Item selectedItem = menuList.getSelectedValue();

        if (selectedItem != null) {
            currentOrder.addItem(selectedItem);
            refreshOrderList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item from the menu.");
        }
    }

    private void removeSelectedItem() {
        OrderItem selectedItem = orderList.getSelectedValue();

        if (selectedItem != null) {
            currentOrder.removeItem(selectedItem.getName());
            refreshOrderList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.");
        }
    }

    private void undoLastAdd() {
        boolean success = currentOrder.undoLastAdd();

        if (success) {
            refreshOrderList();
        } else {
            JOptionPane.showMessageDialog(this, "There is nothing to undo.");
        }
    }

    private void clearOrder() {
        currentOrder.clearOrder();
        refreshOrderList();
    }

    private void placeOrder() {
        if (currentOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your order is empty.");
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Order placed successfully!\n\n" + currentOrder.getReceiptText()
        );

        clearOrder();
    }

    private void saveReceipt() {
        if (currentOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your order is empty. There is no receipt to save.");
            return;
        }

        ReceiptWriter.saveReceipt("receipt.txt", currentOrder.getReceiptText());
        JOptionPane.showMessageDialog(this, "Receipt saved to receipt.txt.");
    }

    private void sendToKitchen() {
        if (currentOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your order is empty.");
            return;
        }

        kitchenQueue.add(new Order(currentOrder));
        clearOrder();
        updateLabels();

        JOptionPane.showMessageDialog(this, "Order sent to kitchen queue.");
    }

    private void processNextKitchenOrder() {
        if (kitchenQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no orders in the kitchen queue.");
            return;
        }

        Order nextOrder = kitchenQueue.poll();
        updateLabels();

        JOptionPane.showMessageDialog(
                this,
                "Next kitchen order processed:\n\n" + nextOrder.getReceiptText()
        );
    }

    private void refreshOrderList() {
        orderListModel.clear();

        for (OrderItem orderItem : currentOrder.getOrderItems()) {
            orderListModel.addElement(orderItem);
        }

        updateLabels();
    }

    private void updateLabels() {
        totalLabel.setText("Total: $" + String.format("%.2f", currentOrder.calculateTotal()));
        itemCountLabel.setText("Items: " + currentOrder.countItemsRecursive());
        queueLabel.setText("Kitchen Queue: " + kitchenQueue.size());
    }
}