package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.figures.*;

import java.util.*;

import static de.techfak.gse.ysander.model.figures.Figure.Color.BLACK;
import static de.techfak.gse.ysander.model.figures.Figure.Color.WHITE;

/**
 * The internal representation of the grid.
 */
public class Grid {

    private static final int GRID_SIZE = 8;
    private static final Figure[] FIGURES = {
        new Rook(WHITE), new Rook(BLACK),
        new Knight(WHITE), new Knight(BLACK),
        new Bishop(WHITE), new Bishop(BLACK),
        new Queen(WHITE), new Queen(BLACK),
        new King(WHITE), new King(BLACK),
        new Bishop(WHITE), new Bishop(BLACK),
        new Knight(WHITE), new Knight(BLACK),
        new Rook(WHITE), new Rook(BLACK),
        new Pawn(WHITE), new Pawn(BLACK)
    };
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
    static Grid defaultGrid() {
        Map<Field, Figure> grid = new HashMap<>();

        grid.put(new Field("a8"), new Rook(WHITE));
        grid.put(new Field("b8"), new Knight(WHITE));
        grid.put(new Field("c8"), new Bishop(WHITE));
        grid.put(new Field("d8"), new Queen(WHITE));
        grid.put(new Field("e8"), new King(WHITE));
        grid.put(new Field("f8"), new Bishop(WHITE));
        grid.put(new Field("g8"), new Knight(WHITE));
        grid.put(new Field("h8"), new Rook(WHITE));

        grid.put(new Field("a7"), new Pawn(WHITE));
        grid.put(new Field("b7"), new Pawn(WHITE));
        grid.put(new Field("c7"), new Pawn(WHITE));
        grid.put(new Field("d7"), new Pawn(WHITE));
        grid.put(new Field("e7"), new Pawn(WHITE));
        grid.put(new Field("f7"), new Pawn(WHITE));
        grid.put(new Field("g7"), new Pawn(WHITE));
        grid.put(new Field("h7"), new Pawn(WHITE));


        grid.put(new Field("a1"), new Rook(BLACK));
        grid.put(new Field("b1"), new Knight(BLACK));
        grid.put(new Field("c1"), new Bishop(BLACK));
        grid.put(new Field("d1"), new Queen(BLACK));
        grid.put(new Field("e1"), new King(BLACK));
        grid.put(new Field("f1"), new Bishop(BLACK));
        grid.put(new Field("g1"), new Knight(BLACK));
        grid.put(new Field("h1"), new Rook(BLACK));

        grid.put(new Field("a2"), new Pawn(BLACK));
        grid.put(new Field("b2"), new Pawn(BLACK));
        grid.put(new Field("c2"), new Pawn(BLACK));
        grid.put(new Field("d2"), new Pawn(BLACK));
        grid.put(new Field("e2"), new Pawn(BLACK));
        grid.put(new Field("f2"), new Pawn(BLACK));
        grid.put(new Field("g2"), new Pawn(BLACK));
        grid.put(new Field("h2"), new Pawn(BLACK));

        return new Grid(grid);
    }

    /**
     * Creates a Grid from a given String in FENotation
     *
     * @param fen Serialized grid
     * @return deserialized Grid
     * @throws FENParseException if FEN format is not obeyed
     */
    static Grid fromFEN(String fen) throws FENParseException {
        String[] rows = fen.split("/");
        if (rows.length < GRID_SIZE) throw new FENParseException();

        Map<Field, Figure> grid = new HashMap<>();
        Map<Character, Figure> mapping = new HashMap<>();
        Arrays.asList(FIGURES).forEach((Figure f) -> mapping.put(f.symbol(), f));

        for (int r = 0; r < rows.length; r++) {
            String row = rows[r];
            int processed = 0;
            for (int i = 0; i < row.length(); i++) {
                char field = row.charAt(i);

                if (!mapping.containsKey(field)) {
                    try {
                        processed += Integer.parseInt(String.valueOf(field));
                    } catch (NumberFormatException e) {
                        throw new FENParseException();
                    }
                    continue;
                }

                grid.put(new Field(processed, r), mapping.get(field).copy());
                processed++;
            }
            if (processed != GRID_SIZE) throw new FENParseException();
        }

        return new Grid(grid);
    }

    String toFEN() {
        List<String > rows = new ArrayList<>();

        for (String y: Y_KEYS.split("")) {
            int empty = 0;
            StringBuilder row = new StringBuilder();
            for (String x: X_KEYS.split("")) {
                Field field = new Field(x+y);
                Figure fig = grid.get(field);

                if (fig == null) {
                    empty++;
                    continue;
                }

                if (empty != 0) {
                    row.append(empty);
                }
                row.append(fig.symbol());
                empty = 0;
            }
            if (empty != 0) {
                row.append(empty);
            }
            rows.add(row.toString());

        }

        return String.join("/", rows);
    }

    public Set<Map.Entry<Field, Figure>> getFigures() {
        return this.grid.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        return Objects.equals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }

    /**
     * A wrapper around coordinates on the grid.
     */
    private static class Field {
        final int x;
        final int y;

        Field(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Field(String coord) {
            this.x = X_KEYS.indexOf(coord.charAt(0));
            this.y = Y_KEYS.indexOf(coord.charAt(1));
        }

        /**
         * Creates a canonical representation of the field as string.
         * @return coordinate String
         */
        String toCoords() {
            return ("" + X_KEYS.charAt(this.x) + Y_KEYS.charAt(this.y));
        }

        /**
         * Print the field
         * @return Verbose string representation of a field
         */
        public String toString() {
            return String.format("[Field %s @ (%s, %s)]", this.toCoords(), this.x, this.y);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Field field = (Field) o;
            return x == field.x &&
                y == field.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}
