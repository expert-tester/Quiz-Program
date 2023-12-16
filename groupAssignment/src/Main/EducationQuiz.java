/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 *
 * @author Ou Kai Ying
 */
public class EducationQuiz extends JPanel implements ActionListener {

    JPanel panel;
    JLabel label;
    JRadioButton radioButtons[] = new JRadioButton[5];
    JButton nextButton, backButton, resultsButton;
    ButtonGroup bg;
    int count = 0, current = 0, totalCorrect = 0;
    Timer timer;
    int secondsPassed;
    boolean checkResults = true;

    // THIS KEEPS TRACK OF CORRECTNESS OF ANSWERS
    boolean[] userResponses = new boolean[6];

    EducationQuiz() {
        setLayout(new GridLayout(0, 1));
        panel = new JPanel(new GridLayout(0, 1));
        label = new JLabel();
        panel.add(label);
        bg = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            panel.add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }

        nextButton = new JButton("Next");
        backButton = new JButton("Back");
        resultsButton = new JButton("Results");
        resultsButton.setVisible(false);

        resultsButton.addActionListener(this);
        nextButton.addActionListener(this);
        backButton.addActionListener(this);

        panel.add(resultsButton);
        panel.add(nextButton);
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
            }
        });

        setQuiz();
    }

    void startTimer() {
        timer.start();
    }

    void stopTimer() {
        timer.stop();
    }

    void setQuiz() {
        bg.clearSelection();
        backButton.setEnabled(current > 0);
        startTimer();

        String questionFilePath = "text/questions/question" + (current + 1) + ".txt";
        ArrayList questionArray = FileHandling.textToArrayString(questionFilePath);
        label.setText(FileHandling.getLineByIndex(0, questionArray));
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        label.setFont(new Font("Boulder", Font.TYPE1_FONT, 24));

        for (int i = 0; i < radioButtons.length - 1; i++) {
            radioButtons[i].setText(FileHandling.getLineByIndex(i + 1, questionArray));
            radioButtons[i].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            radioButtons[i].setFont(new Font("Boulder", Font.TYPE1_FONT, 24));
        }

        for (int i = 0; i < radioButtons.length - 1; i++) {
            radioButtons[i].setBounds(50, 80 + i * 30, 500, 20);
        }

        nextButton.setBounds(100, 240, 100, 30);
        backButton.setBounds(210, 240, 100, 30);
        resultsButton.setBounds(320, 240, 100, 30);
    }

    boolean checkAns() {
        ButtonModel selectedButton = bg.getSelection();
        if (selectedButton == null) {
            JOptionPane.showMessageDialog(panel, "Please select an answer", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        int selectedAnswer = -1;

        // Find the index of the selected answer
        for (int i = 0; i < radioButtons.length; i++) {
            if (selectedButton == radioButtons[i].getModel()) {
                selectedAnswer = i;
                break;
            }
        }
        
        // THIS WILL CHECK WHETHER OR NOT THE CURRENT SELECTED ANSWER MATCHES THE RIGHT ANSWER
        boolean isCorrect = (selectedAnswer == getCorrectAnswer(current));

        // UPDATE ARRAY 
        userResponses[current] = isCorrect;

        return isCorrect;

    }

    int getCorrectAnswer(int question) {
        // Return the index of the correct answer for the given question
        switch (question) {
            case 0:
                return 0;
            case 1:
                return 2;
            case 2:
                return 0;
            case 3:
                return 3;
            case 4:
                return 3;
            case 5:
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAns()) {
                count++;
            }
            current++;
            if (current == 5) { // THE NEXT BUTTON WILL BE SET TO DONE IF ITS ON THE LAST QUESTION
                nextButton.setText("Done");
            }
            if (current == 6) {
                int choice = JOptionPane.showConfirmDialog(panel, "Submit answer?", TOOL_TIP_TEXT_KEY, JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    nextButton.setEnabled(false);
                    backButton.setEnabled(false);
                    resultsButton.setVisible(true);
                    resultsButton.setText("Results");
                } else {
                    current--;
                }
            } else {
                setQuiz();
            }
        } else if (e.getSource() == backButton) {
            current--;

            if (current == 4) { // THE NEXT BUTTON WILL BE SET TO DONE IF ITS ON THE LAST QUESTION
                nextButton.setText("Next");
                nextButton.repaint();
            }

            setQuiz();
        } else if (e.getActionCommand().equals("Results")) {
            stopTimer();
            totalCorrect = 0;

            // Calculate the total correct answers based on userResponses
            for (boolean response : userResponses) {
                if (response) {
                    totalCorrect++;
                }
            }

            JOptionPane.showMessageDialog(this, "Total correct answers are " + totalCorrect + "\nTime taken: " + secondsPassed + " seconds");
            saveScore();
        }
    }

    public static void buildQuiz() {
        String[] questions = {
            "Q1: What are some effective ways to reduce climate change?",
            "Q2: Which of the following is an example of climate change?",
            "Q3: Which pollution is the main cause of climate change?",
            "Q4: Which of the following has contributed most to global warming?",
            "Q5: Which of the following gases is not considered a greenhouse gas?",
            "Q6: What does UNFCCC stand for?"
        };

        String[][] answers = {
            {"Carpool activities", "Burning fossil fuels", "Cutting down forests", "Farming livestock"},
            {"Photosynthesis", "Flood", "Global warming", "Earthquake"},
            {"Air pollution", "Sound pollution", "Soil pollution", "Water pollution"},
            {"Deforestation", "Industrial processes", "Agriculture", "Fossil fuels combustion"},
            {"Methane", "Nitrous Oxide", "Carbon dioxide", "Oxygen"},
            {"United Nations Framework Control on Climate Change",
                "United Nations Framework Convention on Climate Change",
                "United Nations Framework Communications on Climate Change",
                "United Nations Framework Climate Communications Council"}
        };

        for (int i = 0; i < questions.length; i++) {
            String questionDir = "text/questions";
            new File(questionDir).mkdirs();
            FileHandling.setFileContent(questions[i], questionDir + "/question" + (i + 1) + ".txt", false);

            for (int j = 0; j < answers[i].length; j++) {
                FileHandling.setFileContent("\n\n" + answers[i][j], questionDir + "/question" + (i + 1) + ".txt", true);
            }
        }
    }

    public void saveScore() {
        String username = Main.showUsername();
        String filePath = "text/score/" + username + "/score.txt";
        String score = Integer.toString(totalCorrect);
        if (checkResults) {
            FileHandling.setFileContent(score + "\n", filePath, true);
            checkResults = false;
        }
    }

}
