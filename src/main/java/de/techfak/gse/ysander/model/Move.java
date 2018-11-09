package de.techfak.gse.ysander.model;

import java.util.Objects;

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


    public static Move fromString(String move) throws InvalidMoveException {
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

        // TODO: NoFigureMoved exception to be thrown here already?

        return new Move(from, to);
    }

    public Field getFrom() {
        return from;
    }

    public Field getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move move = (Move) o;
        return Objects.equals(from, move.from) &&
               Objects.equals(to, move.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
