/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hema
 */
public class SecureNote {

    private String password;
    private String tittle;
    private int noteId;
    private String content;
    private Date createdDate;
    private Date lastModified;
    private List<Image> images;
    private List<Sketch> sketches;
    private static int noteIdCounter = 0;
    private SecureNote secureNote;

    public SecureNote getSecureNote() {
        return secureNote;
    }

    public void setSecureNote(SecureNote secureNote) {
        this.secureNote = secureNote;
    }

    public SecureNote(String password) {
        this.tittle = "";
        this.noteId = generateNoteId();
        this.content = "";
        this.createdDate = new Date();
        this.lastModified = new Date();
        this.images = new ArrayList<>();
        this.sketches = new ArrayList<>();
        this.password = password;
    }

    public int generateNoteId() {
        return noteIdCounter++;
    }

    public void editContent(String newContent) {
        this.content = newContent;
        this.lastModified = new Date();
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public void addSketch(Sketch sketch) {
        sketches.add(sketch);
    }

    public String getTittle() {
        return tittle;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Sketch> getSketches() {
        return sketches;
    }

    public static int getNoteIdCounter() {
        return noteIdCounter;
    }

    public boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setSketches(List<Sketch> sketches) {
        this.sketches = sketches;
    }

    public static void setNoteIdCounter(int noteIdCounter) {
        SecureNote.noteIdCounter = noteIdCounter;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SecureNote{" + "password=" + password + ", tittle=" + tittle + ", noteId=" + noteId + ", content=" + content + ", createdDate=" + createdDate + ", lastModified=" + lastModified + ", images=" + images + ", sketches=" + sketches + '}';
    }

}
