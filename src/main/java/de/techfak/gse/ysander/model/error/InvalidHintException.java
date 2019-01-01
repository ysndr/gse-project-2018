package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.rules.Hint;

/**
 * An Exception indicating that an invalid hint was to be applied
 */
public class InvalidHintException extends ChessGameException {

    private static final String REASON = "Tried to apply a hint that was not in the states HintSet: (%s)";

    /**
     * Creates an exception related to an invalid hint.
     *
     * @param hint that was invalid
     */
    public InvalidHintException(Hint hint) {
        super(String.format(REASON, hint));
    }
}
