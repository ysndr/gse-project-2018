package de.techfak.gse.ysander.model;

import de.techfak.gse.ysander.model.error.InvalidFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void validation() {
        assertThrows(InvalidFieldException.class, () -> new Field("i9"));
        assertThrows(InvalidFieldException.class, () -> new Field("i8"));
        assertThrows(InvalidFieldException.class, () -> new Field("1a"));
        assertThrows(InvalidFieldException.class, () -> new Field("cc"));

        assertThrows(InvalidFieldException.class, () -> new Field(8,8));
        assertThrows(InvalidFieldException.class, () -> new Field(-3,3));
        assertThrows(InvalidFieldException.class, () -> new Field(-3,8));

        assertEquals(new Field("d8"), new Field(3,0));

    }

    @Test
    void toCoords() {
        assertEquals("d8", new Field(3,0).toCoords());
    }
}
