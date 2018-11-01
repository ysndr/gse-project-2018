package de.techfak.gse.ysander.model.figures;

/**
 * The Rook figure.
 */
public class Rook extends Figure {

    public Rook(Color color) {
        super(color,'r', 'R');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new Rook(this.color());
    }
}
