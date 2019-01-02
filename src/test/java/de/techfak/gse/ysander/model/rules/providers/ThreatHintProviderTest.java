package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.figures.Figure.Color;
import de.techfak.gse.ysander.model.figures.Knight;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThreatHintProviderTest {

    private State state;

    @BeforeEach
    void setUp() {
        state = StateBuilder.defaultState().builder()
            .setGrid(GridBuilder.fromFEN("8/8/8/8/8/8/8/8")
                         .builder()
                         .setField(new Field(3,3), new Knight(Color.BLACK))
                         .setField(new Field(3, 4), new Knight(Color.WHITE))
                         .setField(new Field(4, 4), new Knight(Color.BLACK))
                         .setField(new Field(0, 3), new Knight(Color.BLACK))
                         .setField(new Field(0, 0), new Knight(Color.BLACK))
                         .createGrid())
            .setSelection(new Field(3, 3))
            .createState();
    }


    @Test
    void getHints() {
        assertEquals(new HashSet<>(), new ThreatHintProvider(0, 1, Color.WHITE).getHints(state));
        assertTrue(new ThreatHintProvider(0, 1, Color.BLACK).getHints(state)
                       .stream()
                       .findFirst()
                       .filter(hint -> hint.target().equals(new Field(3 ,4))).isPresent());

        assertEquals(new HashSet<>(), new ThreatHintProvider(0, 0, Color.BLACK).getHints(state));
        assertEquals(new HashSet<>(), new ThreatHintProvider(0, 0, Color.WHITE).getHints(state));


        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(4,4))))
                .collect(Collectors.toSet()),
            new ThreatHintProvider(1, 1, Color.WHITE).getHints(state));

        assertEquals(
            Stream.of(new ThreatHint(new Move(
                new Field(3,3),
                new Field(0,3))))
                .collect(Collectors.toSet()),
            new ThreatHintProvider(-1, 0, Color.WHITE).getHints(state));

        assertEquals(new HashSet<>(), new ThreatHintProvider(-1, -1, 2, Color.WHITE).getHints(state));

    }
}
