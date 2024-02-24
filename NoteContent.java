/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Hema
 */
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteContent extends JFrame {
    public static JTextPane noteTextPane;

    public NoteContent() {
        setTitle("Note App GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        noteTextPane = new JTextPane();

        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor();
            }
        });

        JButton fontSizeButton = new JButton("Adjust Font Size");
        fontSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustFontSize();
            }
        });

        JCheckBox boldCheckBox = new JCheckBox("Bold");
        JCheckBox italicCheckBox = new JCheckBox("Italic");

        boldCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        italicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(fontSizeButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(noteTextPane));

    }

    private void chooseColor() {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Text Color", noteTextPane.getForeground());
        if (selectedColor != null) {
            noteTextPane.setForeground(selectedColor);
        }
    }

    private void adjustFontSize() {
        String fontSizeStr = JOptionPane.showInputDialog(this, "Enter Font Size:");
        try {
            int fontSize = Integer.parseInt(fontSizeStr);
            noteTextPane.setFont(new Font(noteTextPane.getFont().getFamily(), noteTextPane.getFont().getStyle(), fontSize));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Font Size!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NoteContent().setVisible(true);
        });
    }
}

