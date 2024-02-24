/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hema
 */
public class saveNotes extends JFrame {

    DefaultTableModel model = new DefaultTableModel(new String[]{"noteName"}, 0);

    public User catalog;

    public saveNotes() {
        catalog = new User();
        createCatalogTab();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            saveNotes app = new saveNotes();
            app.setSize(800, 600);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });
    }

    public void createCatalogTab() {
        setTitle("Loaded Notes");

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel catalogPanel = new JPanel();
        catalogPanel.setLayout(new BorderLayout());

        // Create a JTable to display the catalog
        JTable catalogTable = new JTable(model);

        // Create JTextFields for book information
        JTextField noteNameField = new JTextField(20);

        // Create buttons to add and remove books
        JButton addButton = new JButton("Add Note");
        JButton removeButton = new JButton("Remove Note");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noteName = noteNameField.getText();
                if (!noteName.isEmpty()) {
                    // Create a new book object and add it to the catalog
                    Note newNote = new Note(noteName);
                    catalog.addNote(newNote);
                    model.addRow(new Object[]{noteName}); // Update the table view
                    // Clear the JTextFields for the next entry
                    noteNameField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = catalogTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Remove the selected book from the catalog
                    catalog.removeNote(selectedRow);
                    model.removeRow(selectedRow); // Update the table view
                }
            }
        });

        // Create a JTextField for search and a button
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                if (!searchTerm.isEmpty()) {
                    searchNotes(searchTerm);
                } else {
                    // If the search field is empty, show all books
                    model.setRowCount(0);
                    for (Note note : catalog.getNotes()) {
                        model.addRow(new Object[]{note.getTittle()});
                    }
                }
            }
        });

        // Create the layout for the Catalog Tab
        JPanel catalogLayout = new JPanel();
        catalogLayout.setLayout(new BoxLayout(catalogLayout, BoxLayout.Y_AXIS));
        catalogLayout.add(new JScrollPane(catalogTable));
        catalogLayout.add(noteNameField);
        catalogLayout.add(addButton);
        catalogLayout.add(removeButton);
        catalogLayout.add(searchField);
        catalogLayout.add(searchButton);

        catalogPanel.add(catalogLayout, BorderLayout.CENTER);
        tabbedPane.add("Catalog", catalogPanel);
        add(tabbedPane);
    }

    public void searchNotes(String searchTerm) {
        model.setRowCount(0);
        for (Note note : catalog.getNotes()) {
            if (note.getTittle().toLowerCase().contains(searchTerm.toLowerCase())) {
                model.addRow(new Object[]{note.getTittle()});
            }
        }
    }
}
