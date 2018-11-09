package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.figures.Figure;

public class NotPlayersTurnException extends InvalidMoveException {

    private static final int ERROR_CODE = 103;

    private static final String REASON = "Invalid move (its not %s's turn)";

    public NotPlayersTurnException(Figure.Color color) {
        super(ERROR_CODE, String.format(REASON, color));
    }
}
