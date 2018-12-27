package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;

public class ChainedHintProvider implements HintProvider {

    private final HintProvider wrapped;
    private ChainedHintProvider tail;

    public ChainedHintProvider(final HintProvider wrapped) {
        this.wrapped = wrapped;
    }


    /**
     * Chain more {@link HintProvider}s.
     * Creates a simple singly linked list of {@link ChainedHintProvider}s by
     * managing a tail of another {@link ChainedHintProvider} which might contain a
     * tail itself or not recursing further.
     * @param other the other {@link HintProvider} to chain up
     * @return new {@link ChainedHintProvider} which has the current one as its tail.
     */
    @Override
    public ChainedHintProvider chain(final HintProvider other) {
        ChainedHintProvider extended = new ChainedHintProvider(other);
        extended.tail = this;
        return extended;
    }


    /**
     * Creates a set of rules by unifying its wrapped rulesets rules with its
     * tails wrapped rulesets rules...
     * @param state the state to create rules for
     * @param base
     * @return A combined set of rules contained in the chain
     */
    @Override
    public Set<? extends Hint> getHints(final State state, final Field base) {
        Set<Hint> hints = new HashSet<>(wrapped.getHints(state, base));
        if (tail != null) {
            hints.addAll(tail.getHints(state, base));
        }
        return hints;
    }
}
