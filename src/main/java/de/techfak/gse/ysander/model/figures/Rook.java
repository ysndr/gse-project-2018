package de.techfak.gse.ysander.model.figures;

import java.util.List;
import java.util.Objects;

import de.techfak.gse.ysander.model.Field;

/**
 * The Rook figures.
 */
public class Rook extends Figure {

    private final String BLACK = "r";

    private final String WHITE = "R";

    private final String NAME = "rook";

    public Rook(Color color) {
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
        if (!(o instanceof Rook)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Rook rook = (Rook) o;
        return Objects.equals(BLACK, rook.BLACK) &&
               Objects.equals(WHITE, rook.WHITE) &&
               Objects.equals(NAME, rook.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}
