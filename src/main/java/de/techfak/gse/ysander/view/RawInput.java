package de.techfak.gse.ysander.view;

import de.techfak.gse.ysander.model.State;

import java.util.function.BiConsumer;

public interface RawInput {
    void setRawInputCB(BiConsumer<String, Output<State>> rawInputCB);
}
