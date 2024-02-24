package project;

import java.util.List;

public class Project {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        String userFolder = "user"; // Replace with the actual user folder

        // Creating and saving a Note with an Image
        Note noteToSave = new Note();
        noteToSave.setNoteId(noteToSave.getNoteId());
        noteToSave.setContent("This is a test note.");
        //image
        Image imageToSave = new Image();
        imageToSave.setImageId(imageToSave.getImageId());
        noteToSave.addImage(imageToSave);
        //sketch
        Sketch sketchToSave = new Sketch();
        sketchToSave.setSketchId(sketchToSave.getSketchId());
        sketchToSave.setContent("This is a test sketch.");
        noteToSave.addSketch(sketchToSave);
        //secure
        SecureNote secureNoteToSave = new SecureNote("securePassword");
        noteToSave.setSecureNote(secureNoteToSave);

        fileManager.saveNote(noteToSave, userFolder);

        // Loading Notes
        List<Note> loadedNotes = fileManager.loadNotes(userFolder);

        // Displaying loaded Notes
        for (Note loadedNote : loadedNotes) {
            System.out.println("Loaded Note:");
            System.out.println("Note ID: " + loadedNote.getNoteId());
            System.out.println("Content: " + loadedNote.getContent());

            // Displaying loaded Images for the Note
            for (Image loadedImage : loadedNote.getImages()) {
                System.out.println("Loaded Image:");
                System.out.println("Image ID: " + loadedImage.getImageId());
            }
            for (Sketch loadedSketch : loadedNote.getSketches()) {
                System.out.println("Loaded Sketch:");
                System.out.println("Sketch ID: " + loadedSketch.getSketchId());
            }

            // Displaying loaded SecureNote for the Note
            SecureNote loadedSecureNote = loadedNote.getSecureNote();
            if (loadedSecureNote != null) {
                System.out.println("Loaded SecureNote:");
                System.out.println("Password: " + loadedSecureNote.getPassword());
            }

            System.out.println();
        }
    }

}
