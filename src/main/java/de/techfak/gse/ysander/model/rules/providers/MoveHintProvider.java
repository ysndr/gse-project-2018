package de.techfak.gse.ysander.model.rules.providers;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidFieldException;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;

public class MoveHintProvider implements HintProvider {

    private final int ydelta;
    private final int xdelta;
    private final int reach;

    public MoveHintProvider(final int xdelta, final int ydelta, final int reach) {
        this.ydelta = ydelta;
        this.xdelta = xdelta;
        this.reach = reach;
    }


    public MoveHintProvider(final int xdelta, final int ydelta) {
        this(xdelta, ydelta, Integer.MAX_VALUE);
    }


    /**
     * Checks all fields in one direction within {@code this.reach} steps.
     * Stops if reaches field with figure present on it, or when attempting to
     * target an otherwise invalid field.
     * @param state current state
     * @return List of fields to ove to in the given direcion
     */
    @Override
    public Set<? extends Hint> getHints(final State state) {
        Field selection = state.getSelection(); // guaranteed to be != null when this method is called
        Set<Field> targets = new HashSet<>();

        if (selection == null) {
            return new HashSet<>();
        }

        for (int i = 1; i <= this.reach; i++) {
            int xpos = selection.getX() + i * xdelta;
            int ypos = selection.getY() + i * ydelta;

            try {
                Field target = new Field(xpos, ypos);
                if (state.getGrid().getFigureOnField(target).isPresent()) {
                    break;
                }

                targets.add(target);

            } catch (InvalidFieldException e) {
                break;
            }
        }

        return targets.stream()
            .map(field -> new Move(selection, field))
            .map(MoveHint::new).collect(Collectors.toSet());

    }


}
