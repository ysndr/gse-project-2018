package de.techfak.gse.ysander.model.rules;

import de.techfak.gse.ysander.model.Move;


/**
 * Hint to perform checks, which are essentially just moves.
 * Yet subtyped because threads are hinted differently.
 */
public class ThreatHint extends MoveHint {

    public ThreatHint(final Move move) {
        super(move);
    }

    @Override
    public String toString() {
        return "ThreatHint{move=" + getMove() + "}";
    }
}


