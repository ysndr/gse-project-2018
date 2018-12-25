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

class BishopTest {

    State state;

    @BeforeEach
    void setUp() {
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

}
