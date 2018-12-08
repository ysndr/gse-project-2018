package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;
import de.techfak.gse.ysander.model.error.ChessGameException;

/**
 * On error do some informational error comprehension.
 */
public final class GlobalErrorEmergencyExit implements ErrorHandler {
    private static GlobalErrorEmergencyExit ourInstance = new GlobalErrorEmergencyExit();

    private GlobalErrorEmergencyExit() {
    }

    public static GlobalErrorEmergencyExit getInstance() {
        return ourInstance;
    }

    @Override
    public void handleError(final ChessGameException e) {

        System.err.println(e.getMessage());
        System.exit(e.getErrorCode());
    }

}
