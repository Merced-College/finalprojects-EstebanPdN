import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantOrderingGUI extends JFrame {
    private ArrayList<Item> menuItems;
    private Order currentOrder;

    private DefaultListModel<Item> menuListModel;
    private DefaultListModel<Item> orderListModel;

    private JList<Item> menuList;
    private JList<Item> orderList;

    private JLabel totalLabel;

    public RestaurantOrderingGUI() {
        menuItems = MenuLoader.loadMenu("menu.csv");
        currentOrder = new Order();

        setTitle("Restaurant Ordering System");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupGUI();

        setVisible(true);
    }

    private void setupGUI() {
        menuListModel = new DefaultListModel<>();
        orderListModel = new DefaultListModel<>();

        for (Item item : menuItems) {
            menuListModel.addElement(item);
        }

        menuList = new JList<>(menuListModel);
        orderList = new JList<>(orderListModel);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(new JLabel("Menu", SwingConstants.CENTER), BorderLayout.NORTH);
        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton clearButton = new JButton("Clear Order");
        JButton placeOrderButton = new JButton("Place Order");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(placeOrderButton);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(new JLabel("Current Order", SwingConstants.CENTER), BorderLayout.NORTH);
        orderPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);

        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        orderPanel.add(totalLabel, BorderLayout.SOUTH);

        mainPanel.add(menuPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(orderPanel);

        add(mainPanel);

        addButton.addActionListener(e -> addSelectedItem());
        removeButton.addActionListener(e -> removeSelectedItem());
        clearButton.addActionListener(e -> clearOrder());
        placeOrderButton.addActionListener(e -> placeOrder());
    }

    private void addSelectedItem() {
        Item selectedItem = menuList.getSelectedValue();

        if (selectedItem != null) {
            currentOrder.addItem(selectedItem);
            orderListModel.addElement(selectedItem);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item from the menu.");
        }
    }

    private void removeSelectedItem() {
        int selectedIndex = orderList.getSelectedIndex();

        if (selectedIndex >= 0) {
            currentOrder.removeItem(selectedIndex);
            orderListModel.remove(selectedIndex);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.");
        }
    }

    private void clearOrder() {
        currentOrder.clearOrder();
        orderListModel.clear();
        updateTotal();
    }

    private void placeOrder() {
        if (currentOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your order is empty.");
            return;
        }

        double total = currentOrder.calculateTotal();

        JOptionPane.showMessageDialog(
                this,
                "Order placed successfully!\nTotal: $" + String.format("%.2f", total)
        );

        clearOrder();
    }

    private void updateTotal() {
        totalLabel.setText("Total: $" + String.format("%.2f", currentOrder.calculateTotal()));
    }
}