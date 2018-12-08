package de.techfak.gse.ysander.communication.handlers;

import de.techfak.gse.ysander.model.Field;

/**
 * Accept field input. Basically that means this interface is called whenever a
 * field event is invoked.
 */
public interface FieldInputHandler {
    void handleFieldInput(Field input);
}
