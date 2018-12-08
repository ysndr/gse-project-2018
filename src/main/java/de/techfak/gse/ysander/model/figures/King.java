package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The King figures.
 */
public class King extends Figure {

    public King(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "k" : "K";
    }

    @Override
    public String canonicalName() {
        return "king";
    }
}

