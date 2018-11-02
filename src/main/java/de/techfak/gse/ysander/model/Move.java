package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.InvalidFieldException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;

/**
 * Move wraps the origin and target field of a move
 */
public final class Move {

    private final Field from;
    private final Field to;

    public Move(Field from, Field to) {
        this.from = from;
        this.to = to;
    }


    public static  Move fromString(String move) throws InvalidMoveException {
        String[] fields = move.trim().split("-");

        if (fields.length != 2) {
            throw new InvalidMoveException();
        }

        Field from;
        Field to;
        try {
            from = new Field(fields[0]);
            to = new Field(fields[1]);
        } catch (InvalidFieldException e) {
             throw new InvalidMoveException();
        }

        if (from.equals(to)) {
            throw new InvalidMoveException();
        }

        return new Move(from, to);
    }

    public Field getFrom() {
        return from;
    }

    public Field getTo() {
        return to;
    }
}
