package de.techfak.gse.ysander.view;

/**
 * The generic Output interface.
 * Classes implementing this interface implement some way to display data of
 * type {@link T}
 * @param <T> type of displayed data
 */
public interface Output<T> {

    void display(T state);
}
