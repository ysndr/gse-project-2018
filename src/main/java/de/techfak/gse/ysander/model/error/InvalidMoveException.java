package de.techfak.gse.ysander.model.error;

public class InvalidMoveException extends ChessGameException {
    private static final int ERROR_CODE = 101;

    private static final String REASON = "Invalid move";


    public InvalidMoveException() {
        super(ERROR_CODE, REASON);
    }

    public InvalidMoveException(Throwable cause) {
        super(ERROR_CODE, REASON, cause);
    }

    protected InvalidMoveException(int code, String message) {
        super(code, message);
    }
}
