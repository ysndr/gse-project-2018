package de.techfak.gse.ysander.model;

import java.util.Objects;

import de.techfak.gse.ysander.model.error.InvalidFieldException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;

/**
 * Move wraps the origin and target field of a move.
 */
public final class Move {

    private final Field from;

    private final Field to;

    // Constructors

    /**
     * Binds two field into a move.
     *
     * @param from field on which the moves starts
     * @param to field on which the moves ends
     */
    public Move(Field from, Field to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Access start field.
     *
     * @return start field
     */
    public Field getFrom() {
        return from;
    }


    /**
     * Access end field.
     *
     * @return end field
     */
    public Field getTo() {
        return to;
    }

    /**
     * Converts the string notation of a move into the Move pair.
     * @param move the string version of a move
     * @return A move constructed by first extracting fields from the string
     * then combines them into a move
     * @throws InvalidMoveException if the fields are incorrect
     */
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
