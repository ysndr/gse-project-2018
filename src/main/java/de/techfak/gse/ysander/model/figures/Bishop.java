package de.techfak.gse.ysander.model.figures;

import java.util.Objects;
import java.util.Set;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider;
import de.techfak.gse.ysander.model.rules.providers.LinearThreatHintProvider;

/**
 * The Bishop figures.
 */
public class Bishop extends Figure {

    private final String BLACK = "b";

    private final String WHITE = "B";

    private final String NAME = "bishop";


    public Bishop(Color color) {
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
    public Set<? extends Hint> getHints(final State state) {
        return new LinearMoveHintProvider(LinearMoveHintProvider.Axis.DIAGONAL)
            .chain(new LinearThreatHintProvider(LinearThreatHintProvider.Axis.DIAGONAL))
            .getHints(state);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bishop)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Bishop bishop = (Bishop) o;
        return Objects.equals(BLACK, bishop.BLACK) &&
               Objects.equals(WHITE, bishop.WHITE) &&
               Objects.equals(NAME, bishop.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}


