package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.MovesInput;
import de.techfak.gse.ysander.view.Output;
import de.techfak.gse.ysander.view.RawInput;
import de.techfak.gse.ysander.view.View;

import java.util.List;
import java.util.stream.Collector;

public class ChessController<V extends View<ChessGameException> & RawInput> {


    private State state;

    public ChessController(V view, State startState) {

        state = startState;

        view.setExceptionCB((e, o) -> {
            o.display(state);
            System.exit(e.getErrorCode());
        });

        view.setRawInputCB((i, o) -> {
            String[] moves = i.trim().split(";");
            for (String moveStr : moves) {
                Move move = Move.fromString(moveStr);
                state = state.applyMove(move);
            }
            o.display(state);
        });

        view.setOnInitCB((o) -> {
            o.display(state);
        });
    }

    public ChessController(V view) {
        this(view, State.defaultState());
    }




}
