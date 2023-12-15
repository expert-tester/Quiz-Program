package Main;

import java.io.BufferedReader;
import javax.swing.JOptionPane;

/**
 *
 * @author HongZhe
 */
public class AboutUs {
    private static final String FILE_PATH = "text/aboutUs.txt";

    public static void buildAboutUs(int language) {
        String message = "Welcome to our program!\n\n";
        // Language can be added
        switch (language) {
            case 1:
                message += "Objective:\nOur program is dedicated to solving the Target 13.3 in SDG 13.\nIts main goal is improve education, awareness-raising, and human and institutional capacity on climate change mitigation, adaptation, impact reduction, and early warning.\n\n";
                message += "Introduction:\nClimate change is a global challenge that requires the concerted efforts of people around the world.\nOur program aims to provide resources and knowledge to individuals and society,\nand to raise people's awareness of climate change in order to actively participate in climate action.\nWe foster people's sense of responsibility to sustainable practice through educational advocacy and awareness promotion activities.\n\n";
                FileHandling.setFileContent(message,FILE_PATH, false);
        }
    }

    public static void showAboutUs() {
        String title = "About Us";
        BufferedReader readAboutUs = FileHandling.getFileContent(FILE_PATH);
        String message = FileHandling.showMessage(readAboutUs); 
        int option = showInformationDialog(message, title);

        if (option == 0) {
            if (FileHandling.checkFileExist("text/help.txt")) {
                Help.showHelp();
            } else {
                Help.buildHelp(1);
                Help.showHelp();
            }
        }
    }

    private static int showInformationDialog(String message, String title) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Help", "Close"}, "Close");
    }

}
