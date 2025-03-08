
package GUI;

import Reservation.Reservation;
import ReservationList.ReservationList;
import File.ReservationFile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationGUI extends JFrame implements ActionListener {
    private ReservationList reservationList;
    private JTextField userTextField, phoneTextField, emailTextField, searchTextField;
    private JComboBox<String> tableComboBox;
    private JButton addButton, removeButton, searchButton, displayButton, backButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public ReservationGUI() {
        super("Restaurant Reservation System");
        reservationList = new ReservationList(50);

        ReservationFile.loadReservations(reservationList);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Reservation System");
        titleLabel.setBounds(350, 20, 300, 30);
        titleLabel.setFont(new Font("Cambria", Font.BOLD, 28));
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        add(nameLabel);
        userTextField = new JTextField();
        userTextField.setBounds(160, 80, 200, 25);
        add(userTextField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 120, 100, 25);
        add(phoneLabel);
        phoneTextField = new JTextField();
        phoneTextField.setBounds(160, 120, 200, 25);
        add(phoneTextField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 160, 100, 25);
        add(emailLabel);
        emailTextField = new JTextField();
        emailTextField.setBounds(160, 160, 200, 25);
        add(emailTextField);

        JLabel tableLabel = new JLabel("Table Type:");
        tableLabel.setBounds(50, 200, 100, 25);
        add(tableLabel);
        tableComboBox = new JComboBox<>(new String[]{"Regular", "VIP", "Corner"});
        tableComboBox.setBounds(160, 200, 200, 25);
        add(tableComboBox);

        addButton = new JButton("Add Reservation");
        addButton.setBounds(100, 250, 150, 30);
        addButton.addActionListener(this);
        add(addButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(260, 250, 150, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(500, 80, 100, 25);
        searchButton.addActionListener(this);
        add(searchButton);

        searchTextField = new JTextField();
        searchTextField.setBounds(610, 80, 200, 25);
        add(searchTextField);

        displayButton = new JButton("Display All");
        displayButton.setBounds(550, 250, 150, 30);
        displayButton.addActionListener(this);
        add(displayButton);

        backButton = new JButton("Back");
        backButton.setBounds(720, 250, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Phone", "Email", "Table Type"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 300, 850, 300);
        add(scrollPane);


        getContentPane().setBackground(Color.WHITE);
        setForeground(Color.RED);
        titleLabel.setForeground(Color.BLACK);
        nameLabel.setForeground(Color.BLACK);
        phoneLabel.setForeground(Color.BLACK);
        emailLabel.setForeground(Color.BLACK);
        tableLabel.setForeground(Color.BLACK);

        Color deepOrange = new Color(255, 0, 0); // Deep orange color (adjust RGB values as needed)
        addButton.setBackground(deepOrange);
        addButton.setForeground(Color.WHITE);

        removeButton.setBackground(deepOrange);
        removeButton.setForeground(Color.WHITE);

        searchButton.setBackground(deepOrange);
        searchButton.setForeground(Color.WHITE);

        displayButton.setBackground(deepOrange);
        displayButton.setForeground(Color.WHITE);

        backButton.setBackground(deepOrange);
        backButton.setForeground(Color.WHITE);


        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.getTableHeader().setBackground(Color.RED);
        table.getTableHeader().setForeground(Color.WHITE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = userTextField.getText().trim();
            String phone = phoneTextField.getText().trim();
            String email = emailTextField.getText().trim();
            String tableType = (String) tableComboBox.getSelectedItem();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reservation reservation = new Reservation(name, phone, email, tableType);
            reservationList.insert(reservation);
            ReservationFile.saveReservations(reservationList);

            updateTableDisplay();
            clearInputFields();

            JOptionPane.showMessageDialog(this, "Reservation Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == removeButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String name = (String) tableModel.getValueAt(selectedRow, 0);
                reservationList.removeByName(name);
                ReservationFile.saveReservations(reservationList);
                updateTableDisplay();
                JOptionPane.showMessageDialog(this, "Reservation Removed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == searchButton) {
            String name = searchTextField.getText().trim();
            Reservation res = reservationList.getByName(name);
            if (res != null) {
                JOptionPane.showMessageDialog(this, res.getReservationAsString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No Reservation found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == displayButton) {
            updateTableDisplay();

        } else if (e.getSource() == backButton) {
            this.dispose();
            new DashboardPage();
        }
    }

    private void updateTableDisplay() {
        tableModel.setRowCount(0);
        Reservation[] reservations = reservationList.getAllReservations();
        for (int i = 0; i < reservationList.getReservationCount(); i++) {
            Reservation reservation = reservations[i];
            tableModel.addRow(new Object[]{reservation.getCustomerName(), reservation.getCustomerPhone(), reservation.getCustomerEmail(), reservation.getTableType()});
        }
    }

    private void clearInputFields() {
        userTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservationGUI());
    }
}