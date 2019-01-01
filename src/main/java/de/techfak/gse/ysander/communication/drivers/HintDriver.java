package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.handlers.HintInputHandler;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.communication.inputs.FieldInput;
import de.techfak.gse.ysander.communication.inputs.HintInput;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;

/**
 * Connects to a {@link FieldInput} and enriches that.
 */
public class HintDriver implements PlayerInputDriver, HintInputHandler {

    private final Player player;

    private final HintInput input;

    private PlayerInputHandler output = (f, p) -> { };

    private ErrorHandler errorHandler = (e) -> { };

    public HintDriver(final Player player, final HintInput input) {
        this.player = player;
        this.input = input;
    }

    @Override
    public void setPlayerInputHandler(final PlayerInputHandler playerInputHandler) {
        this.output = playerInputHandler;
    }

    @Override
    public void startTurn(final State state) {
        this.input.setHintInputHandler(this);
    }

    @Override
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void handleHintInput(final Hint input) {
        this.output.handlePlayerInput(input, player);
    }
}
