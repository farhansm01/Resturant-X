package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends JFrame implements ActionListener {

    // Font styles
    Font font20 = new Font("Cambria", Font.BOLD, 20);
    Font font33 = new Font("Arial", Font.BOLD, 33);

    // UI Components
    JLabel title;
    JButton menuManagementButton, employeeManagementButton, reservationManagementButton, backButton;

    // Constructor to set up the Dashboard window
    public DashboardPage() {
        super("Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Create title label
        title = new JLabel("Dashboard", SwingConstants.CENTER);
        title.setFont(font33);
        title.setForeground(Color.BLACK);
        this.add(title, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        // Create buttons with icons
        menuManagementButton = createButton("Menu", new Dimension(200, 200), "/GUI/icons/menu.png");
        employeeManagementButton = createButton("Employee", new Dimension(200, 200), "/GUI/icons/employee.png");
        reservationManagementButton = createButton("Reservation", new Dimension(200, 200), "/GUI/icons/reservation.png");
        backButton = createButton("Back", new Dimension(200, 50), "/GUI/icons/back.png");

        // Add buttons to panel
        buttonPanel.add(menuManagementButton);
        buttonPanel.add(employeeManagementButton);
        buttonPanel.add(reservationManagementButton);

        // Create a panel for the back button
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        backPanel.add(backButton);

        // Add panels to frame
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(backPanel, BorderLayout.SOUTH);

        // Add action listeners
        menuManagementButton.addActionListener(this);
        employeeManagementButton.addActionListener(this);
        reservationManagementButton.addActionListener(this);
        backButton.addActionListener(this);

        this.setVisible(true);
    }

    // Method to create a button with an icon
    private JButton createButton(String text, Dimension size, String iconPath) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setBackground(new Color(255, 102, 102)); // Light red
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Load icon
        java.net.URL imageUrl = getClass().getResource(iconPath);
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Icon not found: " + iconPath);
        }

        return button;
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuManagementButton) {
           // JOptionPane.showMessageDialog(this, "Menu Management clicked!");
            this.dispose();
            SwingUtilities.invokeLater(MenuManagerGui::new);
        } else if (e.getSource() == employeeManagementButton) {
            //JOptionPane.showMessageDialog(this, "Employee Management clicked!");
            this.dispose();
            SwingUtilities.invokeLater(EmployeeGui::new);
        } else if (e.getSource() == reservationManagementButton) {
            //JOptionPane.showMessageDialog(this, "Reservation Management clicked!");
            this.dispose();
            SwingUtilities.invokeLater(ReservationGUI::new);
        } else if (e.getSource() == backButton) {
            System.out.println("Back button clicked!");
            this.setVisible(false);
            new Login();
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardPage::new);
    }
}
