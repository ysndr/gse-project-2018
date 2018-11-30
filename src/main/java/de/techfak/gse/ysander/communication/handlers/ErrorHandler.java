package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.error.ChessGameException;

public interface ErrorHandler {

    void handleError(ChessGameException e);

}
