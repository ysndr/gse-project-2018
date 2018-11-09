package de.techfak.gse.ysander.model.error;

/**
 * An Exception indicating that the referenced move moved no figure.
 */
public class NoFigureMovedException extends InvalidMoveException {

    private static final int ERROR_CODE = 104;

    private static final String REASON = "Invalid move (fields are not different)";

    /**
     * Creates an exception indicating that figure was moved during the move.
     */
    public NoFigureMovedException() {
        super(ERROR_CODE, REASON);
    }

}
