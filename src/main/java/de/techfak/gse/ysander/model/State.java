package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.figures.Figure;

public class State {

    public final Grid grid;
    public final Figure.Color color;

    public State(Grid grid, Figure.Color color) {
        this.grid = grid;
        this.color = color;
    }

    public static State defaultState() {
        return new State(Grid.defaultGrid(), Figure.Color.WHITE);
    }

    public State withGrid(Grid grid) {
        return new State(grid, this.color);
    }

    public State withColor(Figure.Color color) {
        return new State(this.grid, color);
    }


    public String toFEN() {
        return String.format("%s %s", this.grid.toFEN(), this.color.toFEN());
    }

}
