package de.techfak.gse.ysander.communication.handlers;

import javafx.stage.Window;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.model.State;

/**
 * Tries to load a {@link State} from file;
 * TODO: Could default methods be used here?
 */
public interface LoadHandler {
    void loadState(Window parent);
}
