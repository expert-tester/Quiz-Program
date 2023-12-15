package Main;

import static Main.FrontPage.buildFrontPage;
import static Main.FrontPage.showFrontPage;
import static Main.AboutUs.buildAboutUs;
import static Main.AboutUs.showAboutUs;
import static Main.EducationLesson.buildLesson;
import static Main.Help.buildHelp;
import static Main.Help.showHelp;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static Main.EducationQuiz.buildQuiz;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class NavBar implements ActionListener {

    //Define variables
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu educationMenu;
    private JMenu profileMenu;
    private JMenu aboutUsMenu;
    private JMenu helpMenu;
    private JMenu logOutMenu;
    private JMenuItem showMainMenu;
    private JMenuItem showEducationLesson;
    private JMenuItem showEducationQuiz;
    private JMenuItem showProfile;
    private JMenuItem showAboutUs;
    private JMenuItem showHelp;
    private JMenuItem showLogOut;
    private JButton nextButton;
    private JButton backButton;
    private JPanel cardPanel;
    private JPanel buttonPanel;
    private CardLayout cardLayout;
    private int current;
    public int language = 1; //change?

    public void createGui() {
        //NavBar frame
        frame = new JFrame("Quiz Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //NavBar menu
        menuBar = new JMenuBar();
        mainMenu = new JMenu("Main Menu");
        educationMenu = new JMenu("Education");
        profileMenu = new JMenu("Profile");
        aboutUsMenu = new JMenu("About Us");
        helpMenu = new JMenu("Help");
        logOutMenu = new JMenu("Log Out");

        //NavBar menu item
        showMainMenu = new JMenuItem("Show Main Menu");
        showEducationLesson = new JMenuItem("Lesson");
        showEducationQuiz = new JMenuItem("Quiz");
        showProfile = new JMenuItem("Show Profile");
        showAboutUs = new JMenuItem("Show About Us");
        showHelp = new JMenuItem("Show Help");
        showLogOut = new JMenuItem("Log Out Account");

        //NavBar content
        menuBar.add(mainMenu);
        menuBar.add(educationMenu);
        menuBar.add(profileMenu);
        menuBar.add(aboutUsMenu);
        menuBar.add(helpMenu);
        menuBar.add(logOutMenu);

        //Menu Item content
        mainMenu.add(showMainMenu);
        educationMenu.add(showEducationLesson);
        educationMenu.add(showEducationQuiz);
        profileMenu.add(showProfile);
        aboutUsMenu.add(showAboutUs);
        helpMenu.add(showHelp);
        logOutMenu.add(showLogOut);

        //Card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //Default card
        if (FileHandling.checkFileExist("text/frontPage.txt")) {
            cardPanel.add(showFrontPage(frame, language), "frontPage");
            cardLayout.show(cardPanel, "frontPage");
        } else {
            buildFrontPage(language);
            cardLayout.show(showFrontPage(frame, language), "frontPage");
        }

        //Buttons for lessons
        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        nextButton.addActionListener(this);
        backButton.addActionListener(this);
        nextButton.setPreferredSize(new Dimension(50, 50));
        backButton.setPreferredSize(new Dimension(50, 50));
        buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(nextButton, BorderLayout.SOUTH);
        buttonPanel.add(backButton, BorderLayout.SOUTH);

        //ActionListener for NavBar
        showMainMenu.addActionListener(this);
        showEducationLesson.addActionListener(this);
        showEducationQuiz.addActionListener(this);
        showProfile.addActionListener(this);
        showAboutUs.addActionListener(this);
        showHelp.addActionListener(this);
        showLogOut.addActionListener(this);

        //NavBar frame show
        frame.setJMenuBar(menuBar);
        frame.add(cardPanel);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setVisible(false);

        //NavBar frame settings
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showMainMenu) {
            buttonPanel.setVisible(false);
            cardLayout.show(cardPanel, "frontPage");
        }
        if (e.getSource() == showEducationLesson) {
            buttonPanel.setVisible(true);
            backButton.setEnabled(false);
            if (FileHandling.checkDirectoryExist("text/lessons")) {
                current = 1;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
            } else {
                buildLesson();
                current = 1;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
                frame.add(buttonPanel, BorderLayout.SOUTH);
            }
        }
        if (e.getSource() == showEducationQuiz) {
            buttonPanel.setVisible(false);
            if (FileHandling.checkDirectoryExist("text/questions")) {
                EducationQuiz educationQuiz = new EducationQuiz();
                cardPanel.add(educationQuiz, "EducationQuiz");
                cardLayout.show(cardPanel, "EducationQuiz");
            } else {
                buildQuiz();
                EducationQuiz educationQuiz = new EducationQuiz();
                cardPanel.add(educationQuiz, "EducationQuiz");
                cardLayout.show(cardPanel, "EducationQuiz");
            }
        }
        if (e.getSource() == showProfile) {
            buttonPanel.setVisible(false);
            String filePath = "database/database.txt";
            String name = Main.showUsername();
            String email = FileHandling.splitData(1, name, filePath);
            String address = FileHandling.splitData(2, name, filePath);
            String contactNumber = FileHandling.splitData(3, name, filePath);
            String password = FileHandling.splitData(4, name, filePath);
            Profile userProfile = new Profile(name, email, address, contactNumber, password);
            
            cardPanel.add(userProfile.displayProfile(frame), "Profile");
            cardLayout.show(cardPanel, "Profile");
        }
        if (e.getSource() == showAboutUs) {
            if (FileHandling.checkFileExist("text/aboutUs.txt")) {
                showAboutUs();
            } else {
                buildAboutUs(language);
                showAboutUs();
            }
        }
        
        if (e.getSource() == showHelp) {
            if (FileHandling.checkFileExist("text/help.txt")) {
                showHelp();
            } else {
                buildHelp(language);
                showHelp();
            }
        }
        if (e.getSource() == showLogOut) {
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        
        if (e.getSource() == nextButton) {
            if (current == EducationLesson.maxLesson()-1) {
                nextButton.setEnabled(false);
                backButton.setEnabled(true);
                current++;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
            } else if (current < EducationLesson.maxLesson()) {
                backButton.setEnabled(true);
                current++;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "File out or range!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            if (current > 2) {
                nextButton.setEnabled(true);
                current--;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
            } else if (current > 1) {
                backButton.setEnabled(false);
                current--;
                JTextArea lesson = EducationLesson.showLesson(current);
                cardPanel.add(lesson, "EducationLesson");
                cardLayout.show(cardPanel, "EducationLesson");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "File out or range!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
}
