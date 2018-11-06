package de.techfak.gse.ysander;

import de.techfak.gse.ysander.controller.ChessController;
import de.techfak.gse.ysander.model.Grid;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.view.Cli;

/**
 * The main Gameclass.
 * This will manage a sstate and the games control flow
 */
public class ChessGame {

    public static void main(final String... args) {

        State state = State.defaultState();

        if (args.length > 0) {
            try {
                state = state.withGrid(Grid.fromFEN(args[0]));
            } catch (FENParseException e) {
                System.err.println(e.getReason());
                System.exit(e.getErrorCode());
            }
        }

        Cli view = new Cli();
        ChessController<Cli> controller = new ChessController(view, state);

        view.start();
    }

}
