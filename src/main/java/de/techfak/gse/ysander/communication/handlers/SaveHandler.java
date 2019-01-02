package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;

/**
 * Tries to save the current {@link State} to file;
 * TODO: Could default methods be used here?
 */
public interface SaveHandler {
    void saveState(State state) throws ChessGameException;
}
