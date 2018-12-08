package de.techfak.gse.ysander.controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.*;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.inputs.LoadHandler;
import de.techfak.gse.ysander.communication.inputs.SaveHandler;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.error.JavaPlatformException;


public abstract class BaseChessController implements ErrorHandler, SaveHandler, LoadHandler {

    protected Output<State> output;
    protected State state;

    BaseChessController(final Output<State> output, final State initialState) {
        this.output = output;
        this.state = initialState;
    }

    BaseChessController(final State initialState) {
        this((s) -> {}, initialState);
    }

    public void setOutput(final Output<State> output) {
        this.output = output;
    }

    @Override
    public void handleError(final ChessGameException e) {
        if (e instanceof InvalidMoveException) {
            output.display(state);
        }
        GlobalErrorEmergencyExit.getInstance().handleError(e);
    }

    void setState(State state) {
        this.state = state;

    }


    @Override
    public void saveState(Window parent) {

        final FileChooser chooser = this.getFileChooser();
        chooser.setTitle("Save FEN");
        File file = chooser.showSaveDialog(parent);

        if (file == null) {
            return;
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
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FEN files", "fen"));
        return chooser;
    }

    @Override
    public void loadState(Window parent) {
        final FileChooser chooser = this.getFileChooser();
        chooser.setTitle("Load FEN");
        File file = chooser.showOpenDialog(parent);
        if (file == null) {
            return;
        }
        Path path = file.toPath();

        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String fen = reader.readLine();

            State loaded = StateBuilder.fromFEN(fen);
            this.setState(loaded);
            this.output.display(loaded);
        } catch (IOException e) {
            throw new JavaPlatformException(String.format("Could not save file to %s", path), e);
        }
    }
}

