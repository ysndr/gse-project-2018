package de.techfak.gse.ysander.view;

import java.util.function.BiConsumer;

import de.techfak.gse.ysander.model.State;

/**
 * An input device that calls a callback with the raw string input and an
 * {@link Output} device to display {@link State}.
 */
public interface RawInput {
    void setRawInputCB(BiConsumer<String, Output<State>> rawInputCB);
}
