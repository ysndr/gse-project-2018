package de.techfak.gse.ysander.view;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface View<E> {
    void start();
    void setExceptionCB(BiConsumer<E, Output<State>> exceptionCB);
    void setOnInitCB(Consumer<Output<State>> onInitCB);
}
