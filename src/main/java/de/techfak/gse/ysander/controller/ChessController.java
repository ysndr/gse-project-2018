package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.MovesInput;
import de.techfak.gse.ysander.view.Output;

import java.util.List;
import java.util.stream.Collector;

public class ChessController {

    private MovesInput movesInput;
    private Output<State> view;

    public ChessController updateView(State state) {
        view.display(state);
        return this;
    }

    public void turn(State state) {

        List<Move> moves = movesInput.requestMoves();

        for (Move move : moves) {
            state = state.applyMove(move);
        }

    }


}
