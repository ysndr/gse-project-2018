package de.techfak.gse.ysander.model.figures;

import java.util.List;
import java.util.Objects;

import de.techfak.gse.ysander.model.Field;

/**
 * The King figures.
 */
public class King extends Figure {


    private final String BLACK = "k";

    private final String WHITE = "K";

    private final String NAME = "king" ;

    public King(Color color) {
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
        if (!(o instanceof King)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final King king = (King) o;
        return Objects.equals(BLACK, king.BLACK) &&
               Objects.equals(WHITE, king.WHITE) &&
               Objects.equals(NAME, king.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}

