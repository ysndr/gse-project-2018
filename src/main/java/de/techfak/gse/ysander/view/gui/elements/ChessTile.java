package de.techfak.gse.ysander.view.gui.elements;

import java.io.IOException;

import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.InvalidFieldException;

public class ChessTile extends AnchorPane {

    private final ReadOnlyIntegerWrapper y = new ReadOnlyIntegerWrapper();

    private final ReadOnlyIntegerWrapper x = new ReadOnlyIntegerWrapper();

    private final ObjectProperty<State> state = new SimpleObjectProperty<>();

    private final ObjectProperty<Field> onClick = new SimpleObjectProperty<>();

    private final Field fieldKey;

    @FXML
    private Button tileInput;

    @FXML
    private AnchorPane background;


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


    @FXML
    public void initialize() {

        this.background.setBackground(new Background(new BackgroundFill(
            ((getY() + getX()) % 2 == 0
             ? Color.DARKGRAY
             : Color.LIGHTGRAY), CornerRadii.EMPTY, Insets.EMPTY

        )));

        this.tileInput.setOnMouseClicked(event -> this.onClick.setValue(this.fieldKey));
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

    public Field getOnClick() {
        return onClick.get();
    }

    public ObjectProperty<Field> onClickProperty() {
        return onClick;
    }

}
