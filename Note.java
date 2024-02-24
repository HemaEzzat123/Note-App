/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.*;

public class Note {

    private String tittle;
    private static int noteId;
    private String content;
    private Date createdDate;
    private Date lastModified;
    private List<Image> images;
    private List<Sketch> sketches;
    private SecureNote secureNote;
    private static int noteIdCounter = 0;

    public Note() {
        this.tittle = "";
        this.noteId = generateNoteId();
        this.content = "";
        this.createdDate = new Date();
        this.lastModified = new Date();
        this.images = new ArrayList<>();
        this.sketches = new ArrayList<>();
    }

    public Note(String tittle) {
        this.tittle = tittle;
        this.noteId = generateNoteId();
        this.content = "";
        this.createdDate = new Date();
        this.lastModified = new Date();
        this.images = new ArrayList<>();
        this.sketches = new ArrayList<>();
    }

    public String getTittle() {
        return tittle;
    }

    @Override
    public String toString() {
        return "Note{" + "noteId=" + noteId + '}';
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public SecureNote passwordProtect(String password) {
        secureNote = new SecureNote(password);
        return secureNote;
    }

    public static int getNoteId() {
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

    public SecureNote getSecureNote() {
        return secureNote;
    }

    public static int getNoteIdCounter() {
        return noteIdCounter;
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

    public void setSecureNote(SecureNote secureNote) {
        this.secureNote = secureNote;
    }

    public static void setNoteIdCounter(int noteIdCounter) {
        Note.noteIdCounter = noteIdCounter;
    }

    
}
