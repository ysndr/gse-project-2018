package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class NoFigureOnFieldException extends InvalidMoveException {

    private final Field field;
    private static final int ERROR_CODE = 102;
    private static final String REASON = "Invalid move ( %s No figure on this field)";

    public NoFigureOnFieldException(Field field) {
        this.field = field;
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
        return String.format(REASON, field);
    }


}
