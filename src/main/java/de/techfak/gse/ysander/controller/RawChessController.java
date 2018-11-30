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

    public RawChessController(final View view,
                              final RawInputDriver rawInput,
                              final State initialState) {
        super(view, initialState);

        this.rawInput = rawInput;

        view.setOnInitCB(() -> view.display(initialState));
        rawInput.setErrorHandler(this);
        rawInput.setMoveHandler(this);
    }

    @Override
    public void handleMove(Move move) {
        State newState;
        try {
             newState = state.applyMove(move);
        } catch (InvalidMoveException e) {
            this.handleError(e);
            return;
        }
        super.setState(newState);
        output.display(newState);
    }
}
