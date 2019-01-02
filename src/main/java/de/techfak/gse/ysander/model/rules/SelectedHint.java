package de.techfak.gse.ysander.model.rules;

import java.util.Objects;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;

/**
 * Represents Fields that are selected (usually one).
 */
public class SelectedHint implements Hint {

    private final Field selected;

    public SelectedHint(final Field selectable) {
        this.selected = selectable;
    }

    @Override
    public Field target() {
        return selected;
    }

    @Override
    public State apply(final State state) {
        return StateBuilder.of(state)
            .resetSelection()
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
        final SelectedHint that = (SelectedHint) o;
        return Objects.equals(selected, that.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selected);
    }

    @Override
    public String toString() {
        return "SelectedHint{" +
               "selected=" + selected +
               '}';
    }
}
