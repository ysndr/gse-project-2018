package de.techfak.gse.ysander.model.figures;

import java.util.HashSet;
import java.util.Set;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider.Axis.DIAGONAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BishopTest extends FigureTest {

    State state;

    @BeforeEach
    void setUp() {
        super.setUp();
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Bishop(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }

    @Test
    void testGetHintsColorIsNotRelevant() {

        assertEquals(13, new Bishop(Figure.Color.WHITE).getHints(state).size());
        assertEquals(13, new Bishop(Figure.Color.BLACK).getHints(state).size());

    }

    @Test
    void testGetHints() {

        assertEquals(new LinearMoveHintProvider(DIAGONAL).getHints(state),
                     new Bishop(Figure.Color.WHITE).getHints(state));

    }

    @Test
    void getHintsWithThreat() {

        state = state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(
                             new Field(5, 5),
                             new Pawn(Figure.Color.BLACK))
                         .createGrid())
            .createState();

        assertEquals(
            hintSetFrom(threatAt(new Field(5,5))),
            filter(ThreatHint.class, new Bishop(Figure.Color.WHITE).getHints(state)));

            Set<Hint> test = filter(MoveHint.class, new Bishop(Figure.Color.BLACK).getHints(state));

            assertEquals(10, filter(MoveHint.class, new Bishop(Figure.Color.BLACK).getHints(state)).size());
            assertEquals(11, new Bishop(Figure.Color.WHITE).getHints(state).size());
    }


}
