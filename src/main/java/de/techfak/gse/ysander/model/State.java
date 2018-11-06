package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.model.error.NoFigureMovedException;
import de.techfak.gse.ysander.model.figures.Figure;

import java.util.Objects;

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

    public static State defaultState() {
        return new State(Grid.defaultGrid(), Color.WHITE);
    }


    public static State fromFEN(String fen) throws FENParseException {

        String[] split = fen.trim().split(" ");
        if (split.length != 2) {
            throw new FENParseException();
        }

        return State.fromFEN(split[0], split[1]);
    }

    public static State fromFEN(String grid, String color) throws FENParseException {

        return State.defaultState()
            .withGrid(Grid.fromFEN(grid))
            .withColor(Color.fromFEN(color));
    }

    public State withGrid(Grid grid) {
        return new State(grid, this.color);
    }

    public State withColor(Color color) {
        return new State(this.grid, color);
    }

    public State applyMove(Move move) throws InvalidMoveException {

        if (move.getFrom().equals(move.getTo())) {
            throw new NoFigureMovedException();
        }

        Grid gridAfterMove = this.grid.applyMove(move, this.color);
        Color playerAfterMove = (this.color == Color.BLACK) ? Color.WHITE : Color.BLACK;

        return new State(gridAfterMove, playerAfterMove);

    }

    public String toFEN() {
        return String.format("%s %s", this.grid.toFEN(), this.color.toFEN());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(grid, state.grid) &&
            color == state.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, color);
    }


}
