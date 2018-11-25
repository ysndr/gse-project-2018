package de.techfak.gse.ysander.view;

import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;

/**
 * A view_v1 Abstraction that can be {@link #start()}ed and will accept some input
 * by also implementing some Input interface. It will also notify listeners via
 * callbacks about its initialization.
 */
public interface View extends Output<State> {

    /**
     * Start the view whatever that means for the implementation.
     * - Should call the {@code onInitCB} to notify listeners about its creation
     */
    void start();

    void setOnInitCB(Runnable onsonInitCB);
}
