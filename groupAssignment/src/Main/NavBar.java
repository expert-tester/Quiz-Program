package Main;

import static Main.AboutUs.buildAboutUs;
import static Main.AboutUs.showAboutUs;
import static Main.Help.buildHelp;
import static Main.Help.showHelp;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel; //test might delete later
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Chun On
 */
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
    private JPanel cardPanel;
    private CardLayout cardLayout;
    public int language = 1;

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

        //NavBar frame settings
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showMainMenu) {
            System.out.println("Main Menu"); //change later
        }
        if (e.getSource() == showEducationLesson) {
            System.out.println("Education Lesson"); //change later
        }
        if (e.getSource() == showEducationQuiz) {
            System.out.println("Educatin Quiz"); //change later
        }
        if (e.getSource() == showProfile) {
            System.out.println("Profile"); //change later
        }
        if (e.getSource() == showAboutUs) {
            if (FileHandling.checkFileExist("text/aboutUs.txt")) {
                showAboutUs(language);
            } else {
                buildAboutUs(language);
                showAboutUs(language);
            }
        }

        if (e.getSource() == showHelp) {
            if (FileHandling.checkFileExist("text/help.txt")) {
                showHelp(language);
            } else {
                buildHelp(language);
                showHelp(language);
            }
        }
        if (e.getSource() == showLogOut) {
            System.out.println("Log Out"); //change later
        }
    }

}
