package de.techfak.gse.ysander.model.figures;

/**
 * The Queen figure.
 */
public class Queen extends Figure {

    public Queen(Color color) {
        super(color, 'q', 'Q');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new Queen(this.color());
    }
}
