package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

/**
 * An Exception indicating that the referenced move moved no figure.
 */
public class NoFigureOnFieldException extends InvalidMoveException {

    private static final int ERROR_CODE = 102;

    private static final String REASON = "Invalid move ( %s No figure on this field)";

    /**
     * Creates an exception indicating that no figure stands on the requested
     * field.
     *
     * @param field the field on which no figure stands
     */
    public NoFigureOnFieldException(Field field) {
        super(ERROR_CODE, String.format(REASON, field));
    }

}
