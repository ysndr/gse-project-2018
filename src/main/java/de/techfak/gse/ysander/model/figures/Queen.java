package de.techfak.gse.ysander.model.figures;

import java.util.List;
import java.util.Objects;

import de.techfak.gse.ysander.model.Field;

/**
 * The Queen figures.
 */
public class Queen extends Figure {

    private  final String BLACK = "q";

    private  final String WHITE = "Q";

    private  final String NAME = "queen";


    public Queen(Color color) {
        super(color);
    }

    @Override
    public String symbol() {
        return this.color() == Color.BLACK ? BLACK : WHITE;
    }

    @Override
    public String canonicalName() {
        return NAME;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Queen)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Queen queen = (Queen) o;
        return Objects.equals(BLACK, queen.BLACK) &&
               Objects.equals(WHITE, queen.WHITE) &&
               Objects.equals(NAME, queen.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}

