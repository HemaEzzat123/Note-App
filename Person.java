/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private int age;
    private String occupation;

    public Person(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }
}

class PersonTableModel extends AbstractTableModel {
    private List<Person> data;
    private String[] columns = {"Name", "Age", "Occupation"};

    public PersonTableModel(List<Person> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return person.getName();
            case 1:
                return person.getAge();
            case 2:
                return person.getOccupation();
            default:
                return null;
        }
    }
}

 class TableWithObjectsExample {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", 25, "Engineer"));
        personList.add(new Person("Jane", 30, "Teacher"));
        personList.add(new Person("Bob", 22, "Student"));

        // Create a custom TableModel with the list of Person objects
        PersonTableModel tableModel = new PersonTableModel(personList);

        // Create a JFrame instance
        JFrame frame = new JFrame("Table with Objects Example");

        // Create a JTable with the custom TableModel
        JTable table = new JTable(tableModel);

        // Create a JScrollPane to provide scrollbars for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Set the size of the frame
        frame.setSize(400, 300);

        // Set the default close operation to exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Make the frame visible
        frame.setVisible(true);
    }
}
