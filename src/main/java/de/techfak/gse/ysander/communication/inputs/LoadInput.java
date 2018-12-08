package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.LoadHandler;

/**
 * Connect to an{@link LoadHandler}.
 */
public interface LoadInput {
    void setLoadHandler(LoadHandler handler);
}
