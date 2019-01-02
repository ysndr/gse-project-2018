package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.ResetHandler;

/**
 * Request a reset from the connected {@link ResetHandler}.
 */
public interface ResetInput {

    void setResetHandler(ResetHandler resetHandler);

}
