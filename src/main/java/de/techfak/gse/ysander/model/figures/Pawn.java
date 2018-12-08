package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The Pawn figures.
 */
public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "p" : "P";
    }

    @Override
    public String canonicalName() {
        return "pawn";
    }
}


