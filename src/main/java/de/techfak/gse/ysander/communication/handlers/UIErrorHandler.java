package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.view.View;

public class UIErrorHandler implements ErrorHandler {

    private final View view;

    public UIErrorHandler(final View view) {
        this.view = view;
    }

    @Override
    public void handleError(final ChessGameException e) {
        this.view.message(e.getMessage());
    }
}
