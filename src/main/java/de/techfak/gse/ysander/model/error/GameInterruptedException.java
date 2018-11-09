package de.techfak.gse.ysander.model.error;

/**
 * Specialized Exception for user induced interruptions by the user.
 */
public class GameInterruptedException extends ChessGameException {

    private static final int ERROR_CODE = 0;

    private static final String REASON = "Player interrupted the game";

    public GameInterruptedException() {
        super(ERROR_CODE, REASON);
    }

    /**
     * Delegate cause to parent {@link ChessGameException}.
     *
     * @param cause why the error was thrown
     */
    public GameInterruptedException(Throwable cause) {
        super(ERROR_CODE, REASON, cause);
    }

}
