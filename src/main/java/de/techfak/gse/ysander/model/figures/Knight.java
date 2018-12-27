package de.techfak.gse.ysander.model.figures;

import java.util.Objects;
import java.util.Set;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.MoveHintProvider;
import de.techfak.gse.ysander.model.rules.providers.ThreatHintProvider;

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
    public Set<? extends Hint> getHints(final State state) {
        return new MoveHintProvider(-2, 1, 1)
            .chain(new MoveHintProvider(-2, -1, 1))
            .chain(new MoveHintProvider(-1, 2, 1))
            .chain(new MoveHintProvider(-1, -2, 1))
            .chain(new MoveHintProvider(2, 1, 1))
            .chain(new MoveHintProvider(2, -1, 1))
            .chain(new MoveHintProvider(1, 2, 1))
            .chain(new MoveHintProvider(1, -2, 1))
            .chain(new ThreatHintProvider(-2, 1, 1))
            .chain(new ThreatHintProvider(-2, -1, 1))
            .chain(new ThreatHintProvider(-1, 2, 1))
            .chain(new ThreatHintProvider(-1, -2, 1))
            .chain(new ThreatHintProvider(2, 1, 1))
            .chain(new ThreatHintProvider(2, -1, 1))
            .chain(new ThreatHintProvider(1, 2, 1))
            .chain(new ThreatHintProvider(1, -2, 1))
            .getHints(state);
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
