package de.techfak.gse.ysander.view;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.techfak.gse.ysander.model.State;

/**
 * A view Abstraction that can be {@link #start()}ed and will accept some input
 * by also implementing some Input interface. It will also notify listeners via
 * callbacks about exceptions of type {@link E}, as well as its initialization.
 *
 * @param <E> Type of exception to notify
 */
public interface View<E> {

    /**
     * Start the view whatever that means for the implementation.
     * - Should call the {@code onInitCB} to notify listeners about its creation
     * - Should catch Exceptions of type {@link E} and notify listeners by
     *   calling {@code onExceptionCB}
     */
    void start();

    void setExceptionCB(BiConsumer<E, Output<State>> onExceptionCB);

    void setOnInitCB(Consumer<Output<State>> onInitCB);
}
