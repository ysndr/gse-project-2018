package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;

public interface Driver {

    void setErrorHandler(ErrorHandler errorHandler);

}
