package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileHandling {

    public static BufferedReader getFileContent(String filePath) {

        try {
            FileReader reader = new FileReader(filePath);
            return new BufferedReader(reader);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }

        return null;
    }

    public static void setFileContent(String text, String filePath, Boolean appendable) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            try (FileWriter writer = new FileWriter(filePath, appendable); BufferedWriter writeFile = new BufferedWriter(writer)) {
                writeFile.write(text);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String showMessage(BufferedReader readFile) {
        String message = "";
        String line;

        try {
            do {
                line = readFile.readLine();
                if (line != null) {
                    message += line + "\n";
                }
            } while (line != null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }

        return message;
    }

    public static ArrayList<String> textToArrayString(String filePath) {
        BufferedReader reader = FileHandling.getFileContent(filePath);
        ArrayList<String> arrayLines = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line.isEmpty() ? null : line);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return arrayLines;
    }

    public static ArrayList<Integer> textToArrayInt(String filePath) {
        BufferedReader reader = FileHandling.getFileContent(filePath);
        ArrayList<Integer> arrayInt = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                int score = (line != null && !line.isEmpty()) ? Integer.parseInt(line) : 0;
                arrayInt.add(score);
            }
        } catch (IOException ex) {
            return arrayInt;
        }
        return arrayInt;
    }

    public static String getLineByIndex(int index, ArrayList<String> arrayLines) {
        arrayLines.removeIf(Objects::isNull);
        if (index >= 0 && index < arrayLines.size()) {
            return arrayLines.get(index);
        }
        return null;
    }

    public static boolean checkFileExist(String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }
    
    public static boolean checkDirectoryExist(String filePath) {
        File f = new File(filePath);
        return f.exists() && f.isDirectory();
    }

    public static JTextArea textToJTextArea(String filePath) {
        BufferedReader reader = FileHandling.getFileContent(filePath);
        String text = FileHandling.showMessage(reader);

        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        return textArea;
    }

    public static String splitData(int number, String searchString, String filePath) {
        BufferedReader reader = FileHandling.getFileContent(filePath);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    String[] fields = line.split(",");
                    return fields[number];
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public static String replaceLine(String filePath, String searchString, String replacementString) {
        StringBuilder updatedLine = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    line = replacementString;
                }
                updatedLine.append(line).append("\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return updatedLine.toString();
    }
}
