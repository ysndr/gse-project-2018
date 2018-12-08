package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The Bishop figures.
 */
public class Bishop extends Figure {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "b" : "B";
    }

    @Override
    public String canonicalName() {
        return "bishop";
    }

}


