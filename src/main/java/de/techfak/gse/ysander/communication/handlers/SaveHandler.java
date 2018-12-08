package de.techfak.gse.ysander.communication.handlers;

import javafx.stage.Window;

import de.techfak.gse.ysander.model.State;

/**
 * Tries to save the current {@link State} to file;
 * TODO: Could default methods be used here?
 */
public interface SaveHandler {
    void saveState(Window parent);
}
