package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.drivers.RawInputDriver;
import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.handlers.MoveHandler;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> view.
 */
public class ChessController implements MoveHandler, ErrorHandler {


    private final View view;

    private final RawInputDriver rawInput;

    private State state;

    public ChessController(final View view,
                           final RawInputDriver rawInput,
                           final State initialState) {
        this.view = view;
        this.rawInput = rawInput;
        this.state = initialState;

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
        view.display(newState);
    }

    @Override
    public void handleError(final ChessGameException e) {
        if (e instanceof InvalidMoveException) {
            view.display(state);
        }
        System.exit(e.getErrorCode());
    }
}
