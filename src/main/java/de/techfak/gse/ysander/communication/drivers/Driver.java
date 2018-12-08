package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.ErrorHandler;

/**
 * Drivers basically convert some sort of _raw_/_untagged_ input into more
 * meaningful data that is then used as input.
 */
public interface Driver {

    void setErrorHandler(ErrorHandler errorHandler);

}
