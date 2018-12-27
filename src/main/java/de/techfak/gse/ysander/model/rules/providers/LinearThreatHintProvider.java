package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;

public class LinearThreatHintProvider implements HintProvider {

    private final Axis axis;

    private final int reach;

    private final Figure.Color egoColor;

    public LinearThreatHintProvider(final Axis axis, final int reach, final Figure.Color egoColor) {
        this.axis = axis;
        this.reach = reach;
        this.egoColor = egoColor;
    }

    public LinearThreatHintProvider(final Axis axis, final Figure.Color egoColor) {
        this(axis, Integer.MAX_VALUE, egoColor);
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
            case HORIZONTAL:
                strategy = new ThreatHintProvider(1, 0, reach, egoColor)
                    .chain(new ThreatHintProvider(-1, 0, reach, egoColor));
                break;
            case VERTICAL:
                strategy = new ThreatHintProvider(0, 1, reach, egoColor)
                    .chain(new ThreatHintProvider(0, -1, reach, egoColor));
                break;
            case DIAGONAL:
                strategy = new ThreatHintProvider(-1, 1, reach, egoColor)
                    .chain(new ThreatHintProvider(1, -1, reach, egoColor))
                    .chain(new ThreatHintProvider(1, 1, reach, egoColor))
                    .chain(new ThreatHintProvider(-1, -1, reach, egoColor));
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
