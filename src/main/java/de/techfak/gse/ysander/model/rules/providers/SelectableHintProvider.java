package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.SelectableHint;

public class SelectableHintProvider implements HintProvider {

    @Override
    public Set<? extends Hint> getHints(final State state) {
        if (state.getSelection() != null) {
            return new HashSet<>();
        }

        return state.getGrid().getFigures().stream()
            .filter(e -> e.getValue().color().equals(state.getColor()))         // only current players
            .map(Map.Entry::getKey)                                             // convert to field
            .map(SelectableHint::new)                                           // wrap in hint
            .collect(Collectors.toSet());                                       // collect in set
        }

}
