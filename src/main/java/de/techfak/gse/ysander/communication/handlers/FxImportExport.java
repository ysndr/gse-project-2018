package de.techfak.gse.ysander.communication.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.IllegalFormatException;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.JavaPlatformException;

/**
 * Provide the logic to load and save States with javaFX components.
 */
public class FxImportExport implements SaveHandler, LoadHandler {


    private final Window parent;

    public FxImportExport(final Window parent) {
        this.parent = parent;
    }

    @Override
    public void saveState(final State state) throws ChessGameException {

        final FileChooser chooser = this.getFileChooser();
        chooser.setTitle("Save FEN");
        File file = chooser.showSaveDialog(parent);

        if (file == null) {
            throw new JavaPlatformException("No file selected", new NoSuchFileException(""));
        }

        if (!file.getPath().replaceAll(".*\\.", "").equals("fen")) {
            throw new JavaPlatformException("File has no `*.fen` ending", new NoSuchFileException(file.getPath()));
        }
        Path path = file.toPath();

        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(state.toFEN());
            writer.close();
        } catch (IOException e) {
            throw new JavaPlatformException(String.format("Could not save file to %s", path), e);
        }
    }

    private FileChooser getFileChooser() {
        final FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FEN files", "*.fen"));
        return chooser;
    }

    @Override
    public State loadState() throws ChessGameException {
        final FileChooser chooser = this.getFileChooser();
        chooser.setTitle("Load FEN");
        File file = chooser.showOpenDialog(parent);
        if (file == null) {
            throw new JavaPlatformException("No file selected", new NoSuchFileException(""));
        }
        Path path = file.toPath();

        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String fen = reader.readLine();

            return StateBuilder.fromFEN(fen);
        } catch (IOException e) {
            throw new JavaPlatformException(String.format("Could not load file %s", path), e);
        }
    }

}
