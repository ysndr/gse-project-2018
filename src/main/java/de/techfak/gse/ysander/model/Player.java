package de.techfak.gse.ysander.model;

import java.util.Objects;

import de.techfak.gse.ysander.model.figures.Figure;

/**
 * Maps a Color to a physical Player.
 * Note: Does not yet do anything besides wrapping a color - quite redundant
 * I know ¯\_(ツ)_/¯
 */
public class Player {

    private final Figure.Color color;


    public Player(final Figure.Color color) {
        this.color = color;
    }

    public Figure.Color getColor() {
        return color;
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
        return color == player.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
