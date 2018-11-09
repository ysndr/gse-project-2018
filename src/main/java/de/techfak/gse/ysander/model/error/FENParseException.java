package de.techfak.gse.ysander.model.error;

/**
 * Specialized Exception for issues while parsing an FEN string.
 */
public class FENParseException extends ChessGameException {

    private static final int ERROR_CODE = 100;
    private static final String REASON = "FEN invalid";

    public FENParseException() {
        super(ERROR_CODE, REASON);
    }

    public FENParseException(Throwable cause) {
        super(ERROR_CODE, REASON, cause);
    }
}
