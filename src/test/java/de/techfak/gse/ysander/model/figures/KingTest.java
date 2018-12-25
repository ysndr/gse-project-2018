package de.techfak.gse.ysander.model.figures;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.techfak.gse.ysander.model.rules.providers.LinearMoveHintProvider.Axis.DIAGONAL;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {


    State state;

    @BeforeEach
    void setUp() {
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new King(Figure.Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }

    @Test
    void testGetHintsNoBounds() {

        Set<Hint> hints = new HashSet<Field>(Arrays.asList(
            new Field(2, 2),
            new Field(2, 3),
            new Field(2, 4),
            new Field(3, 2),
            new Field(3, 4),
            new Field(4, 2),
            new Field(4, 3),
            new Field(4, 4)
        )).stream()
            .map(to -> new Move(new Field(3, 3), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        assertEquals(
            hints,
            state.getSelectedFigure().map(f -> f.getHints(state)).orElse(new HashSet<>()));

    }


    @Test
    void testGetHintsGridBounds() {

        state = state.builder()
            .setGrid(state
                         .getGrid()
                         .builder()
                         .setField(new Field(0, 0), new King(Figure.Color.WHITE)).createGrid())
            .setSelection(new Field(0, 0)).createState();

        Set<Hint> hints = new HashSet<Field>(Arrays.asList(
            new Field(0, 1),
            new Field(1, 1),
            new Field(1, 0)
        )).stream()
            .map(to -> new Move(new Field(0, 0), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        assertEquals(
            hints,
            state.getSelectedFigure().map(f -> f.getHints(state)).orElse(new HashSet<>()));

    }


    @Test
    void getHintsBlocked() {
        state = state.builder()
            .setGrid(state
                         .getGrid()
                         .builder()
                         .setField(new Field(3, 4), new King(Figure.Color.WHITE)).createGrid())
            .createState();

        Set<Hint> hints = new HashSet<Field>(Arrays.asList(
            new Field(2, 2),
            new Field(2, 3),
            new Field(2, 4),
            new Field(3, 2),
            new Field(4, 2),
            new Field(4, 3),
            new Field(4, 4)
        )).stream()
            .map(to -> new Move(new Field(3, 3), to))
            .map(MoveHint::new)
            .collect(Collectors.toSet());

        assertEquals(
            hints,
            state.getSelectedFigure().map(f -> f.getHints(state)).orElse(new HashSet<>()));

    }
}
