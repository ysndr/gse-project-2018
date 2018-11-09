package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

/**
 * An Exception indicating that an invalid field was referenced.
 */
public class InvalidFieldException extends ChessGameException {

    private static final String REASON = "Invalid coordinates (%s)";

    /**
     * Creates an exception related to an invalid field.
     *
     * @param field that was invalid
     */
    public InvalidFieldException(Field field) {
        super(String.format(REASON, field));
    }
}
