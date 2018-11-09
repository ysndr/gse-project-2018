package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.model.error.GameInterruptedException;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import de.techfak.gse.ysander.view.RawInput;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 * @param <V>
 */
public class ChessController<V extends View<ChessGameException> & RawInput> {


    private State state;

    public ChessController(V view) {
        this(view, State.defaultState());
    }

    /**
     * Contructs a chess controller with a given view and a start state.
     *
     * @param view the view to attach to
     * @param startState to begin with
     */
    public ChessController(V view, State startState) {

        state = startState;

        view.setExceptionCB((e, o) -> {
            if (e instanceof InvalidMoveException) {
                o.display(state);
            }
            System.exit(e.getErrorCode());
        });

        view.setRawInputCB((i, o) -> {
            if (i.trim().length() == 0) {
                throw new GameInterruptedException();
            }
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


}
