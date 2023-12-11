package LogInSignUp;

import Main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class signup extends JFrame implements ActionListener {

    JLabel nameLabel, emailLabel, addressLabel, contactNumberLabel, passwordLabel, userTypeLabel;
    JTextField nameField, emailField, addressField, contactNumberField;
    JPasswordField passwordField;
    JButton signUpButton, backButton;
    JComboBox<String> userTypeComboBox;
    String getUserType;

    signup() {
        // Set up the frame
        setTitle("Sign Up");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        addressLabel = new JLabel("Address:");
        contactNumberLabel = new JLabel("Contact Number:");
        passwordLabel = new JLabel("Password:");
        userTypeLabel = new JLabel("User type: ");
        String[] userTypes = {"User", "Admin"};
        userTypeComboBox = new JComboBox<>(userTypes);

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        contactNumberField = new JTextField(20);
        passwordField = new JPasswordField(20);

        signUpButton = new JButton("Sign Up");
        backButton = new JButton("Back");

        // Set up layout
        setLayout(new GridLayout(7, 2));
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(addressLabel);
        add(addressField);
        add(contactNumberLabel);
        add(contactNumberField);
        add(passwordLabel);
        add(passwordField);
        add(userTypeLabel);
        add(userTypeComboBox);
        add(backButton);
        add(signUpButton);

        // Add action listener to the sign-up button
        signUpButton.addActionListener((ActionListener) this);
        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.startMain();

                // Close the signup window
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            // Collect user information
            String name = nameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String contactNumber = contactNumberField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            String userType = (String) userTypeComboBox.getSelectedItem();
            getUserType = userType;

            // Validate that fields are not empty
            if (isEmpty(name) || isEmpty(email) || isEmpty(address) || isEmpty(contactNumber) || isEmpty(passwordChars)) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Do not proceed with signup if any field is empty
            } else if (!Validation.validateEmail(email)) {
                JOptionPane.showMessageDialog(this, "Please check your email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!Validation.validatePhoneNumber(contactNumber)) {
                JOptionPane.showMessageDialog(this, "Please check your phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!Validation.validatePassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must contains minimum eight characters with at least one letter, one number and one special character", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save user information to a text file
            saveToTextFile(name, email, address, contactNumber, password, userType);

            // Display a success message
            JOptionPane.showMessageDialog(this, "Sign up successful! Please log in again.");
            
            Main.startMain();
            
            // Close the sign-up window
            dispose();
        }
    }

    private boolean isEmpty(String value) {
        return value.trim().isEmpty();
    }

    private boolean isEmpty(char[] value) {
        return value.length == 0;
    }

    private void saveToTextFile(String name, String email, String address, String contactNumber, String password, String userType) {
        try {

            String fileName = (userType.equals("Admin")) ? "database/database_admin.txt" : "database/database.txt";
            File f = new File(fileName);

            try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, true))) {
                pw.println(name + ',' + email + ',' + address + ',' + contactNumber + ',' + password + ',' + userType);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving user information");
        }
    }

    public String getUserType() {
        return getUserType;
    }

}
