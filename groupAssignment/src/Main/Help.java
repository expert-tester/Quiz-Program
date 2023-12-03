/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.BufferedReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Chun On
 */
public class Help {

    private static final String FILE_PATH = "text/help.txt";
//    public static void main(String[] args) {
//        if (FileHandling.checkFileExist(FILE_PATH)) {
//            showHelp();
//        }
//        else {
//            buildHelp(1);
//            showHelp();
//        }
//    }

    public static void buildHelp(int language) {
        String message = "";
        switch (language) {
            case 1:
                message += "Help:\nIf you need help or consultation, please contact our team by email, email:adminhelp@sdg13.com.\n\n";
                message += """
                           Feedback:
                           If you have any comments to provide us for improvement, please send them to email:adminfeedback@sdg13.com.
                           
                           Thank you for joining us and moving us together into a sustainable future!""";
                FileHandling.setFileContent(message,FILE_PATH);

            // Language can be added
        }
    }

    public static void showHelp(int language) {
        String title = "Help";
        BufferedReader readAboutUs = FileHandling.getFileContent(FILE_PATH);
        String message = FileHandling.showMessage(language, readAboutUs); // 1 for English
        JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Close"}, "Close");
    }

}
