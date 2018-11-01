package de.techfak.gse.ysander.model.figures;

/**
 * The King figure.
 */
public class King extends Figure {

    public King(Color color) {
        super(color, 'k', 'K');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new King(this.color());
    }
}
