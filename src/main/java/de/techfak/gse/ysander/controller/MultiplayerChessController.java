package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.drivers.PlayerInputDriver;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 */
public class MultiplayerChessController extends BaseChessController implements PlayerInputHandler {

    private final PlayerInputDriver playerWhite;
    private final PlayerInputDriver playerBlack;


    public MultiplayerChessController(final View view,
                                      final State initialState,
                                      final PlayerInputDriver playerWhite,
                                      final PlayerInputDriver playerBlack) {
        super(view, initialState);

        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;

        view.setOnInitCB(() -> view.display(initialState));
        playerWhite.setPlayerInputHandler(this);
        playerBlack.setPlayerInputHandler(this);

        playerWhite.startTurn(state); //TODO: remove
    }



    @Override
    public void handlePlayerInput(final Field input, final Player player) {
        State newState = state;
        try {
             newState = state.applyField(input);
        } catch (InvalidMoveException e) {
            this.handleError(e);
        }

        if (newState != null && newState.getColor() == Figure.Color.WHITE) {
            playerWhite.startTurn(newState);
        }else {
            playerBlack.startTurn(newState);
        }

        output.display(newState);
    }

}
