package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.figures.Figure;

/**
 * An Exception indicating that the referenced move was commited by the wrong
 * player.
 */
public class NotPlayersTurnException extends InvalidMoveException {

    private static final int ERROR_CODE = 103;

    private static final String REASON = "Invalid move (its not %s's turn)";

    /**
     * Creates an exception indicating that the player trying to make a move is
     * not in turn.
     *
     * @param color the color of the player
     */
    public NotPlayersTurnException(Figure.Color color) {
        super(ERROR_CODE, String.format(REASON, color));
    }
}
