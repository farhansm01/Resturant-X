package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import File.FileIO;

public class Login extends JFrame implements ActionListener {
    // Font styles for labels and buttons
    Font font18 = new Font("Cambria", Font.BOLD, 18);
    Font font20 = new Font("Cambria", Font.BOLD, 20);
    Font font30 = new Font("Arial", Font.BOLD, 30);

    // UI Components
    JLabel title, userLabel, passwordLabel, loginLabel, background;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton, resignationButton;
    ImageIcon backgroundImageIcon;

    public Login() {
        super("Restaurant X");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setFont(font20);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Title label setup
        loginLabel = new JLabel("Restaurant I");
        loginLabel.setFont(font30);
        loginLabel.setBounds(630, 50, 200, 40);
        this.add(loginLabel);

        // Username label and text field setup
        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(535, 170, 150, 25);
        userLabel.setFont(font20);
        this.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(650, 170, 200, 25);
        userTextField.setFont(font18);
        this.add(userTextField);

        // Password label and password field setup
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(535, 220, 150, 25);
        passwordLabel.setFont(font20);
        passwordLabel.setForeground(Color.BLACK);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(650, 220, 200, 25);
        passwordField.setFont(font18);
        this.add(passwordField);

        // Login button setup
        loginButton = new JButton("Log In");
        loginButton.setBounds(530, 270, 130, 30);
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        loginButton.setFont(font18);
        this.add(loginButton);

        // Registration button setup
        resignationButton = new JButton("Register");
        resignationButton.setBounds(690, 270, 130, 30);
        resignationButton.setBackground(Color.RED);
        resignationButton.setForeground(Color.WHITE);
        resignationButton.addActionListener(this);
        resignationButton.setFont(font18);
        this.add(resignationButton);

        // Background image setup
        backgroundImageIcon = new ImageIcon(getClass().getResource("/GUI/iftee.jpg"));
        background = new JLabel("", backgroundImageIcon, JLabel.CENTER);
        background.setBounds(0, 0, 1000, 700);
        this.add(background);
        background.setLayout(null);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String uname = userTextField.getText();
            String upass = new String(passwordField.getPassword());


            if (uname.equals("admin") && upass.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Admin Login Successful");
                this.dispose();  
                new DashboardPage();  
                return;
            }

            if (FileIO.checkUser(uname, upass)) {
                JOptionPane.showMessageDialog(this, "User Login Successful");
                this.dispose();  
                new MenuGui();  
                return;
            } else {
                showErrorGif(); 
            }
        }

        else if (e.getSource() == resignationButton) {
            this.dispose(); 
            new Regis(); 
        }
    }

    private void showErrorGif() {
        JDialog errorDialog = new JDialog(this, "Login Failed", true);
        errorDialog.setSize(300, 200);
        errorDialog.setLocationRelativeTo(this);
        errorDialog.setLayout(new BorderLayout());

        JLabel gifLabel = new JLabel(new ImageIcon(getClass().getResource("/GUI/ae.gif"))); // Add GIF
        errorDialog.add(gifLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Try Again");
        closeButton.setFont(font18);
		closeButton.setBackground(Color.WHITE);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(e -> errorDialog.dispose());

        errorDialog.add(closeButton, BorderLayout.SOUTH);
        errorDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
