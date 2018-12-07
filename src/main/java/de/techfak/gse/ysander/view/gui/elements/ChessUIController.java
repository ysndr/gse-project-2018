package de.techfak.gse.ysander.view.gui.elements;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.layout.GridPane;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.inputs.FieldInput;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;

public class ChessUIController implements Output<State>, FieldInput {

    @FXML
    private GridPane grid;
    private ObjectProperty<State> state = new SimpleObjectProperty<>();

    private FieldInputHandler fieldInputHandler = (f) -> {};


    @FXML
    public void initialize() {
        this.grid.getChildren().stream()
            .filter(n -> n instanceof ChessTile)
            .map(n -> (ChessTile) n)
            .forEach(tile -> {
                tile.stateProperty().bind(state);
                tile.onClickProperty().addListener((observable, old, current) -> {
                    this.fieldInputHandler.handleFieldInput(current);
                });
            });
    }

    @Override
    public void display(final State state) {
        this.state.setValue(state);
    }

    @Override
    public void setFieldInputHandler(final FieldInputHandler fieldInputHandler) {
        this.fieldInputHandler = fieldInputHandler;
    }
}
