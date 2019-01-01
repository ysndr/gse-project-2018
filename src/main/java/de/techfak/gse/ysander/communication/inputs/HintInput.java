package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.HintInputHandler;


/**
 * An input device that calls a {@link HintInputHandler} with the given hint.
 */
public interface HintInput {
    void setHintInputHandler(HintInputHandler hintInputHandler);
}
