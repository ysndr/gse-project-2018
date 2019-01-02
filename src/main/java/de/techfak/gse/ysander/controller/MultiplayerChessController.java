package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.drivers.PlayerInputDriver;
import de.techfak.gse.ysander.communication.handlers.*;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 */
public class MultiplayerChessController extends BaseChessController
    implements PlayerInputHandler,
               LoadHandler,
               SaveHandler,
               ResetHandler {

    private final PlayerInputDriver playerWhite;

    private final PlayerInputDriver playerBlack;

    private final LoadHandler loadHandler;

    private final SaveHandler saveHandler;

    /**
     * Construct a new Controller.
     *
     * @param initialState
     * @param view
     * @param errorHandler
     * @param playerWhite
     * @param playerBlack
     * @param loadHandler  delegate for load actions
     * @param saveHandler  delegate for save actions
     */
    public MultiplayerChessController(final State initialState,
                                      final View view,
                                      final ErrorHandler errorHandler,
                                      final PlayerInputDriver playerWhite,
                                      final PlayerInputDriver playerBlack,
                                      final LoadHandler loadHandler,
                                      final SaveHandler saveHandler) {
        super(initialState, view, errorHandler);


        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.loadHandler = loadHandler;
        this.saveHandler = saveHandler;

        playerWhite.setPlayerInputHandler(this);
        playerBlack.setPlayerInputHandler(this);

        this.updateState(state);

    }

    @Override
    public void updateState(final State state) {
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

    @Override
    public State loadState() throws ChessGameException {
        State state = this.state;
        try {
            state = loadHandler.loadState();
        } catch (ChessGameException e) {
            errorHandler.handleError(e);
        }
        updateState(state);
        return state;
    }

    @Override
    public void saveState(final State state) throws ChessGameException {
        try {
            saveHandler.saveState(state);
        } catch (ChessGameException e) {
            errorHandler.handleError(e);
        }
    }

    @Override
    public void resetState() {
        updateState(StateBuilder.defaultState());
    }
}
