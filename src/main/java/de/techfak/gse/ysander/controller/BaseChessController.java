package de.techfak.gse.ysander.controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.*;

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
    public void saveState() {
        final FileDialog fileDialog = this.getFileDialog();
        fileDialog.setMode(FileDialog.SAVE);
        fileDialog.setTitle("Save FEN");
        fileDialog.setVisible(true);

        Path path = FileSystems.getDefault().getPath(fileDialog.getFile());
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(state.toFEN());
            writer.close();
        } catch (IOException e) {
            throw new JavaPlatformException(String.format("Could not save file to %s", path), e);
        }
    }

    private FileDialog getFileDialog() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final FileDialog fileDialog = new FileDialog(frame, "Save FEN");
        fileDialog.setFilenameFilter((dir, name) -> name.endsWith(".fen"));
        fileDialog.setFile("*.fen");
        return fileDialog;
    }

    @Override
    public void loadState() {
        final FileDialog fileDialog = this.getFileDialog();
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setTitle("Load FEN");
        fileDialog.setVisible(true);

        Path path = FileSystems.getDefault().getPath(fileDialog.getFile());
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

