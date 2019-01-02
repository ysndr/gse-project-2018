package de.techfak.gse.ysander;

import de.techfak.gse.ysander.controller.GlobalErrorEmergencyExit;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.view.cli.CLIApplication;
import de.techfak.gse.ysander.view.fx.FXApplication;

/**
 * Main Chess Game Class.
 */
public class ChessGame {

    /**
     * Game launch method.
     * Parses arguments and runs the the kind of Application depending on
     * {@code --gui} flag
     *
     * @param args programm args
     */
    public static void main(String[] args) {

        State state = StateBuilder.defaultState();
        boolean withGUI = false;

        String fen = null;


        if (args.length > 0) {
            withGUI = args[0].equals("--gui");
            if (!withGUI) {
                fen = args[0];
            } else if (args.length > 1) {
                fen = args[1];
            }
        }

        if (fen != null) {
            try {
                state = StateBuilder.fromFEN(fen);
            } catch (FENParseException e) {
                GlobalErrorEmergencyExit.getInstance().handleError(e);
            }
        }


        if (withGUI) {
            FXApplication.startGUI(state);
        } else {
            CLIApplication.startCLI(state);
        }

    }


}
