package de.techfak.gse.ysander.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void toFEN() {
        Grid grid = Grid.defaultGrid();

        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", grid.toFEN());
    }
}
