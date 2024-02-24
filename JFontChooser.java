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

public class JFontChooser extends JDialog {
    private Font selectedFont;

    public JFontChooser(JFrame parent, Font initialFont) {
        super(parent, "Font Chooser", true);
        selectedFont = initialFont;

        setLayout(new BorderLayout());

        JList<String> fontList = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JSpinner fontSizeSpinner = new JSpinner(new SpinnerNumberModel(12, 6, 72, 1));

        JScrollPane fontScrollPane = new JScrollPane(fontList);
        fontScrollPane.setPreferredSize(new Dimension(200, 200));

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fontList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedFontName = fontList.getSelectedValue();
                    int selectedFontSize = (int) fontSizeSpinner.getValue();
                    selectedFont = new Font(selectedFontName, Font.PLAIN, selectedFontSize);
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Font Size: "));
        controlPanel.add(fontSizeSpinner);

        add(fontScrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public Font getSelectedFont() {
        return selectedFont;
    }

    public static Font showDialog(JFrame parent, String title, Font initialFont) {
        JFontChooser fontChooser = new JFontChooser(parent, initialFont);
        fontChooser.setTitle(title);
        fontChooser.setVisible(true);
        return fontChooser.getSelectedFont();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Font Chooser");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton chooseFontButton = new JButton("Choose Font");
        chooseFontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font selectedFont = JFontChooser.showDialog(frame, "Choose Font", new Font("Arial", Font.PLAIN, 12));
                System.out.println("Selected Font: " + selectedFont);
            }
        });

        frame.add(chooseFontButton);
        frame.setVisible(true);
    }
}

