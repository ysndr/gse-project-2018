package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;

/**
 * Tries to load a {@link State} from file;
 * TODO: Could default methods be used here?
 */
public interface LoadHandler {
    State loadState() throws ChessGameException;
}
