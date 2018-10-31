package de.techfak.gse.ysander.model.error;

public class ChessGameException extends RuntimeException {

    public int getErrorCode() {
        return 255;
    }

}
