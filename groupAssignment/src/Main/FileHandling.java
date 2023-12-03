/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Chun On
 */
public class FileHandling {

    public static BufferedReader getFileContent(String filePath) {

        try {
            FileReader reader = new FileReader(filePath);
            return new BufferedReader(reader);
        } catch (IOException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void setFileContent(String text, String filePath) {
        new File("text").mkdir();
        try (FileWriter writer = new FileWriter(filePath); BufferedWriter writeFile = new BufferedWriter(writer)) {
            writeFile.write(text);
        } catch (IOException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String showMessage(int language, BufferedReader readFile) {
        String message = "";
        String line;

        try {
            while ((line = readFile.readLine()) != null) {
                message += line + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }

    public static boolean checkFileExist(String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

    public static JTextArea textToJTextArea(String filePath, int language) {
        BufferedReader reader = FileHandling.getFileContent(filePath);
        String text = FileHandling.showMessage(language, reader);

        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        return textArea;
    }

}
