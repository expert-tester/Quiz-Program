/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Chun On
 */
public class FrontPage {

    private static final String FILE_PATH = "text/frontPage.txt";

    public static void buildFrontPage(int language) {
        String message = "";
        switch (language) {
            case 1:
                message += """
                           Welcome to Program Name
                           
                           Protect Earth NOW!!!
                           
                           Join us with this AMAZING journey to learn about SDG!""";
                FileHandling.setFileContent(message, FILE_PATH, false);

            // Language can be added
        }
    }

    public static JPanel showFrontPage(JFrame frame, int language) {
        String title = "Program Name";
        BufferedReader readFrontPage = FileHandling.getFileContent(FILE_PATH);
        String message = FileHandling.showMessage(readFrontPage);

        GridLayout gridLayout = new GridLayout(3, 1);

        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);
        
        String[] messageParts = message.split("\n\n");
        
        for (int i = 0; i < messageParts.length; i++) {
            JLabel labelMessage = new JLabel(messageParts[i]);
            Font labelFont = null;

            switch (i) {
                case 0:
                    labelFont = new Font("STSong", Font.BOLD, 50);
                    break;
                case 1:
                    labelFont = new Font("STSong", Font.PLAIN, 30);
                    ImageIcon earth = new ImageIcon("image/earth.png");
                    labelMessage.setIcon(earth);
                    break;
                default:
                    labelFont = new Font("STSong", Font.PLAIN, 30);
                    break;
            }
            labelMessage.setFont(labelFont);
            labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(labelMessage);
        }

        frame.setTitle(title);
        frame.add(panel);

        return panel;
    }

}
