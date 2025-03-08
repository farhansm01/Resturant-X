package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import File.FileIO;
import java.io.IOException;

public class Regis extends JFrame implements ActionListener {
    Font font20 = new Font("Cambria", Font.BOLD, 20);

    JLabel nameLabel, emailLabel, passwordLabel, confirmPasswordLabel, genderLabel;
    JTextField nameTextField, emailTextField;
    JPasswordField passwordField, confirmPasswordField;
    JComboBox<String> genderComboBox;
    JCheckBox termsCheckBox;
    JButton registerButton, backButton;

    public Regis() {
        super("Registration");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(20, 24, 82));

        // Labels and Input Fields
        nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(font20);
        nameLabel.setBounds(60, 20, 200, 30);
        this.add(nameLabel);

        nameTextField = new JTextField(15);
        nameTextField.setBounds(60, 50, 200, 30);
        this.add(nameTextField);

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(font20);
        emailLabel.setBounds(60, 80, 200, 30);
        this.add(emailLabel);

        emailTextField = new JTextField(15);
        emailTextField.setBounds(60, 110, 200, 30);
        this.add(emailTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(font20);
        passwordLabel.setBounds(400, 20, 200, 30);
        this.add(passwordLabel);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(400, 50, 200, 30);
        this.add(passwordField);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(font20);
        confirmPasswordLabel.setBounds(400, 80, 200, 30);
        this.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setBounds(400, 110, 200, 30);
        this.add(confirmPasswordField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setFont(font20);
        genderLabel.setBounds(400, 140, 200, 30);
        this.add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderComboBox.setBounds(400, 170, 200, 30);
        this.add(genderComboBox);

        termsCheckBox = new JCheckBox("I agree to the terms and conditions.");
        termsCheckBox.setForeground(Color.WHITE);
        termsCheckBox.setBackground(new Color(20, 24, 82));
        termsCheckBox.setBounds(55, 270, 400, 30);
        this.add(termsCheckBox);

        registerButton = new JButton("Register");
        registerButton.setBounds(250, 300, 200, 40);
        registerButton.addActionListener(this);
        this.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(480, 300, 200, 40);
        backButton.addActionListener(this);
        this.add(backButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String uname = nameTextField.getText().trim();
            String email = emailTextField.getText().trim();
            String upass = new String(passwordField.getPassword()).trim();
            String cpass = new String(confirmPasswordField.getPassword()).trim();
            
			if (uname.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(this, "You cannot register as 'admin'!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            if (upass.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Password 'admin123' is reserved for admin!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
			
            if (!uname.matches("^[a-zA-Z]+$")){
				 JOptionPane.showMessageDialog(this, "Username can only contain letters (A-Z or a-z)!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
			}


            if (!upass.equals(cpass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
			
            if (FileIO.userExists(uname)) {
                JOptionPane.showMessageDialog(this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                FileIO.addUser(uname, email, upass);
                JOptionPane.showMessageDialog(this, "Registration successful!");
                this.dispose();
                new Login();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            new Login();
        }
    }
}
