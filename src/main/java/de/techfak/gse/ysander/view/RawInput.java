package de.techfak.gse.ysander.view;

import java.util.function.BiConsumer;

import de.techfak.gse.ysander.model.State;

public interface RawInput {
    void setRawInputCB(BiConsumer<String, Output<State>> rawInputCB);
}
