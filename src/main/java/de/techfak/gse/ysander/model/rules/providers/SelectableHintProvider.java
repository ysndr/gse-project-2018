package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.SelectableHint;
import de.techfak.gse.ysander.model.rules.SelectedHint;

/**
 * Computes Hints for selectable and selected fields.
 */
public class SelectableHintProvider implements HintProvider {

    private final Figure.Color egoColor;

    public SelectableHintProvider(final Figure.Color egoColor) {
        this.egoColor = egoColor;
    }

    public SelectableHintProvider() {
        this.egoColor = null;
    }


    @Override
    public Set<? extends Hint> getHints(final State state, final Field base) {
        Figure.Color egoColor = (this.egoColor == null) ? state.getColor() : this.egoColor;
        Set<Hint> hints = new HashSet<>();


        if (state.getSelection() != null) {
            hints.add(new SelectedHint(state.getSelection()));
        }

        hints.addAll(state.getGrid().getFigures().stream()
                         .filter(e -> e.getValue().color().equals(egoColor))                 // only current players
                         .filter(e -> !e.getKey().equals(state.getSelection()))              // that are not selected
                         .map(Map.Entry::getKey)                                             // convert to field
                         .map(SelectableHint::new)                                           // wrap in hint
                         .collect(Collectors.toSet()));                                      // collect in set
        return hints;
    }

}
