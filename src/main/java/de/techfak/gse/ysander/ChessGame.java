package de.techfak.gse.ysander;

import de.techfak.gse.ysander.model.State;

/**
 * The main Gameclass.
 * This will manage a sstate and the games control flow
 */
public class ChessGame {

    public static void main(final String... args) {
        System.out.println(State.defaultState().toFEN());
    }

}
