package de.techfak.gse.ysander.model.rules;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.figures.Figure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveHintTest {

    private static MoveHint hint;

    @BeforeAll
    static void setUp() {
       MoveHintTest.hint = new MoveHint(new Move(
            new Field("a2"),
            new Field("a3")
        ));
    }

    @Test
    void getMove() {
        assertEquals(hint.getMove(), new Move(
            new Field("a2"),
            new Field("a3")
        ));
    }

    @Test
    void target() {
        assertEquals(hint.target(), new Field("a3"));
    }

    @Test
    void apply() {
        State state = StateBuilder.defaultState();
        assertEquals(
            this.hint.apply(state),
            state.builder()
                .setGrid(
                    state.getGrid()
                        .applyMove(hint.getMove(), Figure.Color.WHITE))
                .setColor(Figure.Color.BLACK)
                .createState());
    }
}
