package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.view.View;

/**
 * The base of other chess controllers defines common actions main controllers
 * need to be able to handle.
 */
public abstract class BaseChessController {

    protected final ErrorHandler errorHandler;

    protected State state;

    protected View view;

    /**
     * Constructor of BaseController.
     * Binds, State View and error Handler
     *
     * @param state        that is managed by the controller
     * @param view         that the controller uses to display state
     * @param errorHandler that is called to show errors
     */
    protected BaseChessController(final State state, final View view, final ErrorHandler errorHandler) {
        this.state = state;
        this.view = view;
        this.errorHandler = errorHandler;

        this.view.setOnInitCB(() -> view.display(state));
    }

    protected void updateState(final State state) {
        this.state = state;
        this.view.display(state);
    }
}

