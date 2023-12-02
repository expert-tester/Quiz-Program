package Main;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //test might delete later
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Chun On
 */
public class AdminMain implements ActionListener{
    
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
    private JPanel adminPanel;
    private JTextArea adminTextArea;
    private JButton applyButton;
    private JLabel labelTest; //test
    private JLabel editingLabel;
    
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
        
        //Admin
//        String labelText = testContent.getLabelText(labelTest);
        adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminTextArea = new JTextArea();
//        adminTextArea.setText(labelText);
        Border adminBorder = BorderFactory.createEmptyBorder(0, 0, 50, 0);
        adminTextArea.setBorder(adminBorder);
        applyButton = new JButton("Apply");
        applyButton.setBorder(adminBorder);
        adminPanel.add(new JLabel("Admin Text:"));
        adminPanel.add(adminTextArea);
        adminPanel.add(applyButton);
        
        // Action listener for the Apply button
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to update the text based on admin input
                updateLabelText(adminTextArea.getText(),labelTest);
            }
        });
        
        //NavBar frame show
        frame.setJMenuBar(menuBar);
        frame.add(cardPanel,BorderLayout.NORTH);
        frame.add(adminPanel, BorderLayout.SOUTH);
        
        //NavBar frame settings
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
        private void updateLabelText(String newText, JLabel editingLabel) {
        // Assuming you have a method like createProfile that adds the JLabel
        JLabel updatedLabel = editingLabel;
        updatedLabel.setText(newText);
        cardPanel.add(updatedLabel, "Profile"); // Add the updated label to the cardPanel
        cardLayout.show(cardPanel, "Profile");
    }
    
        public void actionPerformed(ActionEvent e){
        if (e.getSource()== showMainMenu) {
            labelTest = testContent.createEducation();
            String name =  "Main menu";
            cardPanel.add(labelTest, name);
            cardLayout.show(cardPanel, name);
            String labelText = labelTest.getText();
            adminTextArea.setText(labelText);
        }
        if (e.getSource()== showEducationLesson) {
            labelTest = testContent.createProfile(); //change later
            String name =  "Education Lesson";
            cardPanel.add(labelTest, name);
            cardLayout.show(cardPanel, name);
            String labelText = labelTest.getText();
            adminTextArea.setText(labelText);
        }
        if (e.getSource()== showEducationQuiz)
            System.out.println("Educatin Quiz"); //change later
        if (e.getSource()== showProfile)
            System.out.println("Profile"); //change later
        if (e.getSource()== showAboutUs)
            System.out.println("About Us"); //change later
        if (e.getSource()== showHelp)
            System.out.println("Help"); //change later
        if (e.getSource()== showLogOut)
            System.out.println("Log Out"); //change later
    }
    

    
}
