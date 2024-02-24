/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;

/**
 *
 * @author Hema
 */
public class Image {

    private int imageId;
    private File file;
    private static int imageIdCounter = 0;

    public Image() {
        this.imageId = generateImageId();
        this.file = new File("image.jpg");
    }

    private int generateImageId() {
        return imageIdCounter++;
    }

    public void viewImage() {
        if (file.exists()) {
            System.out.println("Viewing Image " + imageId);
            System.out.println("File Path: " + file.getAbsolutePath());
        } else {
            System.out.println("Image not found.");
        }
    }

    public void deleteImage() {
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Image " + imageId + " deleted successfully.");
            } else {
                System.out.println("Failed to delete Image " + imageId);
            }
        } else {
            System.out.println("Image not found.");
        }
    }

    public int getImageId() {
        return imageId;
    }

    public File getFile() {
        return file;
    }

    public static int getImageIdCounter() {
        return imageIdCounter;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void setImageIdCounter(int imageIdCounter) {
        Image.imageIdCounter = imageIdCounter;
    }
    
}
