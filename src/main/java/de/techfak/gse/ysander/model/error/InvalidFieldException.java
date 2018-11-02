package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class InvalidFieldException extends ChessGameException {

    private static final String REASON = "Invalid coordinates (%s)";
    private final Field field;

    public InvalidFieldException(Field field) {
        this.field = field;
    }

    @Override
    public String getReason() {
        return String.format(REASON, field);
    }
}
