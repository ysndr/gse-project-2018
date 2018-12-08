package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.communication.handlers.MoveHandler;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.error.GameInterruptedException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;

/**
 * Converts a line of raw input into a consecutively handled list of Move
 * inputs.
 */
public class RawDriver implements RawInputDriver {

    private MoveHandler moveHandler = move -> { };

    private ErrorHandler errorHandler = e -> { };


    @Override
    public void setMoveHandler(final MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    @Override
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void handleRawInput(final String input) {
        if (input.trim().length() == 0) {
            errorHandler.handleError(new GameInterruptedException());
            return;
        }
        for (String moveString : input.trim().split(";")) {
            Move move;
            try {
                move = Move.fromString(moveString);
                moveHandler.handleMove(move);
            } catch (InvalidMoveException e) {
                errorHandler.handleError(e);
                return;
            }
        }
        moveHandler.handleMove(null);
    }
}
