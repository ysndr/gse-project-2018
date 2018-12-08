package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The Queen figures.
 */
public class Queen extends Figure {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "q" : "Q";
    }

    @Override
    public String canonicalName() {
        return "queen";
    }
}

