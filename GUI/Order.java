package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderManagement extends JFrame {
    private JPanel panel;
    private JLabel labelOrderManagement;
    private JLabel labelSelectItem;
    private JLabel labelQuantity;
    private JComboBox<String> dropdownItems;
    private JTextField tfQuantity;
    private JButton buttonAddToOrder;
    private JButton buttonConfirmOrder;
    private JButton buttonClearOrders;

    private String[] menuItems;
    private double[] menuPrices;
    private String[] selectedItems;
    private int[] selectedQuantities;
    private int selectedItemCount;
    private double totalPayment;

    public OrderManagement() {
        initializeComponents();
        setTitle("Order Section");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void initializeComponents() {
        panel = new JPanel(null);
        labelOrderManagement = new JLabel("Make Order");
        labelOrderManagement.setBounds(300, 10, 300, 50);
        labelOrderManagement.setFont(new Font("Roboto", Font.BOLD, 50));
        panel.add(labelOrderManagement);

        labelSelectItem = new JLabel("Select Item:");
        labelSelectItem.setBounds(40, 80, 300, 50);
        labelSelectItem.setFont(new Font("Roboto", Font.PLAIN, 40));
        panel.add(labelSelectItem);

        loadMenuItems();

        dropdownItems = new JComboBox<>(menuItems);
        dropdownItems.setBounds(300, 80, 290, 50);
        dropdownItems.setFont(new Font("Roboto", Font.PLAIN, 20));
        panel.add(dropdownItems);

        labelQuantity = new JLabel("Quantity:");
        labelQuantity.setBounds(40, 150, 200, 50);
        labelQuantity.setFont(new Font("Roboto", Font.PLAIN, 40));
        panel.add(labelQuantity);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(300, 150, 290, 50);
        tfQuantity.setFont(new Font("Roboto", Font.PLAIN, 30));
        panel.add(tfQuantity);

        buttonAddToOrder = new JButton("Add to Order");
        buttonAddToOrder.setBounds(600, 150, 200, 50);
        buttonAddToOrder.setFont(new Font("Roboto", Font.PLAIN, 20));
        buttonAddToOrder.setBackground(Color.ORANGE);
        panel.add(buttonAddToOrder);

        selectedItems = new String[50];
        selectedQuantities = new int[50];
        selectedItemCount = 0;

        buttonConfirmOrder = new JButton("Confirm Order");
        buttonConfirmOrder.setBounds(300, 400, 300, 50);
        buttonConfirmOrder.setFont(new Font("Roboto", Font.PLAIN, 30));
        buttonConfirmOrder.setBackground(Color.RED);
        buttonConfirmOrder.setEnabled(false);
        panel.add(buttonConfirmOrder);

        buttonClearOrders = new JButton("Clear Orders");
        buttonClearOrders.setBounds(40, 400, 200, 50);
        buttonClearOrders.setFont(new Font("Roboto", Font.PLAIN, 20));
        buttonClearOrders.setBackground(Color.ORANGE);
        panel.add(buttonClearOrders);

        buttonAddToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (isQuantityValid()) {
                    String selectedItem = (String) dropdownItems.getSelectedItem();
                    int quantity = Integer.parseInt(tfQuantity.getText());

                    int index = Arrays.asList(menuItems).indexOf(selectedItem);
                    if (index != -1) { // Check if the item is found
                        double price = menuPrices[index];
                        double totalAmount = quantity * price;

                        selectedItems[selectedItemCount] = selectedItem;
                        selectedQuantities[selectedItemCount] = quantity;
                        selectedItemCount++;

                        totalPayment += totalAmount;

                        JOptionPane.showMessageDialog(null, selectedItem + " added to the order.");
                        buttonConfirmOrder.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected item not found in menu.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid positive integer.");
                }
            }
        });

        buttonConfirmOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                showOrderSummary();
            }
        });

        buttonClearOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                clearOrders();
            }
        });

        add(panel);
    }

    private boolean isQuantityValid() {
        String quantityText = tfQuantity.getText();
        if (quantityText.isEmpty()) {
            return false;
        }
        try {
            int quantity = Integer.parseInt(quantityText);
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clearOrders() {
        selectedItemCount = 0;
        totalPayment = 0.0;
        buttonConfirmOrder.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Orders cleared.", "Cleared", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadMenuItems() {
        String file = "./File/data/menu.txt"; // Corrected file path
        List<String> itemList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) { // Ensure enough parts exist
                    String itemName = parts[1].trim();
                    try {
                        double price = Double.parseDouble(parts[4].trim());
                        itemList.add(itemName);
                        priceList.add(price);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Error parsing price for " + itemName, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading menu items: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        menuItems = itemList.toArray(new String[0]);
        menuPrices = priceList.stream().mapToDouble(Double::doubleValue).toArray();
    }


    private void showOrderSummary() {
        StringBuilder summary = new StringBuilder("Order Summary:\n\n");
        for (int i = 0; i < selectedItemCount; i++) {
            int index = Arrays.asList(menuItems).indexOf(selectedItems[i]);
            if (index != -1) { // Check if the item is found
                double total = selectedQuantities[i] * menuPrices[index];
                summary.append(selectedItems[i]).append(" (Quantity: ").append(selectedQuantities[i])
                        .append(", Total: TK").append(total).append(")\n");
            }
        }
        summary.append("\nTotal Payment: TK ").append(totalPayment);
        JOptionPane.showMessageDialog(this, summary.toString(), "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new OrderManagement();
    }
}