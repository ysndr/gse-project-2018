package de.techfak.gse.ysander.model.rules.providers;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearMoveHintProviderTest {

    @Test
    void getHintsFree() {
        State state = StateBuilder.fromFEN("8/8/8/8/8/8/8/8 w");
        LinearMoveHintProvider provider = new LinearMoveHintProvider(LinearMoveHintProvider.Axis.VERTICAL);
        testAxis(state, provider);

        provider = new LinearMoveHintProvider(LinearMoveHintProvider.Axis.HORIZONTAL);
        testAxis(state, provider);
    }

    private void testAxis(State state, final LinearMoveHintProvider provider) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                state = StateBuilder.of(state).setSelection(new Field(i, j)).createState();
                assertEquals(7, provider.getHints(state).size());
            }
        }
    }
}
