package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.drivers.RawInputDriver;
import de.techfak.gse.ysander.communication.handlers.MoveHandler;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 */
public class RawChessController extends BaseChessController implements MoveHandler {

    private final RawInputDriver rawInput;

    /**
     * Constructs a new Controller
     *
     * @param view
     * @param rawInput
     * @param initialState
     */
    public RawChessController(final View view,
                              final RawInputDriver rawInput,
                              final State initialState) {
        super(initialState, view, GlobalErrorEmergencyExit.getInstance());

        this.rawInput = rawInput;

        view.setOnInitCB(() -> view.display(initialState));
        rawInput.setErrorHandler(this.errorHandler);
        rawInput.setMoveHandler(this);
    }

    @Override
    public void handleMove(Move move) {
        // end of series of moves
        // TODO: think about it, is this the right way?
        if (move == null) {
            view.display(state);
            return;
        }

        State newState;
        try {
            newState = state.applyMove(move);
        } catch (InvalidMoveException e) {
            errorHandler.handleError(e);
            return;
        }
        super.updateState(newState);
    }
}
