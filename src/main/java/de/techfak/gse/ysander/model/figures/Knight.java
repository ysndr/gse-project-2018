package de.techfak.gse.ysander.model.figures;

import java.util.List;

import de.techfak.gse.ysander.model.Field;

/**
 * The Knight figures.
 */
public class Knight extends Figure {

    public Knight(Color color) {
        super(color);
    }


    @Override
    public String symbol() {
        return this.color() == Color.WHITE ? "n" : "N";
    }

    @Override
    public String canonicalName() {
        return "knight";
    }
}
