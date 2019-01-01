package de.techfak.gse.ysander.model.rules.providers;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.rules.SelectableHint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectableHintProviderTest {


    @Test
    void testGetHintsNoFigures() {
        State state = StateBuilder.fromFEN("8/8/8/8/8/8/8/8 w");
        SelectableHintProvider provider = new SelectableHintProvider();

        assertEquals(0, provider.getHints(state).size());
    }

    @Test
    void testGetHintsDefault() {
        State state = StateBuilder.defaultState();
        SelectableHintProvider provider = new SelectableHintProvider();

        assertEquals(16, provider.getHints(state).size());
    }

    @Test
    void testGetHintsDifferentColor() {
        State state = StateBuilder.defaultState();
        state = StateBuilder.fromFEN("8/8/8/8/8/8/rrrrrrrr/kkkkkkkk w");
        SelectableHintProvider provider = new SelectableHintProvider();
        assertEquals(0, provider.getHints(state).size());
    }

    @Test
    void testGetHintsAlreadySelected() {
        State state = StateBuilder.defaultState();
        state = StateBuilder.of(state).setSelection(new Field(7, 7)).createState();
        SelectableHintProvider provider = new SelectableHintProvider();
        assertEquals(16, provider.getHints(state).size());
    }

}
