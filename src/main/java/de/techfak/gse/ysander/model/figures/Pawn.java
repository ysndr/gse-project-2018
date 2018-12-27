package de.techfak.gse.ysander.model.figures;

import java.util.Objects;
import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.HintProvider;
import de.techfak.gse.ysander.model.rules.providers.MoveHintProvider;
import de.techfak.gse.ysander.model.rules.providers.ThreatHintProvider;

/**
 * The Pawn figures.
 */
public class Pawn extends Figure {

    private final String BLACK = "p";

    private final String WHITE = "P";

    private final String NAME = "pawn";


    public Pawn(Color color) {
        super(color);
    }

    private Pawn(Color color, boolean moved) {
        super(color, moved);
    }

    @Override
    public Figure moved() {
        return new Pawn(this.color(), true);
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

        int ydelta = color().equals(Color.WHITE) ? -1 : 1;


        HintProvider hints = new ThreatHintProvider(1, ydelta, 1, this.color())
            .chain(new ThreatHintProvider(-1,  ydelta, 1, this.color()));

        if (getMoved()) {
            hints = hints.chain(new MoveHintProvider(0, ydelta, 1));
        } else {
            hints = hints.chain(new MoveHintProvider(0, ydelta, 2));
        }
        return hints.getHints(state, base);
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Pawn)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Pawn pawn = (Pawn) o;
        return Objects.equals(BLACK, pawn.BLACK) &&
               Objects.equals(WHITE, pawn.WHITE) &&
               Objects.equals(NAME, pawn.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), BLACK, WHITE, NAME);
    }
}


