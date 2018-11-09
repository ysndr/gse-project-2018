package de.techfak.gse.ysander.model.error;

public class NoFigureMovedException extends InvalidMoveException {

    private static final int ERROR_CODE = 104;

    private static final String REASON = "Invalid move (fields are not different)";

    public NoFigureMovedException() {
        super(ERROR_CODE, REASON);
    }

}
