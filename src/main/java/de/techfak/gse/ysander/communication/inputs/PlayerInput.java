package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;

/**
 * Connect a {@link PlayerInputHandler}. Classes implementing this interface
 * usually generate input from one player only and use this to enable handlers
 * down the line to distinguish between different Players.
 */
public interface PlayerInput {
    void setPlayerInputHandler(PlayerInputHandler fieldInputHandler);
}
