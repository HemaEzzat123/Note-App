/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAuthentication {

    private static final String USERS_DIRECTORY = "Users";
    private static final String USER_EXTENSION = ".txt";

    private Map<String, String> users;

    public UserAuthentication() {
        this.users = new HashMap<>();
        loadUsers();  // Load existing users when the class is instantiated
    }

    public void registerUser(String username, String password) {
        // Hash the password before saving
        String hashedPassword = hashPassword(password);

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

    public static void main(String[] args) {
        UserAuthentication userAuth = new UserAuthentication();

        // Register a user
        userAuth.registerUser("john_doe", "secure_password");

        // Authenticate the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Enter password: ");
        String passwordInput = scanner.nextLine();

        if (userAuth.authenticateUser(usernameInput, passwordInput)) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed!");
        }
    }
}
