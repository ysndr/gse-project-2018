package de.techfak.gse.ysander.view.gui.elements;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.layout.GridPane;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.inputs.FieldInput;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.view.View;

public class ChessUIController implements View, FieldInput {

    @FXML
    private GridPane grid;
    private ObjectProperty<State> state = new SimpleObjectProperty<>();

    private Runnable onInitCB;

    private FieldInputHandler inputHandler;


    @FXML
    public void initialize() {
        this.grid.getChildren().stream()
            .filter(n -> n instanceof ChessTile)
            .map(n -> (ChessTile) n)
            .forEach(tile -> {
                tile.stateProperty().bind(state);
                tile.onClickProperty().addListener((observableValue, field, t1) -> {
                    inputHandler.handleFieldInput(t1);
                });
            });
    }

    public State getState() {
        return state.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }



    @Override
    public void start() {
        onInitCB.run();
    }

    @Override
    public void setOnInitCB(final Runnable onInitCB) {
        this.onInitCB = onInitCB;
    }

    @Override
    public void setFieldInputHandler(final FieldInputHandler fieldInputHandler) {
        System.out.println("set new handler");
        this.inputHandler = fieldInputHandler;
    }

    @Override
    public void display(final State state) {
        this.state.setValue(state);
    }
}
