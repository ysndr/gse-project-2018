package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.figures.Figure;

/**
 * A builder class to create states by setting all values for a state or
 * converting from fen notation String.
 */
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
            .withGrid(GridBuilder.fromFEN(grid))
            .withColor(Figure.Color.fromFEN(color));
    }

    public static StateBuilder of(State state) {
        return new StateBuilder()
            .setSelection(state.getSelection())
            .setColor(state.getColor())
            .setGrid(state.getGrid());
    }


    /**
     * Creates a default state with default {@link Grid} and White as starting
     * player.
     *
     * @return default state
     */
    public static State defaultState() {
        return new StateBuilder().setGrid(GridBuilder.defaultGrid()).setColor(Figure.Color.WHITE).createState();
    }

    /**
     * Set the grid.
     *
     * @param grid to be set
     * @return this builder for fluent chaining
     */
    public StateBuilder setGrid(final Grid grid) {
        this.grid = grid;
        return this;
    }

    /**
     * Set the current players color.
     *
     * @param color of the current player
     * @return this builder for fluent chaining
     */
    public StateBuilder setColor(final Figure.Color color) {
        this.color = color;
        return this;
    }

    /**
     * Set current selected Field.
     *
     * @param selection
     * @return this builder for fluent chaining
     */
    public StateBuilder setSelection(final Field selection) {
        this.selection = selection;
        return this;
    }

    /**
     * Delete selection
     * @return
     */
    public StateBuilder resetSelection() {
        this.selection = null;
        return this;
    }

    /**
     * Build a full state from gathered information.
     *
     * @return packed State
     */
    public State createState() {
        if (grid == null) {
            grid = GridBuilder.defaultGrid();
        }
        if (color == null) {
            color = Figure.Color.WHITE;
        }
        return new State(grid, color, selection);
    }




}
