package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.rules.Hint;

/**
 * Accept hint as input. Basically that means this interface is called whenever
 * a hint should be applied.
 */
public interface HintInputHandler {
    void handleHintInput(Hint input);
}
