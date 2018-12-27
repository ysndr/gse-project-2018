package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.figures.Figure.Color;
import de.techfak.gse.ysander.model.figures.Knight;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import de.techfak.gse.ysander.model.rules.providers.LinearThreatHintProvider.Axis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearThreatHintProviderTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3, 3), new Knight(Color.BLACK))
                         .setField(new Field(3, 4), new Knight(Color.WHITE))
                         .setField(new Field(4, 4), new Knight(Color.BLACK))
                         .setField(new Field(5, 5), new Knight(Color.BLACK))
                         .setField(new Field(4, 5), new Knight(Color.BLACK))
                         .setField(new Field(4, 3), new Knight(Color.WHITE))
                         .setField(new Field(0, 3), new Knight(Color.BLACK))
                         .setField(new Field(0, 0), new Knight(Color.WHITE))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }


    @Test
    void getHints() {

        assertEquals(new HashSet<>(), new LinearThreatHintProvider(Axis.VERTICAL, Color.WHITE).getHints(state));

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(4,4))))
                .collect(Collectors.toSet()),
            new LinearThreatHintProvider(Axis.DIAGONAL, Color.WHITE).getHints(state));

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(0,0))))
                .collect(Collectors.toSet()),
            new LinearThreatHintProvider(Axis.DIAGONAL, Color.BLACK).getHints(state));

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(0,3))))
                .collect(Collectors.toSet()),
            new LinearThreatHintProvider(Axis.HORIZONTAL, Color.WHITE).getHints(state));
        }

    @Test
    void getHintsBothDirections() {
        state = state.builder().setSelection(new Field(4,4)).createState();

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(4,4),
                new Field(4,5))))
                .collect(Collectors.toSet()),
            new LinearThreatHintProvider(Axis.VERTICAL, Color.WHITE).getHints(state));

    }

    @Test
    void getHintsOutOfReach() {
        state = state.builder().setGrid(state.getGrid().builder()
                                            .setField(new Field(0, 0), new Knight(Color.BLACK))
                                            .createGrid())
            .createState();

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(4,4))))
                .collect(Collectors.toSet()),
            new LinearThreatHintProvider(Axis.DIAGONAL, 2, Color.WHITE).getHints(state));

    }
}
