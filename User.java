/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 * @author Hema
 */
class User {

    private static String userName;
    private static String password;
    private static List<Note> notes;

    private static final String USERS_DIRECTORY = "Users";
    private static final String USER_EXTENSION = ".txt";
    private Map<String, String> users;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        notes = new ArrayList<>();
        this.users = new HashMap<>();
        loadUsers();  // Load existing users when the class is instantiated
    }
//    public User(String userName , String password) {
//        this.userName=userName;
//        this.password=password;
//    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(int noteid) {
        notes.remove(noteid);
    }

    public Note searchByTitle(String title) {
        for (Note note : notes) {
            if (note.getTittle().equalsIgnoreCase(title)) {
                return note;
            }
        }
        return null;
    }

    public void signUp(String us, String pw) {
        this.userName = us;
        this.password = pw;
    }

    public boolean logIn(String password) {
        if (password.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }

    public void logOut() {
        System.out.println("User " + userName + " has been logged out.");
    }

    public Note createNote() {
        Note newNote = new Note();
        notes.add(newNote);
        return newNote;
    }

    public void deleteNote(Note note) {
        notes.remove(note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public Image createImage() {
        return new Image();
    }

    public Sketch createSketch() {
        return new Sketch();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void registerUser(String username, String password) {
        // Hash the password before saving
        String hashedPassword = hashPassword(password);
        this.setUserName(username);
        this.setPassword(password);
        // Save the user data to a file
        saveUserData(username, hashedPassword);
    }

    public boolean authenticateUser(String username, String password) {
        // Hash the provided password for comparison
        String hashedPassword = hashPassword(password);

        // Check if the user exists and the passwords match
        return users.containsKey(username) && users.get(username).equals(hashedPassword);
    }

    private void saveUserData(String username, String hashedPassword) {
        File userFile = new File(USERS_DIRECTORY, username + USER_EXTENSION);

        try (PrintWriter writer = new PrintWriter(userFile)) {
            writer.println(username);
            writer.println(hashedPassword);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Update the in-memory user data
        users.put(username, hashedPassword);
    }

    private void loadUsers() {
        File usersFolder = new File(USERS_DIRECTORY);
        if (usersFolder.exists() && usersFolder.isDirectory()) {
            File[] userFiles = usersFolder.listFiles((dir, name) -> name.endsWith(USER_EXTENSION));

            if (userFiles != null) {
                for (File userFile : userFiles) {
                    readUserData(userFile);
                }
            }
        }
    }

    private void readUserData(File userFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String username = reader.readLine();
            String hashedPassword = reader.readLine();
            users.put(username, hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
