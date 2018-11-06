package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.NoFigureMovedException;
import de.techfak.gse.ysander.model.error.NoFigureOnFieldException;
import de.techfak.gse.ysander.model.error.NotPlayersTurnException;
import de.techfak.gse.ysander.model.figures.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {

    @Test
    void toFEN() {
        Grid grid = Grid.defaultGrid();

        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", grid.toFEN());
    }

    @Test
    void fromFEN() {
        assertEquals(Grid.defaultGrid(), Grid.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            Grid.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR").toFEN());
    }


    @Test
    void applyMove() {
        Move move = Move.fromString("a2-a3");
        Move invalid = Move.fromString("a4-a2");


        assertEquals("rnbqkbnr/pppppppp/8/8/8/P7/1PPPPPPP/RNBQKBNR",
            Grid.defaultGrid().applyMove(move, Figure.Color.WHITE).toFEN());

        assertThrows(NotPlayersTurnException.class,
            () -> Grid.defaultGrid().applyMove(move, Figure.Color.BLACK));

        assertThrows(NoFigureOnFieldException.class,
            () -> Grid.defaultGrid().applyMove(invalid, Figure.Color.WHITE));
    }
}
