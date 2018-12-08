package de.techfak.gse.ysander.model.error;

/**
 * Wraps Exceptions that are out of context of the other specialized exceptions.
 */
public class JavaPlatformException extends ChessGameException {
    public JavaPlatformException(String message, Exception reason) {
        super(254, message, reason);
    }
}
