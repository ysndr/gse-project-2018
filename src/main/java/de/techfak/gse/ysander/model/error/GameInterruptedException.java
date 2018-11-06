package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class GameInterruptedException extends ChessGameException {

    private static final String REASON = "Player interrupted the game";

    /**
     * Returns the exceptions error code.
     *
     * @return error code
     */
    @Override
    public int getErrorCode() {
        return 0;
    }

    public String getReason() {
        return REASON;
    }
}
