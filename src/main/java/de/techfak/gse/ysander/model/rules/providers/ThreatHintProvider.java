package de.techfak.gse.ysander.model.rules.providers;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidFieldException;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.ThreatHint;

public class ThreatHintProvider implements HintProvider {

    private final int ydelta;

    private final int xdelta;

    private final int reach;

    private final Figure.Color egoColor;

    public ThreatHintProvider(final int xdelta, final int ydelta, final Figure.Color egoColor) {
        this(xdelta, ydelta, Integer.MAX_VALUE, egoColor);
    }


    public ThreatHintProvider(final int xdelta, final int ydelta, final int reach, final Figure.Color egoColor) {
        this.ydelta = ydelta;
        this.xdelta = xdelta;
        this.reach = reach;
        this.egoColor = egoColor;
    }

    /**
     * Checks all fields in one direction within {@code this.reach} steps.
     * 1. try to find the figure on the given field
     * 2. compare its color with the current players color
     * 3. stop if no figure found, or the figure has the players color or
     *    an otherwise invalid field is looked up.
     * @param state current state
     * @return List of fields to ove to in the given direcion
     */
    @Override
    public Set<? extends Hint> getHints(final State state, final Field base) {
        Set<Field> targets = new HashSet<>();

        if (base == null) {
            return new HashSet<>();
        }


        if (this.xdelta == 0 && this.ydelta == 0) {
            return new HashSet<>();
        }

        for (int i = 1; i <= this.reach; i++) {
            int xpos = base.getX() + i * xdelta;
            int ypos = base.getY() + i * ydelta;

            try {
                Field target = new Field(xpos, ypos);

                Optional<Boolean> counterPlayersFigureOnField = state.getGrid()
                    .getFigureOnField(target)
                    .map(Figure::color)
                    .map(c -> !c.equals(egoColor));

                if (!counterPlayersFigureOnField.isPresent()) {
                    continue;
                }

                if (counterPlayersFigureOnField.get()) {
                    targets.add(target);
                }

                break;

            } catch (InvalidFieldException e) {
                break;
            }
        }

        return targets.stream()
            .map(field -> new Move(base, field))
            .map(ThreatHint::new).collect(Collectors.toSet());

    }


}
