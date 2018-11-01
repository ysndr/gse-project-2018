package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.FENParseException;

import java.util.Objects;

import static de.techfak.gse.ysander.model.figures.Figure.Color;

public class State {

    private final Grid grid;
    private final Color color;

    private State(Grid grid, Color color) {
        this.grid = grid;
        this.color = color;
    }

    public static State defaultState() {
        return new State(Grid.defaultGrid(), Color.WHITE);
    }


    static State fromFEN(String fen) {
        String[] split = fen.trim().split(" ");
        if (split.length != 2) throw new FENParseException();
        return State.defaultState()
            .withGrid(Grid.fromFEN(split[0]))
            .withColor(Color.fromFEN(split[1]));
    }

    private State withGrid(Grid grid) {
        return new State(grid, this.color);
    }

    private State withColor(Color color) {
        return new State(this.grid, color);
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
