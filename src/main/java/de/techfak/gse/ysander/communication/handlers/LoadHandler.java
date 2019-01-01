package de.techfak.gse.ysander.communication.handlers;

import javafx.stage.Window;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;

/**
 * Tries to load a {@link State} from file;
 * TODO: Could default methods be used here?
 */
public interface LoadHandler {
    State loadState(Window parent) throws ChessGameException;
}
