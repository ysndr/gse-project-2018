package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.model.State;

/**
 * A driver that generates user tagged hints that are put down to
 * {@link PlayerInputHandler}
 */
public interface PlayerInputDriver extends Driver {
    void setPlayerInputHandler(PlayerInputHandler playerInputHandler);

    void startTurn(State state);
}
