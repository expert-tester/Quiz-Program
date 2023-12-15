package LogInSignUp;

//import required classes and packages  
import Main.AdminNavBar;
import Main.NavBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
public class CreateLoginForm extends JFrame implements ActionListener {

    //initialize button, panel, label, and text field  
    JButton b1;
    JButton b2;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;
    String username, userType;

    //calling constructor  
    public CreateLoginForm() {

        //create label for username   
        userLabel = new JLabel();
        userLabel.setText("Email/Username");      //set label value for textField1  

        //create text field to get username from the user  
        textField1 = new JTextField(15);    //set length of the text  

        //create label for password  
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2  

        //create text field to get password from the user  
        textField2 = new JPasswordField(15);    //set length for the password  

        //create submit button & submitted button
        b1 = new JButton("Login"); //set label to button  
        b2 = new JButton("Signup");
        //create panel to put form elements  
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(passLabel);    //set password label to panel  
        newPanel.add(textField2);   //set text field to panel  
        newPanel.add(b1);           //set button to panel  
        newPanel.add(b2);           //Set button to panel

        //set border to panel   
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click   
        b1.addActionListener(this);     //add action listener to button  
        b2.addActionListener(this);
        setTitle("LOGIN FORM");         //set title to the login form  
    }

    //define abstract method actionPerformed() which will be called on button click   
    @Override
    public void actionPerformed(ActionEvent ae) //pass action listener as a parameter  
    {
        String userValue = textField1.getText();        //get user entered username from the textField1  
        String passValue = textField2.getText();        //get user entered pasword from the textField2  

        if (ae.getSource() == b1) {
            // Check whether the credentials are authentic or not
            checkCredentials(userValue, passValue);

        } else if (ae.getSource() == b2) {
            JOptionPane.showMessageDialog(null, "Redirect to Signup Page");
            new signup().setVisible(true);
            dispose();
        }
    }

    private boolean checkCredentials(String userValue, String passValue) {
        String basePath = new File("").getAbsolutePath();
        File f = new File(basePath + File.separator + "database/database.txt");
        File af = new File(basePath + File.separator + "database/database_admin.txt");

        f.getParentFile().mkdirs();
        af.getParentFile().mkdirs();
        try {
            boolean file1Created = f.createNewFile();
            boolean file2Created = af.createNewFile();

            if (file1Created && file2Created) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f)); BufferedReader abr = new BufferedReader(new FileReader(af))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into fields using ',' as a delimiter
                String[] fields = line.split(",");

                // Check if the entered username or email matches
                if (fields[0].equals(userValue) || fields[1].equals(userValue)) {
                    // Check if the entered password matches
                    if (fields[4].equals(passValue)) {
                        username = fields[0];
                        userType = fields[5];
                        JOptionPane.showMessageDialog(null, "Log in Success!!!");
                        new NavBar().createGui();
                        dispose();
                        return true; // Credentials are valid
                    }
                }
            }
            while ((line = abr.readLine()) != null) {
                // Split the line into fields using ',' as a delimiter
                String[] fields = line.split(",");

                // Check if the entered username or email matches
                if (fields[0].equals(userValue) || fields[1].equals(userValue)) {
                    // Check if the entered password matches
                    if (fields[4].equals(passValue)) {
                        username = fields[0];
                        userType = fields[5];
                        JOptionPane.showMessageDialog(null, "Admin login Success!!!");
                        new AdminNavBar().createGui();
                        dispose();
                        return true; // Credentials are valid    
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Invalid username or password");
        return false; // Credentials are not valid
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }
}
