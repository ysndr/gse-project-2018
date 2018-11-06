package de.techfak.gse.ysander.view;

import de.techfak.gse.ysander.model.error.ChessGameException;

public interface Output<T> {

    void display(T state);
}
