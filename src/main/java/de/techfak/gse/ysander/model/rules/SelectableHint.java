package de.techfak.gse.ysander.model.rules;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;

public class SelectableHint implements Hint {

    private final Field selectable;

    public SelectableHint(final Field selectable) {
        this.selectable = selectable;
    }

    @Override
    public Field target() {
        return selectable;
    }

    @Override
    public State apply(final State state) {
        return StateBuilder.of(state)
            .setSelection(selectable)
            .createState();
    }
}
