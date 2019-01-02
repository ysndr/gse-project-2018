package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.rules.Hint;

/**
 * Accept Hint inputs tagged with the player choosing the hint.
 */
public interface PlayerInputHandler {
    void handlePlayerInput(Hint input, Player player);
}
