package de.techfak.gse.ysander.model.rules.providers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.util.Cache;

public interface HintProvider {
    /**
     * Computes rules from a given state.
     * The kind of rules returned is dependent on the Implementor and the state.
     * @param state the state to create rules for
     * @return a set of rules
     */
    default Set<? extends Hint> getHints(State state) {
        if (state == null) {
            return new HashSet<>();
        }
        return getHints(state, state.getSelection());
    }

    Set<? extends Hint> getHints(State state, Field base);

    default Cache<Set<? extends Hint>> getHintsCached(State state, final Field base) {
        return Cache.cache(() -> this.getHints(state, base));
    };

    default Cache<Set<? extends Hint>> getHintsCached(State state) {
        return Cache.cache(() -> this.getHints(state));
    };

    default ChainedHintProvider chain(HintProvider other) {
        return new ChainedHintProvider(this).chain(other);
    }

    static Set<? extends Hint> filter(Set<? extends Hint> hints, Class<? extends Hint> type) {
        return filter(hints, Collections.singletonList(type));
    }

    static Set<? extends Hint> filter(Set<? extends Hint> hints, List<Class<? extends Hint>> types) {
        Set<Class<? extends Hint>> typeSet = new HashSet<>(types);

        return hints.stream().filter(h -> typeSet.contains(((Hint) h).getClass())).collect(Collectors.toSet());
    }


}
