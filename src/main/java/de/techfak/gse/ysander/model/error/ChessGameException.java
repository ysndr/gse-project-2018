package de.techfak.gse.ysander.model.error;

/**
 * Default project exception.
 */
public class ChessGameException extends RuntimeException {

    private static final int GENERAL_ERROR_CODE = 255;

    private final int ERROR_CODE;

    /**
     * Constructs a new ChessGameException exception with {@code 255} as its
     * error code and {@code "Internal Failure"} as its detail message.
     */
    public ChessGameException() {
        this("Internal failure");
    }

    /**
     * Constructs a new chess game exception with the specified detail message
     * and error code {@code 255}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ChessGameException(String message) {
        this(GENERAL_ERROR_CODE, message);
    }

    /**
     * Constructs a new chess game exception with the specified detail message
     * and error code.
     *
     * @param code    the error code of this exception. can be retrieved
     *                with a call to {@link #getErrorCode()}
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ChessGameException(int code, String message) {
        this(code, message, null);
    }

    /**
     * Constructs a new chess game exception with the specified error code detail message and
     * cause.
     *
     * @param code    the error code of this exception. can be retrieved
     *                with a call to {@link #getErrorCode()}
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public ChessGameException(int code, String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = code;
    }

    /**
     * Returns the exceptions error code.
     *
     * @return error code
     */
    public int getErrorCode() {
        return ERROR_CODE;
    }
}
