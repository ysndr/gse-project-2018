package de.techfak.gse.ysander.model.error;

public class InvalidMoveException extends ChessGameException {
    private static final int ERROR_CODE = 101;
    private static final String REASON = "Invalid move";

    /**
     * Returns the exceptions error code.
     *
     * @return error code
     */
    public int getErrorCode() {
        return ERROR_CODE;
    }

    public String getReason() {
        return REASON;
    }
}
