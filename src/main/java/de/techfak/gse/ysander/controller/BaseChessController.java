package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.View;

public abstract class AbstractChessController implements ErrorHandler {

    protected Output<State> output;
    protected State state;

    AbstractChessController(final Output<State> output, final State initialState) {
        this.output = output;
        this.state = initialState;
    }

    AbstractChessController(final State initialState) {
        this((s) -> {}, initialState);
    }

    public void setOutput(final Output<State> output) {
        this.output = output;
    }

    @Override
    public void handleError(final ChessGameException e) {
        if (e instanceof InvalidMoveException) {
            output.display(state);
        }
        GlobalErrorEmergencyExit.getInstance().handleError(e);
    }

    void setState(State state) {
        this.state = state;
    }
}
