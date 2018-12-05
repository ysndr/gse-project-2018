package de.techfak.gse.ysander.model.error;

public class JavaPlatformException extends ChessGameException {
    public JavaPlatformException(String message, Exception reason) {
        super(254, message, reason);
    }
}
