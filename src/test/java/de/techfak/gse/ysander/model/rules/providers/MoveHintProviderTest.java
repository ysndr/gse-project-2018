package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.rules.Hint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveHintProviderTest {

    @Test
    void getHintsNoSelection() {
        State state = StateBuilder.fromFEN("8/8/8/8/8/8/8/8 w");
        MoveHintProvider provider = new MoveHintProvider(1, 1, 5);
        assertEquals(0, provider.getHints(state).size());
    }

    @Test
    void getHintsSelectionEmpty() {


        State state = StateBuilder.fromFEN("8/8/8/8/8/8/8/8 w");
        state = StateBuilder.of(state)
            .setSelection(new Field(0, 0))
            .createState();

        // single steps
        MoveHintProvider provider = new MoveHintProvider(0, 1);
        assertEquals(7, provider.getHints(state).size());

        // double steps
        provider = new MoveHintProvider(0, 2);
        assertEquals(3, provider.getHints(state).size());


        // combined steps
        provider = new MoveHintProvider(1, 2);
        Set<Field> expect = new HashSet<>();
        expect.add(new Field(1,2));
        expect.add(new Field(2,4));
        expect.add(new Field(3, 6));

        assertEquals(3, provider.getHints(state).size());
        assertEquals(expect, provider.getHints(state)
            .stream()
            .map(m -> ((Hint) m).target()).collect(Collectors.toSet()));


        // limit
        provider = new MoveHintProvider(0, 1, 1);
        assertEquals(1, provider.getHints(state).size());

        // huge delta
        // double steps
        provider = new MoveHintProvider(0, 8);
        assertEquals(0, provider.getHints(state).size());

        // wall y
        provider = new MoveHintProvider(0, -1);
        assertEquals(0, provider.getHints(state).size());

        // wall x
        provider = new MoveHintProvider(-1, 0);
        assertEquals(0, provider.getHints(state).size());


    }


    @Test
    void getHintsSelectionWitCollision() {


        State state = StateBuilder.fromFEN("3r4/8/8/8/8/8/8/8 w");
        state = StateBuilder.of(state)
            .setSelection(new Field(0, 0))
            .createState();


        // limit
        HintProvider provider = new MoveHintProvider(1, 0);
        assertEquals(2, provider.getHints(state).size());


        // if on field below is not empty
        state = StateBuilder.of(state)
            .setSelection(new Field(2, 0))
            .createState();

        provider = new MoveHintProvider(1, 0);
        assertEquals(0, provider.getHints(state).size());


    }

}
