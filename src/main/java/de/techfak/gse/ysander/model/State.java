package de.techfak.gse.ysander.model;

import java.util.Objects;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.error.NoFigureMovedException;

import static de.techfak.gse.ysander.model.figures.Figure.Color;

/**
 * Runtime state of the game.
 * Contains current grid configuration a well as the current player who's turn it is
 */
public final class State {

    private final Grid grid;

    private final Color color;

    private State(Grid grid, Color color) {
        this.grid = grid;
        this.color = color;
    }

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

        return State.fromFEN(split[0], split[1]);
    }

    // Modifiers

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

        return State.defaultState()
            .withGrid(Grid.fromFEN(grid))
            .withColor(Color.fromFEN(color));
    }

    public static State defaultState() {
        return new State(Grid.defaultGrid(), Color.WHITE);
    }

    public State withGrid(Grid grid) {
        return new State(grid, this.color);
    }

    // Converters

    public State withColor(Color color) {
        return new State(this.grid, color);
    }

    /**
     * Applies a {@link Move} onto the current State. If successful returns a
     * new State from containing the new current player in turn and the new Grid
     * configuration.
     *
     * @param move the move to apply.
     * @return Updated state
     * @throws InvalidMoveException if the move can not be performed for some
     * reason.
     */
    public State applyMove(Move move) throws InvalidMoveException {

        if (move.getFrom().equals(move.getTo())) {
            throw new NoFigureMovedException();
        }

        Grid gridAfterMove = this.grid.applyMove(move, this.color);
        Color playerAfterMove = (this.color == Color.BLACK) ? Color.WHITE : Color.BLACK;

        return new State(gridAfterMove, playerAfterMove);

    }

    /**
     * Converts state into FEN notation.
     *
     * @return FEN notated State
     */
    public String toFEN() {
        return String.format("%s %s", this.grid.toFEN(), this.color.toFEN());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(grid, state.grid) &&
               color == state.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, color);
    }


}
