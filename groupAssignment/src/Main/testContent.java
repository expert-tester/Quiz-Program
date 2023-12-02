/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Chun On
 */
public class testContent {
        public static JLabel createProfile() {
        ImageIcon image = new ImageIcon("images/bunnies.png");
        Border border = BorderFactory.createLineBorder(Color.GREEN,5);
        JLabel label = new JLabel(); 
        label.setText("bro do you even code?");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("Courier",Font.ITALIC, 50));
        label.setIconTextGap(100); //set gap of text to image
        label.setBackground(Color.BLACK);
        label.setOpaque(true); //bg color opaque
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        return label;

//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //frame.setSize(800, 800);
//        
//        frame.add(label);
//        frame.setVisible(true);
//        frame.pack(); //fit to content size
    }
    
    public static JLabel createEducation() {
        JLabel htmlLabel = new JLabel("<html> <h1>Line 1 bla bla bla</h1> <br><p style='background-color:red;'>line 2 bla bla bla</p><br> line 3 bla bla bla </html>"); 
        Border border = BorderFactory.createLineBorder(Color.green,3);
        htmlLabel.setBorder(border);
        htmlLabel.setHorizontalTextPosition(JLabel.CENTER);
        htmlLabel.setVerticalTextPosition(JLabel.BOTTOM);
        
        return htmlLabel;
    }
    
    public static String getLabelText(JLabel label) {
        return label.getText();
    }
}
