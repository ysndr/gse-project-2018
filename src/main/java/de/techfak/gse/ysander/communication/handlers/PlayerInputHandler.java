package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Player;

public interface PlayerInputHandler {
    void handlePlayerInput(Field input, Player player);
}
