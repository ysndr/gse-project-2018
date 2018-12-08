package de.techfak.gse.ysander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import de.techfak.gse.ysander.controller.GlobalErrorEmergencyExit;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.view.cli.CLIApplication;
import de.techfak.gse.ysander.view.fx.FXApplication;


public class ChessGame {

    public static void main(String[] args) {

        State state = StateBuilder.defaultState();
        boolean withGUI = args[0].equals("--gui");

        String fen = null;

        if (!withGUI) {
            fen = args[0];
        } else if (args.length > 1) {
            fen = args[1];
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
