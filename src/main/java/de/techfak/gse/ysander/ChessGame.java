package de.techfak.gse.ysander;

import de.techfak.gse.ysander.model.Grid;
import de.techfak.gse.ysander.model.State;

public class ChessGame {

    public static void main(final String... args) {
        System.out.println(State.defaultState().toFEN());
    }

}
