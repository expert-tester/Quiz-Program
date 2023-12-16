/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import LogInSignUp.CreateLoginForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Group
 */
public class Main {

    private static String username;
    private static String userType;

    public static void main(String[] args) {
        startMain();
    }

    public static void startMain() {
        CreateLoginForm loginForm = new CreateLoginForm();
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setLocationRelativeTo(null);
        loginForm.setSize(400, 300);
        
        loginForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // This is executed when the loginForm is closed
                username = loginForm.getUsername();
                userType = loginForm.getUserType();
            }
        });

        loginForm.setVisible(true);
    }

    public static String showUsername() {
        return username;
    }

    public static String getUserType() {
        return userType;
    }
}
