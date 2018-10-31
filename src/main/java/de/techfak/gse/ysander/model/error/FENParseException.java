package de.techfak.gse.ysander.model.error;

public class FENParseException extends ChessGameException {

    @Override
    public int getErrorCode() {
        return 100;
    }
}
