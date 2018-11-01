package de.techfak.gse.ysander.model.error;

/**
 * Default project exception.
 */
public class ChessGameException extends RuntimeException {

    private static final int ERROR_CODE = 255;
    private static final String REASON = "Internal failure";

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
