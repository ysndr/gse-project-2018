package de.techfak.gse.ysander;

import de.techfak.gse.ysander.communication.drivers.RawDriver;
import de.techfak.gse.ysander.communication.drivers.RawInputDriver;
import de.techfak.gse.ysander.controller.ChessController;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.view.CLI;

/**
 * The main Gameclass.
 * This will manage a sstate and the games control flow
 */
public class ChessGame {
    /**
     * The mai entry method.
     * @param args command line arguments
     */
    public static void main(final String... args) {

        State state = StateBuilder.defaultState();

        if (args.length > 0) {
            try {
                state = StateBuilder.fromFEN(args[0]);
            } catch (FENParseException e) {
                System.exit(e.getErrorCode());
            }
        }

        CLI view = new CLI();
        RawInputDriver rawDriver = new RawDriver();
        view.setRawInputHandler(rawDriver);

        ChessController controller = new ChessController(view, rawDriver, state);
        view.start();
    }

}
