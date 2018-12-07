package de.techfak.gse.ysander.view.gui.elements;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.layout.GridPane;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;

public class ChessUIController {

    @FXML
    private GridPane grid;
    private ObjectProperty<State> state = new SimpleObjectProperty<>();

    private ObjectProperty<Field> fieldInput = new SimpleObjectProperty<>();


    @FXML
    public void initialize() {
        this.grid.getChildren().stream()
            .filter(n -> n instanceof ChessTile)
            .map(n -> (ChessTile) n)
            .forEach(tile -> {
                tile.stateProperty().bind(state);
                fieldInput.bind(tile.onClickProperty());
            });
    }

    public State getState() {
        return state.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public Field getFieldInput() {
        return fieldInput.get();
    }

    public ObjectProperty<Field> fieldInputProperty() {
        return fieldInput;
    }




}
