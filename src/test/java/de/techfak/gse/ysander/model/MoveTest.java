package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void fromString() {
        Field from = new Field(0,0);
        Field to = new Field(7,7);

        assertEquals(new Move(from, to), Move.fromString("a8-h1"));
        assertThrows(InvalidMoveException.class, () -> Move.fromString("i9-x0"));

    }
}
