package de.techfak.gse.ysander.view;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.techfak.gse.ysander.model.State;

public interface View<E> {
    void start();

    void setExceptionCB(BiConsumer<E, Output<State>> exceptionCB);

    void setOnInitCB(Consumer<Output<State>> onInitCB);
}
