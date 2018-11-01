package de.techfak.gse.ysander.model.error;

/**
 * Specialized Exception for issues while parsing an FEN string.
 */
public class FENParseException extends ChessGameException {

    private static final int ERROR_CODE = 100;
    private static final String REASON = "FEN invalid";

    @Override
    public int getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public String getReason() {
        return REASON;
    }
}
