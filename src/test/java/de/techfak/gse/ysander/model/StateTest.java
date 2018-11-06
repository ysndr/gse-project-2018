package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.*;
import de.techfak.gse.ysander.model.figures.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StateTest {

    @Test
    void toFEN() {
        State state = State.defaultState();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w", state.toFEN());
    }

    @Test
    void fromFEN() {
        assertEquals(State.defaultState(), State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w"));
    }

    @Test
    void detectInvalidFEN() {
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK BNR w"));
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/RNBQK BNR w"));

        // Wrong line sizes
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/9/8/PPPPPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/4PPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/7/8/PPPPPPPP/RNBQKBNR w"));
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/2PPPPP/RNBQKBNR w"));


        // Unknown color
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR j"));
        // no color
        assertThrows(FENParseException.class, () -> State.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));

    }

    @Test
    void applyMove() {
        Move move = Move.fromString("a2-a3");
        Move invalid = Move.fromString("a4-a2");
        Move noop = Move.fromString("a2-a2");

        assertEquals("rnbqkbnr/pppppppp/8/8/8/P7/1PPPPPPP/RNBQKBNR b",
            State.defaultState().applyMove(move).toFEN());

        assertThrows(NotPlayersTurnException.class,
            () -> State.defaultState().withColor(Figure.Color.BLACK).applyMove(move));

        assertThrows(InvalidMoveException.class,
            () -> State.defaultState().applyMove(invalid));

        assertThrows(NoFigureMovedException.class,
            () -> State.defaultState().applyMove(noop));


    }


}
