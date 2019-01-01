package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.drivers.PlayerInputDriver;
import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 */
public class MultiplayerChessController extends BaseChessController implements PlayerInputHandler {

    private final PlayerInputDriver playerWhite;

    private final PlayerInputDriver playerBlack;


    public MultiplayerChessController(final State initialState, final View view,
                                      final ErrorHandler errorHandler,
                                      final PlayerInputDriver playerWhite,
                                      final PlayerInputDriver playerBlack) {
        super(initialState, view, errorHandler);


        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;

        playerWhite.setPlayerInputHandler(this);
        playerBlack.setPlayerInputHandler(this);

        this.updateState(state);

    }

    @Override
    protected void updateState(final State state) {
        super.updateState(state);

        if (this.state.getColor().equals(Figure.Color.WHITE)) {
            playerWhite.startTurn(this.state);
        } else if (this.state.getColor().equals((Figure.Color.BLACK))) {
            playerBlack.startTurn(this.state);
        }
    }

    @Override
    public void handlePlayerInput(final Hint input, final Player player) {
        State newState = state;
        try {
            newState = state.applyHint(input);
        } catch (InvalidMoveException e) {
            errorHandler.handleError(e);
        }

        this.updateState(newState);

    }


}
