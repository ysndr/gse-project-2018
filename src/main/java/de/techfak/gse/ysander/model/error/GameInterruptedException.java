package de.techfak.gse.ysander.model.error;

import de.techfak.gse.ysander.model.Field;

public class GameInterruptedException extends ChessGameException {

    private static final int ERROR_CODE = 0;
    private static final String REASON = "Player interrupted the game";

    public GameInterruptedException() {
        super(ERROR_CODE, REASON);
    }

    public GameInterruptedException(Throwable cause) {
        super(ERROR_CODE, REASON, cause);
    }

}
