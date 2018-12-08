package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.RawInputHandler;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;

/**
 * An input device that calls a callback with the raw string input and an
 * {@link Output} device to display {@link State}.
 */
public interface RawInput {
    void setRawInputHandler(RawInputHandler rawInputHandler);
}
