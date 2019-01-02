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

/**
 * Hint Providers represent a way to calculate hints based on a given state and
 * an optional field of origin.
 * It also provides a way to cache the result of that calculation and chain
 * multiple Providers together (see: {@link ChainedHintProvider})
 */
public interface HintProvider {

    /**
     * Filter Set of hints by given type.
     *
     * @param hints the hint set
     * @param type  a subclass type of hint
     * @return filtered Set
     */
    static Set<? extends Hint> filter(Set<? extends Hint> hints, Class<? extends Hint> type) {
        return filter(hints, Collections.singletonList(type));
    }

    /**
     * Filter Set of hints by given type.
     *
     * @param hints the hint set
     * @param types list of subclass types of hint
     * @return filtered Set
     */
    static Set<? extends Hint> filter(Set<? extends Hint> hints, List<Class<? extends Hint>> types) {
        Set<Class<? extends Hint>> typeSet = new HashSet<>(types);

        return hints.stream().filter(h -> typeSet.contains(((Hint) h).getClass())).collect(Collectors.toSet());
    }

    default Cache<Set<? extends Hint>> getHintsCached(State state, final Field base) {
        return Cache.cache(() -> this.getHints(state, base));
    }

    Set<? extends Hint> getHints(State state, Field base);

    default Cache<Set<? extends Hint>> getHintsCached(State state) {
        return Cache.cache(() -> this.getHints(state));
    }

    /**
     * Computes rules from a given state.
     * The kind of rules returned is dependent on the Implementor and the state.
     *
     * @param state the state to create rules for
     * @return a set of rules
     */
    default Set<? extends Hint> getHints(State state) {
        if (state == null) {
            return new HashSet<>();
        }
        return getHints(state, state.getSelection());
    }

    default ChainedHintProvider chain(HintProvider other) {
        return new ChainedHintProvider(this).chain(other);
    }


}
