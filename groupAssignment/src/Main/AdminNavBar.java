package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class AdminNavBar implements ActionListener {

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
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel adminPanel;
    private JPanel buttonPanel;
    private JTextArea adminTextArea;
    private JButton changesButton;
    private JButton applyChangesButton;
    private JButton nextButton;
    private JButton backButton;
    private JButton searchButton;
    private JTextArea showJTextArea;
    private String filePath;
    private String fileFormat;
    private int current;

    private void updateTextArea(String newText, JTextArea editingJTextArea) {
        editingJTextArea.setText(newText);
        cardLayout.show(cardPanel, "Editing Pane");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showMainMenu) {
            adminPanel.setVisible(true);
            backButton.setEnabled(false);
            nextButton.setEnabled(false);
            filePath = "text/frontPage.txt";
            showJTextArea = FileHandling.textToJTextArea(filePath);
            cardPanel.add(showJTextArea, "Front Page");
            cardLayout.show(cardPanel, "Front Page");
            String labelText = showJTextArea.getText();
            adminTextArea.setText(labelText);
        }
        if (e.getSource() == showEducationLesson) {
            adminPanel.setVisible(true);
            backButton.setEnabled(false);
            nextButton.setEnabled(true);
            current = 1;
            fileFormat = "text/lessons/lesson%d.txt";
            editContent(fileFormat, current);
        }
        if (e.getSource() == showEducationQuiz) {
            adminPanel.setVisible(true);
            backButton.setEnabled(true);
            nextButton.setEnabled(true);
            current = 1;
            fileFormat = "text/questions/question%d.txt";
            editContent(fileFormat, current);
        }
        if (e.getSource() == showProfile) {
            adminPanel.setVisible(false);
            cardPanel.setPreferredSize(null);
            String filePath = "database/database_admin.txt";
            String name = Main.showUsername();
            String email = FileHandling.splitData(1, name, filePath);
            String address = FileHandling.splitData(2, name, filePath);
            String contactNumber = FileHandling.splitData(3, name, filePath);
            String password = FileHandling.splitData(4, name, filePath);
            Profile userProfile = new Profile(name, email, address, contactNumber, password);
            cardPanel.add(userProfile.displayProfile(frame), "Profile");
            cardLayout.show(cardPanel, "Profile");
            adminTextArea.setText(null);
        }
        if (e.getSource() == showAboutUs) {
            adminPanel.setVisible(true);
            backButton.setEnabled(false);
            nextButton.setEnabled(false);
            filePath = "text/aboutUs.txt";
            showJTextArea = FileHandling.textToJTextArea(filePath);
            cardPanel.add(showJTextArea, "About Us");
            cardLayout.show(cardPanel, "About Us");
            String labelText = showJTextArea.getText();
            adminTextArea.setText(labelText);

        }
        if (e.getSource() == showHelp) {
            adminPanel.setVisible(true);
            backButton.setEnabled(false);
            nextButton.setEnabled(false);
            filePath = "text/help.txt";
            showJTextArea = FileHandling.textToJTextArea(filePath);
            cardPanel.add(showJTextArea, "Help");
            cardLayout.show(cardPanel, "Help");
            String labelText = showJTextArea.getText();
            adminTextArea.setText(labelText);
        }
        if (e.getSource() == showLogOut) {
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

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
        cardLayout = new CardLayout(5, 5);
        cardPanel = new JPanel(cardLayout);
        cardPanel.setPreferredSize(new Dimension(400, 250));

        //ActionListener for NavBar
        showMainMenu.addActionListener(this);
        showEducationLesson.addActionListener(this);
        showEducationQuiz.addActionListener(this);
        showProfile.addActionListener(this);
        showAboutUs.addActionListener(this);
        showHelp.addActionListener(this);
        showLogOut.addActionListener(this);

        //Admin
        adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminTextArea = new JTextArea();
        Border adminBorder = BorderFactory.createEmptyBorder(10, 10, 50, 20);
        adminTextArea.setBorder(adminBorder);

        //Buttons
        changesButton = new JButton("Change");
        applyChangesButton = new JButton("Apply Changes");
        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        searchButton = new JButton("Search");

        //Admin Buttons Action listener
        AdminButtons adminButtonsListener = new AdminButtons();
        changesButton.addActionListener(adminButtonsListener);
        applyChangesButton.addActionListener(adminButtonsListener);
        nextButton.addActionListener(adminButtonsListener);
        backButton.addActionListener(adminButtonsListener);
        searchButton.addActionListener(adminButtonsListener);

        // Set layout for buttons to make it horizontal
        FlowLayout buttonLayout = new FlowLayout(FlowLayout.CENTER);
        buttonPanel = new JPanel(buttonLayout);
        buttonPanel.add(changesButton);
        buttonPanel.add(applyChangesButton);
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(searchButton);
        backButton.setEnabled(false);
        nextButton.setEnabled(false);

        adminPanel.add(new JLabel("Editing Text:"));
        adminPanel.add(adminTextArea);
        adminPanel.add(buttonPanel);

        //NavBar frame show
        frame.setJMenuBar(menuBar);
        frame.add(cardPanel, BorderLayout.NORTH);
        frame.add(adminPanel, BorderLayout.SOUTH);

        //NavBar frame settings
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void editContent(String fileFormat, int current) {
        filePath = String.format(fileFormat, current);
        showJTextArea = FileHandling.textToJTextArea(filePath);
        cardPanel.add(showJTextArea, "Education");
        cardLayout.show(cardPanel, "Education");
        String textArea = showJTextArea.getText();
        adminTextArea.setText(textArea);
        adminTextArea.setLineWrap(true);
        adminTextArea.setWrapStyleWord(true);
    }

    public class AdminButtons implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == changesButton) {
                updateTextArea(adminTextArea.getText(), showJTextArea);
            } else if (e.getSource() == applyChangesButton) {
                int result = JOptionPane.showConfirmDialog(frame, "Apply changes?", "Apply Changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    FileHandling.setFileContent(adminTextArea.getText(), filePath, false);
                }
            } else if (e.getSource() == nextButton) {
                if (current == EducationLesson.maxLesson() - 1) {
                    nextButton.setEnabled(false);
                    backButton.setEnabled(true);
                    current++;
                    editContent(fileFormat, current);
                } else if (current < EducationLesson.maxLesson()) {
                    backButton.setEnabled(true);
                    current++;
                    editContent(fileFormat, current);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "File out or range!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else if (e.getSource() == backButton) {
                if (current > 2) {
                    nextButton.setEnabled(true);
                    current--;
                    editContent(fileFormat, current);
                } else if (current > 1) {
                    backButton.setEnabled(false);
                    current--;
                    editContent(fileFormat, current);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "File out or range!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else if (e.getSource() == searchButton) {
                String searchText = JOptionPane.showInputDialog(new JFrame(), "Search Text.").toLowerCase();
                String textArea = adminTextArea.getText().toLowerCase();
                if (textArea.contains(searchText)) {
                    int start = textArea.indexOf(searchText);
                    int end = start + searchText.length();

                    adminTextArea.setSelectionStart(start);
                    adminTextArea.setSelectionEnd(end);
                    adminTextArea.requestFocusInWindow();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Text not found.");
                }
                System.out.println(searchText);
            }
        }
    }
}
