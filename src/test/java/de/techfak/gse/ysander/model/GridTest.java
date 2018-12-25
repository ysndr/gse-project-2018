package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.NoFigureOnFieldException;
import de.techfak.gse.ysander.model.error.NotPlayersTurnException;
import de.techfak.gse.ysander.model.figures.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {

    @Test
    void toFEN() {
        Grid grid = GridBuilder.defaultGrid();

        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", grid.toFEN());
    }

    @Test
    void fromFEN() {
        assertEquals(GridBuilder.defaultGrid(), GridBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
                     GridBuilder.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR").toFEN());
    }


    @Test
    void applyMove() {
        Move move = Move.fromString("a2-a3");
        Move invalid = Move.fromString("a4-a2");


        assertEquals("rnbqkbnr/pppppppp/8/8/8/P7/1PPPPPPP/RNBQKBNR",
                     GridBuilder.defaultGrid().applyMove(move, Figure.Color.WHITE).toFEN());

        assertThrows(NotPlayersTurnException.class,
            () -> GridBuilder.defaultGrid().applyMove(move, Figure.Color.BLACK));

        assertThrows(NoFigureOnFieldException.class,
            () -> GridBuilder.defaultGrid().applyMove(invalid, Figure.Color.WHITE));
    }
}
