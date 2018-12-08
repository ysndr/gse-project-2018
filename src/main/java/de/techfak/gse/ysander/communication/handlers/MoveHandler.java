package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.Move;

/**
 * Accept move input. Basically that means this interface is called whenever a
 * move event is created.
 */
public interface MoveHandler {
    void handleMove(Move move);
}
