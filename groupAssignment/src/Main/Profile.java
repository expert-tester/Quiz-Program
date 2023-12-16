/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import LogInSignUp.Validation;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

/**
 *
 * @author Ng Jacey
 */
public class Profile {

    private String name, email, address, contactNumber, password;
    private ArrayList<Integer> scoreHistory;
    private int highestScore;
    private final ArrayList<String> originalValues = new ArrayList<>();
    private String filePath = "database/database.txt";
    String username = Main.showUsername();
    String userType = Main.getUserType();

    public Profile(String name, String email, String address, String contactNumber, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.password = password;
        this.scoreHistory = ("Admin".equals(userType)) ? new ArrayList<>() : FileHandling.textToArrayInt("text/score/" + username + "/score.txt");
        this.highestScore = ("Admin".equals(userType)) ? 0 : (!scoreHistory.isEmpty()) ? Collections.max(scoreHistory) : 0;
    }

    public JPanel displayProfile(JFrame frame) {

        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel headerLabel = new JLabel("Profile");
        headerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headerLabel);
        panel.add(new JLabel(""));  // Add an empty space in the second column

        JTextField nameField = createEditableField(name);
        JTextField addressField = createEditableField(address);
        JTextField contactNumberField = createEditableField(contactNumber);
        JTextField passwordField = createEditableField(password);
        JLabel scoreHistoryLabel = new JLabel(scoreHistory.toString());
        JLabel highestScoreLabel = new JLabel(Integer.toString(highestScore));

        JButton editButton = new JButton("Edit Info");
        JButton doneButton = new JButton("Done");
        JButton cancelButton = new JButton("Cancel");

        doneButton.addActionListener(e1 -> {
            getText(nameField, addressField, contactNumberField, passwordField);
            if (!Validation.validatePhoneNumber(contactNumber)) {
                JOptionPane.showMessageDialog(new JFrame(), "Please check your phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!Validation.validatePassword(password)) {
                JOptionPane.showMessageDialog(new JFrame(), "Password must contains minimum eight characters with at least one letter, one number and one special character", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            updateInfo(nameField, addressField, contactNumberField, passwordField);
            JOptionPane.showMessageDialog(null, "Your information has been updated successfully!");
            disableFields(nameField, addressField, contactNumberField, passwordField);
            panel.remove(doneButton);
            panel.remove(cancelButton);
            panel.add(editButton);
            frame.revalidate();
            frame.repaint();
        });

        cancelButton.addActionListener(e2 -> {
            resetFields(nameField, addressField, contactNumberField, passwordField);
            disableFields(nameField, addressField, contactNumberField, passwordField);
            panel.remove(doneButton);
            panel.remove(cancelButton);
            panel.add(editButton);
            frame.revalidate();
            frame.repaint();
        });

        editButton.addActionListener(e -> {
            enableFields(nameField, addressField, contactNumberField, passwordField);
            panel.remove(editButton);
            panel.add(doneButton);
            panel.add(cancelButton);
            frame.revalidate();
            frame.repaint();
        });

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactNumberField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Score History:"));
        panel.add(scoreHistoryLabel);
        panel.add(new JLabel("Highest Score:"));
        panel.add(highestScoreLabel);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(editButton);

        return panel;
    }

    private JTextField createEditableField(String text) {
        JTextField field = new JTextField(text);
        field.setEditable(false);
        return field;
    }

    private void enableFields(JTextField... fields) {
        originalValues.clear();
        for (JTextField field : fields) {
            originalValues.add(field.getText());
            field.setEditable(true);
        }
    }

    private void disableFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setEditable(false);
        }
    }

    private void getText(JTextField... fields) {
        name = fields[0].getText();
        address = fields[1].getText();
        contactNumber = fields[2].getText();
        password = fields[3].getText();
    }

    private void updateInfo(JTextField... fields) {
        for (JTextField field : fields) {
            field.setEditable(false);
        }

        getText(fields);
        filePath = ("Admin".equals(userType)) ? "database/database_admin.txt" : filePath;
        String joinField = String.join(",", name, email, address, contactNumber, password, userType);
        String updatedInfo = FileHandling.replaceLine(filePath, username, joinField);
        FileHandling.setFileContent(updatedInfo, filePath, Boolean.FALSE);
    }

    private void resetFields(JTextField... fields) {
        int index = 0;
        for (JTextField field : fields) {
            field.setText(originalValues.get(index++));
        }
    }
}
