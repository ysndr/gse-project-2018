package de.techfak.gse.ysander.model.figures;

import java.util.Objects;
import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider.Axis;
import de.techfak.gse.ysander.model.rules.providers.LinearThreatHintProvider;

/**
 * The Queen figures.
 */
public class Queen extends Figure {

    private final String BLACK = "q";

    private final String WHITE = "Q";

    private final String NAME = "queen";


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
    public Set<? extends Hint> getHints(final State state, final Field base) {
        return new LinearMoveHintProvider(Axis.DIAGONAL)
            .chain(new LinearMoveHintProvider(Axis.VERTICAL))
            .chain(new LinearMoveHintProvider(Axis.HORIZONTAL))
            .chain(new LinearThreatHintProvider(LinearThreatHintProvider.Axis.DIAGONAL, this.color()))
            .chain(new LinearThreatHintProvider(LinearThreatHintProvider.Axis.VERTICAL, this.color()))
            .chain(new LinearThreatHintProvider(LinearThreatHintProvider.Axis.HORIZONTAL, this.color()))
            .getHints(state, base);
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

