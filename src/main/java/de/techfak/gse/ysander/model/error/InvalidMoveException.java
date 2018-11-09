package de.techfak.gse.ysander.model.error;

/**
 * An Exception indicating that an invalid move was referenced.
 */
public class InvalidMoveException extends ChessGameException {
    private static final int ERROR_CODE = 101;

    private static final String REASON = "Invalid move";

    /**
     * Creates an exception indicating that the attempted move was invalid.
     */
    public InvalidMoveException() {
        super(ERROR_CODE, REASON);
    }

    /**
     * Delegate cause to parent {@link ChessGameException}.
     *
     * @param cause why the error was thrown
     */
    public InvalidMoveException(Throwable cause) {
        super(ERROR_CODE, REASON, cause);
    }

    /**
     * Creates an exception indicating that the attempted move was invalid
     * providing extra capability of overriding the exact error code and
     * message.
     *
     * @param code of the child exception
     * @param message of the child exception
     */
    protected InvalidMoveException(int code, String message) {
        super(code, message);
    }
}
