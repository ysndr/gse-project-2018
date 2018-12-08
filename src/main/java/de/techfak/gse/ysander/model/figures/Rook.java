package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The Rook figures.
 */
public class Rook extends Figure {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "r" : "R";
    }

    @Override
    public String canonicalName() {
        return "rook";
    }
}
