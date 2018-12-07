package de.techfak.gse.ysander.view.gui.elements;

import java.io.IOException;

import javafx.beans.NamedArg;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidFieldException;

public class ChessTile extends AnchorPane {

    private final ReadOnlyIntegerWrapper y = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper x = new ReadOnlyIntegerWrapper();
    private final ObjectProperty<State> state = new SimpleObjectProperty<>();

    private final Field fieldKey;


    public ChessTile(@NamedArg("y") int y, @NamedArg("x") int x) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/techfak/gse/ysander/view/gui/elements/ChessTile.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.setClassLoader(this.getClass().getClassLoader());

        this.y.set(y);
        this.x.set(x);

        try {
            this.fieldKey = new Field(x, y);
        } catch (InvalidFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            loader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }




    // accessor
    public int getX() {
        return x.get();
    }

    public ReadOnlyIntegerProperty xProperty() {
        return x.getReadOnlyProperty();
    }

    public int getY() {
        return y.get();
    }

    public ReadOnlyIntegerProperty yProperty() {
        return y.getReadOnlyProperty();
    }

    public State getState() {
        return state.get();
    }
    public ObjectProperty<State> stateProperty() {
        return state;
    }
}
