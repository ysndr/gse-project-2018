package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.figures.*;

import java.util.HashMap;
import java.util.Map;

import static de.techfak.gse.ysander.model.figures.Figure.Color.BLACK;
import static de.techfak.gse.ysander.model.figures.Figure.Color.WHITE;

/**
 * The internal representation of the grid.
 */
public class Grid {

    private static final int GRID_SIZE = 8;
    private static final String Y_KEYS = "12345678";
    private static final String X_KEYS = "abcdefgh";

    /**
     * Our internal representation of the grid
     */
    private Map<Field, Figure> grid;

    private Grid(Map<Field, Figure> grid) {
        this.grid = grid;
    }

    /**
     * Creates a preconfigured grd with the default common setup.
     * @return a setup grid
     */
    public static Grid defaultGrid() {
        Map<Field, Figure> grid = new HashMap<>();

        grid.put(new Field("a8"), new Rook(BLACK));
        grid.put(new Field("b8"), new Knight(BLACK));
        grid.put(new Field("c8"), new Bishop(BLACK));
        grid.put(new Field("d8"), new Queen(BLACK));
        grid.put(new Field("e8"), new King(BLACK));
        grid.put(new Field("f8"), new Bishop(BLACK));
        grid.put(new Field("g8"), new Knight(BLACK));
        grid.put(new Field("h8"), new Rook(BLACK));

        grid.put(new Field("a7"), new Pawn(BLACK));
        grid.put(new Field("b7"), new Pawn(BLACK));
        grid.put(new Field("c7"), new Pawn(BLACK));
        grid.put(new Field("d7"), new Pawn(BLACK));
        grid.put(new Field("e7"), new Pawn(BLACK));
        grid.put(new Field("f7"), new Pawn(BLACK));
        grid.put(new Field("g7"), new Pawn(BLACK));
        grid.put(new Field("h7"), new Pawn(BLACK));

        grid.put(new Field("a2"), new Rook(BLACK));
        grid.put(new Field("b2"), new Knight(BLACK));
        grid.put(new Field("c2"), new Bishop(BLACK));
        grid.put(new Field("d2"), new Queen(BLACK));
        grid.put(new Field("e2"), new King(BLACK));
        grid.put(new Field("f2"), new Bishop(BLACK));
        grid.put(new Field("g2"), new Knight(BLACK));
        grid.put(new Field("h2"), new Rook(BLACK));

        grid.put(new Field("a1"), new Pawn(BLACK));
        grid.put(new Field("b1"), new Pawn(BLACK));
        grid.put(new Field("c1"), new Pawn(BLACK));
        grid.put(new Field("d1"), new Pawn(BLACK));
        grid.put(new Field("e1"), new Pawn(BLACK));
        grid.put(new Field("f1"), new Pawn(BLACK));
        grid.put(new Field("g1"), new Pawn(BLACK));
        grid.put(new Field("h1"), new Pawn(BLACK));

        return new Grid(grid);
    }

    /**
     * A wrapper around coordinates on the grid.
     */
    private static class Field {
        final int x;
        final int y;

        Field(char x, int y) {
            this.x = x;
            this.y = y;
        }

        public Field(String coord) {
            this.y = X_KEYS.indexOf(coord.charAt(0));
            this.x = Y_KEYS.indexOf(coord.charAt(1));
        }

        /**
         * Creates a canonical representation of the field as string.
         * @return coordinate String
         */
        public String toCoords() {
            return ("" + X_KEYS.charAt(this.x) + Y_KEYS.charAt(this.y));
        }

        /**
         * Print the field
         * @return Verbose string representation of a field
         */
        public String toString() {
            return String.format("[Field %s @ (%s, %s)]", this.toCoords(), this.x, this.y);
        }

    }


}
