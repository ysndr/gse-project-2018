package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;

public class LinearMoveHintProvider implements HintProvider {

    private final Axis axis;

    private final int reach;

    public LinearMoveHintProvider(final Axis axis, final int reach) {
        this.axis = axis;
        this.reach = reach;
    }

    public LinearMoveHintProvider(final Axis axis) {
        this(axis, Integer.MAX_VALUE);
    }


    @Override
    public Set<? extends Hint> getHints(final State state) {
        Set<MoveHint> hints = new HashSet<>();
        Field selection = state.getSelection();


        if (selection == null) {
            return hints;
        }

        Set<Field> targets = new HashSet<>();
        HintProvider strategy = (s) -> null; // default


        switch (this.axis) {
            case VERTICAL:
                strategy = new MoveHintProvider(1, 0, reach)
                    .chain(new MoveHintProvider(-1, 0, reach));
                break;
            case HORIZONTAL:
                strategy = new MoveHintProvider(0, 1, reach)
                    .chain(new MoveHintProvider(0, -1, reach));
                break;
            case DIAGONAL:
                strategy = new MoveHintProvider(-1, 1, reach)
                    .chain(new MoveHintProvider(1, -1, reach))
                    .chain(new MoveHintProvider(1, 1, reach))
                    .chain(new MoveHintProvider(-1, -1, reach));
                break;
        }

        return strategy.getHints(state);
    }


    public enum Axis {
        VERTICAL,
        HORIZONTAL,
        DIAGONAL
    }


}
