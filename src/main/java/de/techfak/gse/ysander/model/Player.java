package de.techfak.gse.ysander.model;

import java.util.Objects;

import de.techfak.gse.ysander.model.figures.Figure;

public class Player {

    private final Figure.Color color;
    private final String name; //etc

    public Player(final Figure.Color color, final String name) {
        this.color = color;
        this.name = name;
    }

    public Figure.Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return color == player.color &&
               Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
