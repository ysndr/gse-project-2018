package de.techfak.gse.ysander.view.cli;

import de.techfak.gse.ysander.communication.drivers.RawDriver;
import de.techfak.gse.ysander.communication.drivers.RawInputDriver;
import de.techfak.gse.ysander.controller.RawChessController;
import de.techfak.gse.ysander.model.State;

/**
 * CLIApplication wrapper.
 */
public class CLIApplication {
    /**
     * Initializes Drivers and Controller with a given state.
     *
     * @param state initialState
     */
    public static void startCLI(final State state) {
        CLI view = new CLI();
        RawInputDriver rawDriver = new RawDriver();
        view.setRawInputHandler(rawDriver);

        RawChessController controller = new RawChessController(view, rawDriver, state);
        view.start();
    }

}
