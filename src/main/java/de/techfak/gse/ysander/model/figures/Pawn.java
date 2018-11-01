package de.techfak.gse.ysander.model.figures;

/**
 * The Pawn figure.
 */
public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color, 'p', 'P');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new Pawn(this.color());
    }
}
