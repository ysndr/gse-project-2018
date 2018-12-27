package de.techfak.gse.ysander.model.figures;

import java.util.Set;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest extends FigureTest {

    private State state;

    @BeforeEach
    void setUp() {
        super.setUp();
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Queen(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }

    @Test
    void getHints() {
        assertEquals(27, new Queen(Figure.Color.BLACK).getHints(state).size());
    }


    @Test
    void getHintsBlocked() {
        state = state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(new Field(4,4), new Queen(Figure.Color.BLACK))
                         .createGrid())
            .createState();


        assertEquals(24, new Queen(Figure.Color.WHITE).getHints(state).size());
    }

    @Test
    void getHintsWithThreats() {
        state = state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(new Field(3,4), new Queen(Figure.Color.BLACK))
                         .setField(new Field(4,4), new Queen(Figure.Color.BLACK))
                         .setField(new Field(4, 3), new Queen(Figure.Color.BLACK))
                         .createGrid())
            .createState();



        Set<Hint> hints = hintSetFrom(
            threatAt(new Field(4, 4)),
            threatAt(new Field(4, 3)),
            threatAt(new Field(3, 4))
        );

        assertEquals(15, filter(MoveHint.class, new Queen(Figure.Color.WHITE).getHints(state)).size());
        assertEquals(hints, filter(ThreatHint.class, new Queen(Figure.Color.WHITE).getHints(state)));

    }

}
