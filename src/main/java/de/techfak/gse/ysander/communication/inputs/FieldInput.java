package de.techfak.gse.ysander.communication.inputs;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;


/**
 * An input device that calls a {@link FieldInputHandler} with the selected
 * field.
 */
public interface FieldInput {
    void setFieldInputHandler(FieldInputHandler fieldInputHandler);
}
