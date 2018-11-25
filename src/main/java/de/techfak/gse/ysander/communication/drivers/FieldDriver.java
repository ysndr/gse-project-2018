package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.communication.inputs.FieldInput;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;

public class FieldDriver implements PlayerInputDriver, FieldInputHandler {

    private final Player player;
    private final FieldInput input;
    private PlayerInputHandler output = (f, p) -> {};
    private ErrorHandler errorHandler = (e) -> {};

    public FieldDriver(final Player player, final FieldInput input) {
        this.player = player;
        this.input = input;
    }

    @Override
    public void setPlayerInputHandler(final PlayerInputHandler playerInputHandler) {
        this.output = playerInputHandler;
    }

    @Override
    public void startTurn(final State state) {
        this.input.setFieldInputHandler(this);
    }

    @Override
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void handleFieldInput(final Field input) {
        this.output.handlePlayerInput(input, player);
    }
}
