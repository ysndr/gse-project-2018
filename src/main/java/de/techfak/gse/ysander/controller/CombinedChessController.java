package de.techfak.gse.ysander.controller;

import de.techfak.gse.ysander.communication.handlers.MoveHandler;
import de.techfak.gse.ysander.communication.handlers.PlayerInputHandler;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.view.View;

/**
 * Main controller managing the the control flow between
 * input -> model -> output.
 */
public class CombinedChessController extends BaseChessController implements PlayerInputHandler, MoveHandler , Output<State> {

    private final MultiplayerChessController multiplayer;
    private final RawChessController raw;

    public CombinedChessController(final View view,
                                   final MultiplayerChessController multiplayer,
                                   final RawChessController raw) {
        super(view, null);

        if(!raw.state.equals(multiplayer.state)) {
            throw new IllegalArgumentException("The state on both subcontrollers must be the same");
        }

        this.raw = raw;
        this.multiplayer = multiplayer;

    }

    @Override
    public void handlePlayerInput(final Field input, final Player player) {
        multiplayer.handlePlayerInput(input, player);
    }

    @Override
    public void handleMove(Move move) {
        this.raw.handleMove(move);
    }


    @Override
    public void display(final State state) {
        multiplayer.setState(state);
        raw.setState(state);

        super.output.display(state);
    }
}
