package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.model.State;

public interface PlayerInputDriver extends Driver {
    void setPlayerInputHandler(PlayerInputHandler playerInputHandler);
    void startTurn(State state);
}
