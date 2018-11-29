package de.techfak.gse.ysander.model;

import java.util.Objects;

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

    State(Grid grid, Color color) {
        this.grid = grid;
        this.color = color;
    }

    // Modifiers
    public State withGrid(Grid grid) {
        return new StateBuilder().setGrid(grid).setColor(this.color).createState();
    }
    public State withColor(Color color) {
        return new StateBuilder().setGrid(this.grid).setColor(color).createState();
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

        return new StateBuilder().setGrid(gridAfterMove).setColor(playerAfterMove).createState();

    }


    // Converters
    /**
     * Converts state into FEN notation.
     *
     * @return FEN notated State
     */
    public String toFEN() {
        return String.format("%s %s", this.grid.toFEN(), this.color.toFEN());
    }


    // Util
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
