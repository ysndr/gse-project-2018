package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.*;
import de.techfak.gse.ysander.model.figures.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StateTest {

    @Test
    void toFEN() {
        State state = StateBuilder.defaultState();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w", state.toFEN());
    }

    @Test
    void fromFEN() {
        assertEquals(StateBuilder.defaultState(), StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w"));
    }

    @Test
    void detectInvalidFEN() {
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK BNR w"));
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/RNBQK BNR w"));

        // Wrong line sizes
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/9/8/PPPPPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/4PPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/7/8/PPPPPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/2PPPPP/RNBQKBNR w"));


        // Unknown color
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR j"));
        // no color
        assertThrows(FENParseException.class, () -> StateBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));

    }

    @Test
    void applyMove() {
        Move move = Move.fromString("a2-a3");
        Move invalid = Move.fromString("a4-a2");
        Move noop = Move.fromString("a2-a2");

        assertEquals("rnbqkbnr/pppppppp/8/8/8/P7/1PPPPPPP/RNBQKBNR b",
                     StateBuilder.defaultState().applyMove(move).toFEN());

        assertThrows(NotPlayersTurnException.class,
            () -> StateBuilder.defaultState().withColor(Figure.Color.BLACK).applyMove(move));

        assertThrows(InvalidMoveException.class,
            () -> StateBuilder.defaultState().applyMove(invalid));

        assertThrows(NoFigureMovedException.class,
            () -> StateBuilder.defaultState().applyMove(noop));


    }


}
