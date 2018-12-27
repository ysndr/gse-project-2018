package de.techfak.gse.ysander.model.figures;

import java.util.Set;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.GridBuilder;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest extends FigureTest {

    State state;

    @BeforeEach
    void setUp() {
        super.setUp();
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Pawn(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }


    @Test
    void testGetHintsMove() {
        // Black pawn
        Set<Hint> hints = hintSetFrom(moveAt(new Field(3, 4)));
        assertEquals(hints, new Pawn(Figure.Color.BLACK).moved().getHints(state));

        // Black pawn unmoved
        hints = hintSetFrom(moveAt(new Field(3, 5)), moveAt(new Field(3, 4)));
        assertEquals(hints, new Pawn(Figure.Color.BLACK).getHints(state));


        //white pawn
        hints = hintSetFrom(moveAt(new Field(3, 2)));
        assertEquals(hints, new Pawn(Figure.Color.WHITE).moved().getHints(state));

        //white unmoved
        hints = hintSetFrom(moveAt(new Field(3, 1)), moveAt(new Field(3, 2)));
        assertEquals(hints, new Pawn(Figure.Color.WHITE).getHints(state));


    }


    @Test
    void getHintsThreat() {

        state = state.builder().setGrid(
            state.getGrid().builder()
                .setField(new Field(2, 2), new King(Figure.Color.BLACK))
                .setField(new Field(4, 2), new King(Figure.Color.BLACK))
                .setField(new Field(2, 4), new King(Figure.Color.WHITE))
                .setField(new Field(4, 4), new King(Figure.Color.WHITE))
                .createGrid())
            .setSelection(new Field(3, 3))
            .createState();

        // white pawn
        Set<Hint> hints = hintSetFrom(threatAt(new Field(2, 2)), threatAt(new Field(4,2)));
        assertEquals(hints, filter(ThreatHint.class, new Pawn(Figure.Color.WHITE).getHints(state)));

        // black pawn
        state = state.builder().setColor(Figure.Color.BLACK).createState();
        hints = hintSetFrom(threatAt(new Field(2, 4)), threatAt(new Field(4,4)));
        assertEquals(hints, filter(ThreatHint.class, new Pawn(Figure.Color.BLACK).getHints(state)));


    }


}
