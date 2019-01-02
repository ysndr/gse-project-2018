package de.techfak.gse.ysander.model;

import java.util.*;

import de.techfak.gse.ysander.model.error.NoFigureOnFieldException;
import de.techfak.gse.ysander.model.error.NotPlayersTurnException;
import de.techfak.gse.ysander.model.figures.Figure;

/**
 * The internal representation of the grid.
 */
public final class Grid {

    public static final String Y_KEYS = "87654321";

    public static final String X_KEYS = "abcdefgh";

    public static final int GRID_SIZE = 8;


    // Constructors

    /**
     * Our internal representation of the grid.
     */
    private final Map<Field, Figure> grid;

    Grid(Map<Field, Figure> grid) {
        this.grid = grid;
    }


    // Accessors

    /**
     * Convert fields char represented index into absolut int coordinate on the
     * grid.
     *
     * @param key field key in x
     * @return x index
     */
    public static int getIndexX(char key) {
        return X_KEYS.indexOf(key);
    }

    /**
     * Convert fields char represented index into absolut int coordinate on the
     * grid.
     *
     * @param key field key in y
     * @return y index
     */
    public static int getIndexY(char key) {
        return Y_KEYS.indexOf(key);
    }

    /**
     * Savely access grid.
     *
     * @return copy of the grid map
     */
    public Map<Field, Figure> getGrid() {
        return new HashMap<>(grid);
    }

    /**
     * Extract the fields with figures on them.
     *
     * @return a list of pairs of field and figures
     */
    public Set<Map.Entry<Field, Figure>> getFigures() {
        return this.grid.entrySet();
    }

    // Converters

    /**
     * Tries to find the figure on the given field.
     *
     * @param field field to search for figure on it for
     * @return returns an {@link Optional} filled with the figre or else nothing
     */
    public Optional<Figure> getFigureOnField(Field field) {
        return Optional.ofNullable(this.grid.get(field));
    }

    // Modifiers

    /**
     * Apply a move onto the field by trying to make the move and if successul
     * return a new Grid with the new configuration.
     *
     * @param move          the move to apply
     * @param currentPlayer the player currently in turn
     * @return new grid with changed configuration
     * @throws NoFigureOnFieldException if the move references an empty field
     * @throws NotPlayersTurnException  if the given user tries to move a figures
     *                                  of its opponents color
     */
    public Grid applyMove(Move move, Figure.Color currentPlayer)
        throws NoFigureOnFieldException, NotPlayersTurnException {

        Figure startFigure = grid.get(move.getFrom());

        if (startFigure == null) {
            throw new NoFigureOnFieldException(move.getFrom());
        }

        if (startFigure.color() != currentPlayer) {
            throw new NotPlayersTurnException(startFigure.color());
        }

        return this.builder().applyMove(move).createGrid();
    }


    /**
     * Create a builder from this instance to make immutable changes.
     *
     * @return a builder Instance
     */
    public GridBuilder builder() {
        return GridBuilder.of(this);
    }


    /**
     * Generate FEN notation from internal representation.
     *
     * @return FEN notated String
     */
    String toFEN() {
        List<String> rows = new ArrayList<>();

        for (String y : Y_KEYS.split("")) {
            int empty = 0;
            StringBuilder row = new StringBuilder();
            for (String x : X_KEYS.split("")) {
                Field field = new Field(x + y);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grid grid1 = (Grid) o;
        return Objects.equals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }

    @Override
    public String toString() {
        return "Grid{" +
               "grid=" + grid +
               '}';
    }


}
