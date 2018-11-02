package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.figures.Figure;

public class NotPlayersTurnException extends InvalidMoveException {

    private final Figure.Color color;
    private static final int ERROR_CODE = 103;
    private static final String REASON = "Invalid move (its not %s's turn)";

    public NotPlayersTurnException(Figure.Color color) {
        this.color = color;
    }

    /**
     * Returns the exceptions error code.
     *
     * @return error code
     */
    public int getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public String getReason() {
        return String.format(REASON, color);
    }

}
