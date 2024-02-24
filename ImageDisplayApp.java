/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Hema
 */
public class ImageDisplayApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {
                    JFrame frame = new JFrame("Image Display");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // Provide the path to your image file
                    String imagePath = "C:\\Users\\Hema\\Desktop\\note\\addNote.jpg";

                    // Create an ImageIcon with the specified image path
                    ImageIcon imageIcon = new ImageIcon(imagePath);

                    // Create a JLabel to display the image
                    JLabel label = new JLabel(imageIcon);

                    // Add the JLabel to the frame
                    frame.getContentPane().add(label, BorderLayout.CENTER);

                    // Set frame properties
                    frame.setSize(400, 400);
                    frame.setLocationRelativeTo(null); // Center the frame
                    frame.setVisible(true);
                });
    }
}
