package de.techfak.gse.ysander.model.rules;

import java.util.Objects;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;

/**
 * A hint that represents fields that can be selected by the current player.
 */
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SelectableHint that = (SelectableHint) o;
        return Objects.equals(selectable, that.selectable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectable);
    }

    @Override
    public String toString() {
        return "SelectableHint{" +
               "selectable=" + selectable +
               '}';
    }
}
