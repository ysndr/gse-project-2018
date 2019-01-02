package de.techfak.gse.ysander.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.error.InvalidFieldException;
import de.techfak.gse.ysander.model.figures.*;

import static de.techfak.gse.ysander.model.figures.Figure.Color.BLACK;
import static de.techfak.gse.ysander.model.figures.Figure.Color.WHITE;

/**
 * Construct Grid objects.
 */
public class GridBuilder {

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

    private Map<Field, Figure> grid;

    /**
     * Creates a preconfigured grd with the default common setup.
     *
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


        grid.put(new Field("a1"), new Rook(WHITE));
        grid.put(new Field("b1"), new Knight(WHITE));
        grid.put(new Field("c1"), new Bishop(WHITE));
        grid.put(new Field("d1"), new Queen(WHITE));
        grid.put(new Field("e1"), new King(WHITE));
        grid.put(new Field("f1"), new Bishop(WHITE));
        grid.put(new Field("g1"), new Knight(WHITE));
        grid.put(new Field("h1"), new Rook(WHITE));

        grid.put(new Field("a2"), new Pawn(WHITE));
        grid.put(new Field("b2"), new Pawn(WHITE));
        grid.put(new Field("c2"), new Pawn(WHITE));
        grid.put(new Field("d2"), new Pawn(WHITE));
        grid.put(new Field("e2"), new Pawn(WHITE));
        grid.put(new Field("f2"), new Pawn(WHITE));
        grid.put(new Field("g2"), new Pawn(WHITE));
        grid.put(new Field("h2"), new Pawn(WHITE));

        return new Grid(grid);
    }

    /**
     * Creates a Grid from a given String in FENotation.
     *
     * @param fen Serialized grid
     * @return deserialized Grid
     * @throws FENParseException if FEN format is not obeyed
     */
    public static Grid fromFEN(String fen) throws FENParseException {
        String[] rows = fen.split("/");
        if (rows.length < Grid.GRID_SIZE) {
            throw new FENParseException();
        }

        Map<Field, Figure> grid = new HashMap<>();
        Map<String, Figure> mapping = new HashMap<>();
        Arrays.asList(FIGURES).forEach((Figure f) -> mapping.put(f.symbol(), f));

        for (int r = 0; r < rows.length; r++) {
            String row = rows[r];
            int processed = 0;
            for (int i = 0; i < row.length(); i++) {
                String field = String.valueOf(row.charAt(i));

                if (!mapping.containsKey(field)) {
                    try {
                        processed += Integer.parseInt(field);
                    } catch (NumberFormatException e) {
                        throw new FENParseException();
                    }
                    continue;
                }
                try {
                    grid.put(new Field(processed, r), mapping.get(field));
                } catch (InvalidFieldException e) {
                    throw new FENParseException();
                }

                processed++;
            }
            if (processed != Grid.GRID_SIZE) {
                throw new FENParseException();
            }
        }

        return new Grid(grid);
    }


    /**
     * Create Builder from grid.
     *
     * @param grid
     * @return GridBuilder instance
     */
    static GridBuilder of(Grid grid) {
        return new GridBuilder().setGrid(grid.getGrid());
    }

    /**
     * Set a grid.
     *
     * @param grid the map to use as grid
     * @return GridBuilder instance
     */
    GridBuilder setGrid(final Map<Field, Figure> grid) {
        this.grid = grid;
        return this;
    }

    /**
     * Delete a {@link Figure} on the grid.
     *
     * @param field field to clear
     * @return GridBuilderInstance
     */
    GridBuilder clearField(final Field field) {
        this.grid.remove(field);
        return this;
    }

    /**
     * Apply a move by setting the targets fields value to the value of the
     * source field.
     * If the source field is not set, do nothing.
     *
     * @param move to apply
     * @return GridBuilder
     */
    GridBuilder applyMove(final Move move) {
        Figure movedFig = this.grid.remove(move.getFrom());
        if (movedFig == null) {
            return this;
        }

        this.setField(move.getTo(), movedFig.moved());

        return this;
    }

    /**
     * Set a specific {@link Field} to hold the given {@link Figure}.
     *
     * @param field  key
     * @param figure value
     * @return GridBuilder Instance
     */
    public GridBuilder setField(final Field field, final Figure figure) {
        this.grid.put(field, figure);
        return this;
    }

    /**
     * Assemble {@link Grid}.
     *
     * @return {@link Grid}
     */
    public Grid createGrid() {
        return new Grid(grid);
    }
}
