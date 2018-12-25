package de.techfak.gse.ysander.model.rules.providers;

import java.util.Set;

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
    Set<? extends Hint> getHints(State state);

    default Cache<Set<? extends Hint>> getHintsCached(State state) {
        return Cache.cache(() -> this.getHints(state));
    };

    default ChainedHintProvider chain(HintProvider other) {
        return new ChainedHintProvider(this).chain(other);
    }


}
