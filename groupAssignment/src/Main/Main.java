package Main;

import LogInSignUp.CreateLoginForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Main {

    private static String username;
    private static String userType;

    public static void main(String[] args) { //add language choice, change language, file checking lesson - quiz, sign return
        startMain();
    }

    public static void startMain() {
        CreateLoginForm loginForm = new CreateLoginForm();
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setLocationRelativeTo(null);
        loginForm.setSize(400, 300);
        loginForm.setVisible(true);

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
