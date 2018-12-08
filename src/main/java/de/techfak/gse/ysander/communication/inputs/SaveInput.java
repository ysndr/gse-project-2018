package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.SaveHandler;

/**
 * {@link java.net.ConnectException to a {@link SaveHandler}.
 */
public interface SaveInput {
    void setSaveHandler(SaveHandler handler);
}
