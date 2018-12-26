package de.techfak.gse.ysander.model.figures;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.*;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    private State state;

    @BeforeEach
    void setUp() {
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
        state.builder()
            .setGrid(state.getGrid().builder()
                         .setField(new Field(4,4), new Queen(Figure.Color.BLACK))
                         .createGrid())
            .createState();


        assertEquals(27, new Queen(Figure.Color.BLACK).getHints(state).size());
    }

}
