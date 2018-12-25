package de.techfak.gse.ysander.model.figures;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.MoveHint;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.techfak.gse.ysander.model.figures.Figure.Color.BLACK;
import static de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider.Axis.HORIZONTAL;
import static de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider.Axis.VERTICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {


    State state;

    @BeforeEach
    void setUp() {
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Rook(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }

    @Test
    void testGetHintsColorIsNotRelevant() {

        assertEquals(14, new Rook(Figure.Color.WHITE).getHints(state).size());
        assertEquals(14, new Rook(BLACK).getHints(state).size());

    }

    @Test
    void testGetHintsClear() {
        assertEquals(new LinearMoveHintProvider(VERTICAL)
                         .chain(new LinearMoveHintProvider(HORIZONTAL))
                         .getHints(state),
                     new Rook(Figure.Color.WHITE).getHints(state));
    }


    @Test
    void testGetHintsBlocked() {
        state = state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(new Field(4, 3), new Rook(BLACK))
                         .createGrid())
            .createState();

        assertEquals(10, new Rook(BLACK).getHints(state).size());
        assertFalse(new Rook(BLACK).getHints(state).contains(
            new MoveHint(new Move(
                new Field(3, 3),
                new Field(4, 3)))));

        assertFalse(new Rook(BLACK).getHints(state).contains(
            new MoveHint(new Move(
                new Field(3, 3),
                new Field(5, 3)))));
    }


}
