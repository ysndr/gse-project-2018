package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.RawInputHandler;

/**
 * An input device that calls a {@link RawInputHandler} with the raw string
 * input.
 */
public interface RawInput {
    void setRawInputHandler(RawInputHandler rawInputHandler);
}
