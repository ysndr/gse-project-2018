package de.techfak.gse.ysander.model.figures;

import java.util.Objects;

/**
 * The Knight figures.
 */
public class Knight extends Figure {

    private final String BLACK = "n";

    private final String WHITE = "N";

    private final String NAME = "knight";


    public Knight(Color color) {
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
        if (!(o instanceof Knight)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Knight knight = (Knight) o;
        return Objects.equals(BLACK, knight.BLACK) &&
               Objects.equals(WHITE, knight.WHITE) &&
               Objects.equals(NAME, knight.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}
