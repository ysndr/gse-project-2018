package de.techfak.gse.ysander.model.rules;

import java.util.Objects;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;

/**
 * A {@code Hint} is a tool to change the current {@link State}.
 *
 * - Hints are weakly bound to a specific target {@link Field} which, when selected,
 *   should initiate the application of the {@code Hint} onto the state.
 *
 * - Hints are specific to a state and computed independently for each state.
 *
 * - The task of coordinating the application if hints is left to Controllers
 *   on higher levels.
 */
public interface Hint {
    /**
     * The field this Hint is bound to. It should only be applies when this
     * field is chosen
     * @return {@link Field}
     */
    Field target();
    State apply(State state);
}
