package de.techfak.gse.ysander.model.rules;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectableHintTest {

    private static SelectableHint hint;

    @BeforeAll
    static void setUp() {
        SelectableHintTest.hint = new SelectableHint(new Field("a3"));
    }

    @Test
    void target() {
        assertEquals(new Field("a3"), hint.target());
    }

    @Test
    void apply() {
        State state = StateBuilder
            .of(StateBuilder.defaultState())
            .setSelection(hint.target())
            .createState();
        assertEquals(state, hint.apply(state));
    }
}
