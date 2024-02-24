/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;

/**
 *
 * @author Hema
 */
class Sketch {

    private int sketchId;
    private File file;
    private static int sketchIdCounter = 0;
    private String content;

    public Sketch() {
        this.sketchId = generateSketchId();
        this.file = new File("sketch.txt");

    }

    private int generateSketchId() {
        return sketchIdCounter++;
    }

    public void viewSketch() {
        if (file.exists()) {
            System.out.println("Viewing Sketch " + sketchId);
            System.out.println("File Path: " + file.getAbsolutePath());
        } else {
            System.out.println("Skecth not found.");
        }
    }

    public void deleteSketch() {
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Sketch " + sketchId + " deleted successfully.");
            } else {
                System.out.println("Failed to delete sketch " + sketchId);
            }
        } else {
            System.out.println("Sketch not found.");
        }
    }

    public int getSketchId() {
        return sketchId;
    }

    public File getFile() {
        return file;
    }

    public static int getSketchIdCounter() {
        return sketchIdCounter;
    }

    public void setSketchId(int sketchId) {
        this.sketchId = sketchId;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void setSketchIdCounter(int sketchIdCounter) {
        Sketch.sketchIdCounter = sketchIdCounter;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
