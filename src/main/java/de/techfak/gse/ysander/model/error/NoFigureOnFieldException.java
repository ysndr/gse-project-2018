package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class NoFigureOnFieldException extends InvalidMoveException {

    private static final int ERROR_CODE = 102;
    private static final String REASON = "Invalid move ( %s No figure on this field)";

    public NoFigureOnFieldException(Field field) {
        super(ERROR_CODE, String.format(REASON, field));
    }

}
