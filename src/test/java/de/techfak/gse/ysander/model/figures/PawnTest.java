package de.techfak.gse.ysander.model.figures;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {

    State state;

    @BeforeEach
    void setUp() {
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Pawn(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }


    @Test
    void testGetHintsInitial() {
        Set<Hint> hints = Stream.of(new Field(3, 2), new Field(3, 1))
            .map(to -> new Move(new Field(3, 3), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        assertEquals(hints, new Pawn(Figure.Color.BLACK).getHints(state));

    }

    @Test
    void testGetHintsAfterMoved() {
        state.builder().setGrid(
            state.getGrid()
                .builder()
                .setField(new Field(3, 3), new Pawn(Figure.Color.BLACK).moved())
                .createGrid())
            .createState();

        Set<Hint> hints = Stream.of(new Field(3, 2))
            .map(to -> new Move(new Field(3, 3), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        Figure x = new Pawn(Figure.Color.BLACK).moved();

        assertEquals(hints, new Pawn(Figure.Color.BLACK).moved().getHints(state));

    }

}
