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


class KnightTest extends FigureTest {


    private State state;

    @BeforeEach
    void setUp() {
        super.setUp();
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Knight(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }

    @Test
    void getHintsUnblocked() {
        Set<Hint> hints = Stream.of(
            new Field(1, 2),
            new Field(2, 1),
            new Field(5, 4),
            new Field(4, 5),
            new Field(1, 4),
            new Field(2, 5),
            new Field(5, 2),
            new Field(4, 1))
            .map(to -> new Move(new Field(3, 3), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        assertEquals(hints, new Knight(Figure.Color.BLACK).getHints(state));
    }

    @Test
    void getHintsBlocked() {

        Set<Hint> hints = hintSetFrom(
            moveAt(new Field(5, 4)),
            moveAt(new Field(4, 5)),
            moveAt(new Field(1, 4)),
            moveAt(new Field(2, 5)),
            moveAt(new Field(5, 2)),
            moveAt(new Field(4, 1)),
            threatAt(new Field(2, 1)));

        state = state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(new Field(2, 1), new Knight(Figure.Color.BLACK))
                         .setField(new Field(1, 2), new Knight(Figure.Color.WHITE))
                         .createGrid())
            .createState();

        assertEquals(hints, new Knight(Figure.Color.WHITE).getHints(state));

    }


}
