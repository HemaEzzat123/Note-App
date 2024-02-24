/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static javax.xml.bind.DatatypeConverter.parseDate;

/**
 *
 * @author Hema
 */
public class FileManager {

    static String x;

    public static String readFile(File file) throws IOException {
        // Read the content of the file into a String
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return new String(fileBytes, StandardCharsets.UTF_8);
    }

    public FileManager() {
    }

    public void saveNote(Note note, String userFolder) {
        String filePath = "UsersFolder" + File.separator + userFolder + File.separator + "note_" + note.getNoteId() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Note ID: " + note.getNoteId());
            writer.println("Note Tittle: " + note.getTittle());
            writer.println("Created Date: " + note.getCreatedDate());
            writer.println("Last Modified: " + note.getLastModified());
            writer.println("Content: " + note.getContent());

            for (Image image : note.getImages()) {
                saveImage(image, userFolder);
                writer.println("Image ID: " + image.getImageId());
            }

            for (Sketch sketch : note.getSketches()) {
//                saveSketch(sketch, userFolder);
                writer.println("Sketch ID: " + sketch.getSketchId());
            }

            SecureNote secureNote = note.getSecureNote();
            if (secureNote != null) {
                writer.println("Secure Note - Password: " + secureNote.getPassword());
            }
        } catch (IOException e) {
            System.err.println("Folder Not Found");
        }
    }

    public void saveSecureNote(SecureNote note, String userFolder) {
        String filePath = "UsersFolder" + File.separator + userFolder + File.separator + "secureNote_" + note.getNoteId() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Note ID: " + note.getNoteId());
            writer.println("Note Tittle: " + note.getTittle());
            writer.println("Created Date: " + note.getCreatedDate());
            writer.println("Last Modified: " + note.getLastModified());
            writer.println("Content: " + note.getContent());
            writer.println("Password: " + note.getPassword());

            for (Image image : note.getImages()) {
                saveImage(image, userFolder);
                writer.println("Image ID: " + image.getImageId());
            }

            for (Sketch sketch : note.getSketches()) {
                saveSketch(sketch, userFolder);
                writer.println("Sketch ID: " + sketch.getSketchId());
            }
        } catch (IOException e) {
            System.err.println("Folder Not Found");
        }
    }

    public List<SecureNote> loadSecureNotes(String userFolder) {
        List<SecureNote> notes = new ArrayList<>();

        File folder = new File(userFolder);
        File[] noteFiles = folder.listFiles((dir, name) -> name.startsWith("secureNote_") && name.endsWith(".txt"));

        if (noteFiles != null) {
            for (File noteFile : noteFiles) {
                SecureNote note = readSecureNoteFromFile(noteFile);
                if (note != null) {
                    notes.add(note);
                }
            }
        }

        return notes;
    }

    private SecureNote readSecureNoteFromFile(File noteFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(noteFile))) {
            String line;
            String password = "";
            SecureNote note = new SecureNote(password);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Note ID:")) {
                    note.setNoteId(Integer.parseInt(line.split(":")[1].trim()));
                } else if (line.startsWith("Note Tittle:")) {
                    note.setTittle(line.substring("Tittle:".length()).trim());
                } else if (line.startsWith("Created Date:")) {
                    note.setCreatedDate(parseDate(line.split(":")[1].trim()));
                } else if (line.startsWith("Content:")) {
                    note.setContent(line.substring("Content:".length()).trim());
                } else if (line.startsWith("Last Modified:")) {
                    note.setLastModified(parseDate(line.split(":")[1].trim()));
                } else if (line.startsWith("Image ID:")) {
                    Image image = readImageFromFile(reader);
                    note.addImage(image);
                } else if (line.startsWith("Sketch ID:")) {
                    Sketch sketch = readSketchFromFile(reader);
                    note.addSketch(sketch);
                } else if (line.startsWith("Password:")) {
                    note.setPassword(line.substring("Password:".length()).trim());
                }
            }

            return note;
        } catch (IOException e) {
            System.err.println("Note Not Found");
            System.err.println("Wrong dara entry");

            return null;
        }
    }

    public List<Note> loadNotes(String userFolder) {
        List<Note> notes = new ArrayList<>();

        File folder = new File(userFolder);
        File[] noteFiles = folder.listFiles((dir, name) -> name.startsWith("note_") && name.endsWith(".txt"));

        if (noteFiles != null) {
            for (File noteFile : noteFiles) {
                Note note = readNoteFromFile(noteFile);
                if (note != null) {
                    notes.add(note);
                }
            }
        }

        return notes;
    }

    private Note readNoteFromFile(File noteFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(noteFile))) {
            String line;
            Note note = new Note();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Note ID:")) {
                    note.setNoteId(Integer.parseInt(line.split(":")[1].trim()));
                } else if (line.startsWith("Note Tittle:")) {
                    note.setTittle(line.substring("Tittle:".length()).trim());
                } else if (line.startsWith("Created Date:")) {
                    note.setCreatedDate(parseDate(line.split(":")[1].trim()));
                } else if (line.startsWith("Content:")) {
                    note.setContent(line.substring("Content:".length()).trim());
                } else if (line.startsWith("Last Modified:")) {
                    note.setLastModified(parseDate(line.split(":")[1].trim()));
                } else if (line.startsWith("Image ID:")) {
                    Image image = readImageFromFile(reader);
                    note.addImage(image);
                } else if (line.startsWith("Sketch ID:")) {
                    Sketch sketch = readSketchFromFile(reader);
                    note.addSketch(sketch);
                } else if (line.startsWith("Secure Note - Password:")) {
                    SecureNote secureNote = new SecureNote(line.split(":")[1].trim());
                    note.setSecureNote(secureNote);
                }
            }

            return note;
        } catch (IOException e) {
            System.err.println("Note Not Found");
            System.err.println("Wrong dara entry");

            return null;
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error Date");

            return null;
        }
    }

    private Image readImageFromFile(BufferedReader reader) throws IOException {
        Image image = new Image();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Image ID:")) {
                image.setImageId(Integer.parseInt(line.split(":")[1].trim()));
            }
        }

        return image;
    }

    private Sketch readSketchFromFile(BufferedReader reader) throws IOException {
        Sketch sketch = new Sketch();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Sketch ID:")) {
                sketch.setSketchId(Integer.parseInt(line.split(":")[1].trim()));
            }
        }

        return sketch;
    }

    public void saveImage(Image image, String userFolder) {
        String filePath = "UsersFolder" + File.separator + userFolder + File.separator + "image_" + image.getImageId() + ".jpg";

        try {
            Files.copy(image.getFile().toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
//            System.err.println("Image Not Found");

        }
    }

    public List<Image> loadImages(String userFolder) {
        List<Image> images = new ArrayList<>();

        File folder = new File(userFolder);
        File[] imageFiles = folder.listFiles((dir, name) -> name.startsWith("image_") && name.endsWith(".jpg"));

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                Image image = readImageFromFile(imageFile);
                if (image != null) {
                    images.add(image);
                }
            }
        }

        return images;
    }

    private Image readImageFromFile(File imageFile) {
        Image image = new Image();

        String fileName = imageFile.getName();
        int imageId = Integer.parseInt(fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf(".")));
        image.setImageId(imageId);

        image.setFile(imageFile);

        return image;
    }

    public void saveSketch(Sketch sketch, String userFolder) {
        String filePath = "UsersFolder" + File.separator + userFolder + File.separator + "sketch_" + sketch.getSketchId() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Sketch ID: " + sketch.getSketchId());

            writer.println("Sketch Content:");
            writer.println(sketch.getContent());
        } catch (IOException e) {
//            System.err.println("Sketch Not Found");

        }
    }

    public List<Sketch> loadSketches(String userFolder) {
        List<Sketch> sketches = new ArrayList<>();

        File folder = new File(userFolder);
        File[] sketchFiles = folder.listFiles((dir, name) -> name.startsWith("sketch_") && name.endsWith(".txt"));

        if (sketchFiles != null) {
            for (File sketchFile : sketchFiles) {
                Sketch sketch = readSketchFromFile(sketchFile);
                if (sketch != null) {
                    sketches.add(sketch);
                }
            }
        }

        return sketches;
    }

    private Sketch readSketchFromFile(File sketchFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sketchFile))) {
            String line;
            Sketch sketch = new Sketch();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Sketch ID:")) {
                    sketch.setSketchId(Integer.parseInt(line.split(":")[1].trim()));
                } else if (line.startsWith("Sketch Content:")) {
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    sketch.setContent(content.toString().trim());
                }
            }

            return sketch;
        } catch (IOException e) {
            System.err.println("Sketch Not Found");
            return null;
        }
    }
}
