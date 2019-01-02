package de.techfak.gse.ysander.model.rules;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;

/**
 * Hint to perform moves to a target field.
 * Signals that the selecte a figure can be moved to the target field.
 * The operations should be applied when the target field is chosen.
 */
public class MoveHint implements Hint {
    private final Move move;

    public MoveHint(final Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public Field target() {
        return move.getTo();
    }

    @Override
    public State apply(final State state) {
        return state.applyMove(move);
    }
}


