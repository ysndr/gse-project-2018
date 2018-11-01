package de.techfak.gse.ysander.model.figures;

/**
 * The Knight figure.
 */
public class Knight extends Figure {

    public Knight(Color color) {
        super(color, 'n', 'N');
    }

    /**
     * Creates a copy of the Figure.
     *
     * @return a copy of itself
     */
    @Override
    public Figure copy() {
        return new Knight((this.color()));
    }
}
