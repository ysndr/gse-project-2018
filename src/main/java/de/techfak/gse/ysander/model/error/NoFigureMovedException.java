package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class NoFigureMovedException extends InvalidMoveException {

    private static final int ERROR_CODE = 104;
    private static final String REASON = "Invalid move (fields are not different)";


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
        return REASON;
    }

}
