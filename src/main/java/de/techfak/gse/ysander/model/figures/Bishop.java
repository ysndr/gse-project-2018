package de.techfak.gse.ysander.model.figures;

/**
 * The Bishop figure.
 */
public class Bishop extends Figure {

    public Bishop(Color color) {
        super(color, 'b', 'B');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new Bishop(this.color());
    }
}
