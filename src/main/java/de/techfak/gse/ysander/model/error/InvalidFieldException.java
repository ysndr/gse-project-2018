package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class InvalidFieldException extends ChessGameException {

    private static final String REASON = "Invalid coordinates (%s)";

    public InvalidFieldException(Field field) {
        super(String.format(REASON, field));
    }
}
