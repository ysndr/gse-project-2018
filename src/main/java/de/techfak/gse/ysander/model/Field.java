package de.techfak.gse.ysander.model;

import java.util.Objects;

import de.techfak.gse.ysander.model.error.InvalidFieldException;


/**
 * A wrapper around coordinates on the grid.
 */
public class Field {
    final int x;

    final int y;

    Field(String coord) throws InvalidFieldException {
        this(Grid.getIndexX(coord.charAt(0)),
             Grid.getIndexY(coord.charAt(1)));
    }

    Field(int x, int y) throws InvalidFieldException {
        this.x = x;
        this.y = y;

        if (x >= Grid.GRID_SIZE || x < 0 || y >= Grid.GRID_SIZE || y < 0) {
            throw new InvalidFieldException(this);
        }
    }

    /**
     * Print the field.
     *
     * @return Verbose string representation of a field
     */
    public String toString() {
        return String.format("[Field %s @ (%s, %s)]", this.toCoords(), this.x, this.y);
    }

    /**
     * Creates a canonical representation of the field as string.
     *
     * @return coordinate String
     */
    String toCoords() {
        return ("" + Grid.X_KEYS.charAt(this.x) + Grid.Y_KEYS.charAt(this.y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Field field = (Field) o;
        return x == field.x &&
               y == field.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
