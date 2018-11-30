package de.techfak.gse.ysander.model;

import java.util.Optional;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.figures.Figure;

public class StateBuilder {
    private Grid grid;
    private Figure.Color color;
    private Field selection;

    /**
     * Converts a FEN string into a state where the players color is included in
     * the string.
     *
     * @param fen the fen string
     * @return A state representing the FEN
     * @throws FENParseException if the fen could not be parsed
     */
    public static State fromFEN(String fen) throws FENParseException {
        String[] split = fen.trim().split(" ");
        if (split.length != 2) {
            throw new FENParseException();
        }

        return fromFEN(split[0], split[1]);
    }

    /**
     * Converts a FEN string into a state where the players color is <b>not</b>
     * included in the string.
     *
     * @param grid  the grid part of the fen
     * @param color the initial players color
     * @return A state representing the FEN
     * @throws FENParseException if the fen could not be parsed
     */
    public static State fromFEN(String grid, String color) throws FENParseException {
        return defaultState()
            .withGrid(Grid.fromFEN(grid))
            .withColor(Figure.Color.fromFEN(color));
    }


    /**
     * Creates a default state with default {@link Grid} and White as starting
     * player
     * @return default state
     */
    public static State defaultState() {
        return new StateBuilder().setGrid(Grid.defaultGrid()).setColor(Figure.Color.WHITE).createState();
    }

    /**
     * Set the grid
     * @param grid
     * @return
     */
    public StateBuilder setGrid(final Grid grid) {
        this.grid = grid;
        return this;
    }

    public StateBuilder setColor(final Figure.Color color) {
        this.color = color;
        return this;
    }

    StateBuilder setSelection(final Field selection) {
        this.selection = selection;
        return this;
    }

    public State createState() {
        if (grid == null) {
            grid = Grid.defaultGrid();
        }
        if (color == null) {
            color = Figure.Color.WHITE;
        }
        return new State(grid, color, selection);
    }




}
