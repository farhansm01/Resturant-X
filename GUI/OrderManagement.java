package GUI;

import MenuItem.Item;
import File.MenuFile;
import ManageMenuItem.MenuItemManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderManagement extends JFrame implements ActionListener {
    private JComboBox<String> dropdownItems;
    private JTextField tfQuantity;
    private JButton buttonAddToOrder, buttonConfirmOrder, buttonClearOrders, buttonBack;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private double totalPayment = 0;

    private MenuItemManager menuList;
    private ArrayList<Item> items;

    public OrderManagement() {
        super("Order Section");
        menuList = new MenuItemManager();
        MenuFile.loadMenuFile(menuList);
        items = menuList.getAllItems();

        initializeComponents();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

 private void initializeComponents() {
    setLayout(null);

    JLabel titleLabel = new JLabel("Make Order", SwingConstants.CENTER);
    titleLabel.setBounds(320, 20, 300, 30);
    titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
    add(titleLabel);

    JLabel itemLabel = new JLabel("Select Item:");
    itemLabel.setBounds(50, 80, 100, 25);
    add(itemLabel);

    dropdownItems = new JComboBox<>(getMenuItemNames());
    dropdownItems.setBounds(160, 80, 200, 25);
    add(dropdownItems);

    JLabel quantityLabel = new JLabel("Quantity:");
    quantityLabel.setBounds(50, 120, 100, 25);
    add(quantityLabel);

    tfQuantity = new JTextField();
    tfQuantity.setBounds(160, 120, 200, 25);
    add(tfQuantity);

    buttonAddToOrder = new JButton("Add to Order");
    buttonAddToOrder.setBounds(100, 160, 150, 30);
    buttonAddToOrder.addActionListener(this);
    buttonAddToOrder.setBackground(Color.RED);
    buttonAddToOrder.setForeground(Color.WHITE);
    buttonAddToOrder.setFont(buttonAddToOrder.getFont().deriveFont(Font.BOLD));
    add(buttonAddToOrder);

    buttonConfirmOrder = new JButton("Confirm Order");
    buttonConfirmOrder.setBounds(260, 160, 150, 30);
    buttonConfirmOrder.addActionListener(this);
    buttonConfirmOrder.setEnabled(false);
    buttonConfirmOrder.setBackground(Color.RED);
    buttonConfirmOrder.setForeground(Color.WHITE);
    buttonConfirmOrder.setFont(buttonConfirmOrder.getFont().deriveFont(Font.BOLD));
    add(buttonConfirmOrder);

    buttonClearOrders = new JButton("Clear Orders");
    buttonClearOrders.setBounds(100, 200, 150, 30);
    buttonClearOrders.addActionListener(this);
    buttonClearOrders.setBackground(Color.RED);
    buttonClearOrders.setForeground(Color.WHITE);
    buttonClearOrders.setFont(buttonClearOrders.getFont().deriveFont(Font.BOLD));
    add(buttonClearOrders);

    buttonBack = new JButton("Back");
    buttonBack.setBounds(260, 200, 150, 30);
    buttonBack.addActionListener(this);
    buttonBack.setBackground(Color.RED);
    buttonBack.setForeground(Color.WHITE);
    buttonBack.setFont(buttonBack.getFont().deriveFont(Font.BOLD));
    add(buttonBack);

    tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Price", "Total"}, 0);
    orderTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(orderTable);
    scrollPane.setBounds(50, 250, 800, 300);
    add(scrollPane);

    getContentPane().setBackground(Color.WHITE);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddToOrder) {
            addItemToOrder();
        } else if (e.getSource() == buttonConfirmOrder) {
            showOrderSummary();
        } else if (e.getSource() == buttonClearOrders) {
            clearOrders();
        } else if (e.getSource() == buttonBack) {
            this.dispose();
			new MenuGui();
			
        }
    }

    private void addItemToOrder() {
        int index = dropdownItems.getSelectedIndex();
        if (index == -1 || tfQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid selection or quantity.");
            return;
        }

        String quantityText = tfQuantity.getText();
        if (!quantityText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Invalid quantity. Enter a positive number.");
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
            return;
        }

        Item selectedItem = items.get(index);
        double totalAmount = quantity * selectedItem.getPrice();
        tableModel.addRow(new Object[]{selectedItem.getItemName(), quantity, selectedItem.getPrice(), totalAmount});
        totalPayment += totalAmount;
        buttonConfirmOrder.setEnabled(true);
    }

    private void clearOrders() {
        tableModel.setRowCount(0);
        totalPayment = 0;
        buttonConfirmOrder.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Orders cleared.");
    }

    private void showOrderSummary() {
        StringBuilder summary = new StringBuilder("Order Summary:\n\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            summary.append(tableModel.getValueAt(i, 0)).append(" (Quantity: ").append(tableModel.getValueAt(i, 1))
                    .append(", Total: TK ").append(tableModel.getValueAt(i, 3)).append(")\n");
        }
        summary.append("\nTotal Payment: TK ").append(totalPayment);

        int choice = JOptionPane.showConfirmDialog(this, summary.toString(), "Order Confirmed", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Order has been saved.");
            clearOrders();
        }
    }

    private String[] getMenuItemNames() {
        return items.stream().map(Item::getItemName).toArray(String[]::new);
    }
}