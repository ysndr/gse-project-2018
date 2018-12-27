package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;

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


}
