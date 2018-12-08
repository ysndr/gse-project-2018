package de.techfak.gse.ysander.view.fx.elements;

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
import de.techfak.gse.ysander.model.figures.Figure;

/**
 * Custom JavaFX element to wrap one tile that can hold a figure and receives
 * clicks. Further, each tile responsible for showing the correct content.
 * This is achieved by letting the tile itself know which tile on a chess grid
 * it represents and binding it to the current (changing) state.
 */
public class ChessTile extends AnchorPane {

    private static final String SELECTED = "selected";

    private static final String UNSELECTED = "unselected";


    private final ReadOnlyIntegerWrapper y = new ReadOnlyIntegerWrapper();

    private final ReadOnlyIntegerWrapper x = new ReadOnlyIntegerWrapper();

    private final ObjectProperty<State> state = new SimpleObjectProperty<>();

    private final ObjectProperty<Field> onClick = new SimpleObjectProperty<>();

    private final Field fieldKey;

    @FXML
    private Button tileInput;

    @FXML
    private AnchorPane background;

    /**
     * Component constructor of the CHessTile.
     *
     * @param y position on the chess grid
     * @param x position on the chess grid
     */
    public ChessTile(@NamedArg("y") int y, @NamedArg("x") int x) {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/de/techfak/gse/ysander/view/fx/elements/ChessTile.fxml"));
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

    /**
     * Component initialization. Binds to clicks and state changes.
     */
    @FXML
    public void initialize() {

        this.background.setBackground(new Background(new BackgroundFill(
            ((getY() + getX()) % 2 == 0
             ? Color.DARKGRAY
             : Color.LIGHTGRAY), CornerRadii.EMPTY, Insets.EMPTY

        )));

        this.tileInput.setOnMouseClicked(event -> {
            this.onClick.setValue(null);
            this.onClick.setValue(this.fieldKey);
        });
        this.stateProperty().addListener((observable, old, value) -> {
            this.update(value);
        });


    }

    /**
     * Performs the updating of this tile by checking if the tile is selected
     * and what figure is placed on this tile's filed.
     *
     * @param value the new state
     */
    private void update(final State value) {
        this.tileInput.getStyleClass().clear();
        if (this.fieldKey.equals(value.getSelection())) {
            this.tileInput.getStyleClass().add(SELECTED);
        } else {
            this.tileInput.getStyleClass().add(UNSELECTED);
        }

        if (value.getGrid().getFigureOnField(fieldKey).isPresent()) {
            Figure fig = value.getGrid().getFigureOnField(fieldKey).get();
            this.tileInput.getStyleClass().addAll(
                "figure_image",
                fig.canonicalName(),
                fig.color().toString());
        }
    }


    // accessor
    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public ReadOnlyIntegerProperty xProperty() {
        return x.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty yProperty() {
        return y.getReadOnlyProperty();
    }

    public State getState() {
        return state.get();
    }

    public Field getOnClick() {
        return onClick.get();
    }

    public ObjectProperty<Field> onClickProperty() {
        return onClick;
    }

}
