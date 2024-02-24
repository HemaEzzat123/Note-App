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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class SketchApp extends JFrame {

    private int prevX, prevY;
    private Canvas canvas;
    private Color currentColor = Color.BLACK;

    public SketchApp() {
        setTitle("Sketch App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);

        JButton saveButton = new JButton("Save Sketch");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSketch();
            }
        });

        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = JColorChooser.showDialog(SketchApp.this, "Choose Color", currentColor);
            }
        });
        JButton loadButton = new JButton("Load Sketch");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSketch();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(colorButton);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = canvas.getGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(prevX, prevY, e.getX(), e.getY());
//                canvas.addPoint(e.getX(), e.getY());
                prevX = e.getX();
                prevY = e.getY();
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
        add(canvas);
    }

    private void saveSketch() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sketch.txt"))) {
            // Save sketch coordinates to the file
            // In a more complex application, you might save additional information (color, size, etc.)
            // Here, we're just saving the coordinates.
            for (int i = 0; i < canvas.getXPoints().size(); i++) {
                int x = canvas.getXPoints().get(i);
                int y = canvas.getYPoints().get(i);
                writer.println(x + " " + y);
            }
            JOptionPane.showMessageDialog(this, "Sketch saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving the sketch!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSketch() {
        try (BufferedReader reader = new BufferedReader(new FileReader("sketch.txt"))) {
            canvas.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                canvas.addPoint(x, y);
            }
            JOptionPane.showMessageDialog(this, "Sketch loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading the sketch!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SketchApp().setVisible(true);
        });
    }

    private class Canvas extends JPanel {

        private java.util.List<Integer> xPoints = new java.util.ArrayList<>();
        private java.util.List<Integer> yPoints = new java.util.ArrayList<>();

        public void addPoint(int x, int y) {
            xPoints.add(x);
            yPoints.add(y);
            repaint();
        }

        public java.util.List<Integer> getXPoints() {
            return xPoints;
        }

        public java.util.List<Integer> getYPoints() {
            return yPoints;
        }

        public void clear() {
            xPoints.clear();
            yPoints.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 1; i < xPoints.size(); i++) {
                int x1 = xPoints.get(i - 1);
                int y1 = yPoints.get(i - 1);
                int x2 = xPoints.get(i);
                int y2 = yPoints.get(i);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}
