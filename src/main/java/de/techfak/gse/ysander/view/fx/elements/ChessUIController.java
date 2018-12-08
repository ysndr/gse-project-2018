package de.techfak.gse.ysander.view.fx.elements;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.inputs.*;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.View;

public class ChessUIController implements View, FieldInput, LoadInput, SaveInput {

    @FXML
    private GridPane grid;
    @FXML
    private ImageView currentPlayerIndicator;
    @FXML
    private MenuItem menuLoad;
    @FXML
    private MenuItem menuSave;
    @FXML
    private MenuItem menuStart;
    @FXML
    private Label labelState;

    private ObjectProperty<State> state = new SimpleObjectProperty<>();
    private Runnable onInitCB = () -> {};
    private Runnable onStartCB = () ->  {
        this.menuStart.setDisable(true);
        this.grid.setDisable(false);
        this.labelState.setText("started");
    };
    private Window parent;
    private FieldInputHandler inputHandler = (f) -> {};
    private LoadHandler loadHandler = (p) -> {};
    private SaveHandler saveHandler = (p) -> {};


    @FXML
    public void initialize() {
        this.labelState.setText("stoped");
        this.grid.setDisable(true);
        this.grid.getChildren().stream()
            .filter(n -> n instanceof ChessTile)
            .map(n -> (ChessTile) n)
            .forEach(tile -> {
                tile.stateProperty().bind(state);
                tile.onClickProperty().addListener((observableValue, field, t1) -> {
                    if (t1 == null) {
                        return;
                    }
                    inputHandler.handleFieldInput(t1);
                });
            });

        this.stateProperty().addListener((observableValue, state1, t1) -> {
            if (t1.getColor() == null) {
                return;
            }

            if (t1.getColor() == Figure.Color.WHITE) {
                currentPlayerIndicator.setImage(new Image("/de/techfak/gse/ysander/model/figures/king_white.png"));
            } else {
                currentPlayerIndicator.setImage(new Image("/de/techfak/gse/ysander/model/figures/king_black.png"));
            }
        });




        this.menuLoad.setOnAction((e) -> loadHandler.loadState(parent));
        this.menuSave.setOnAction((e) -> saveHandler.saveState(parent));
        this.menuStart.setOnAction((e) -> this.onStartCB.run());


    }

    public State getState() {
        return state.get();
    }

    private ObjectProperty<State> stateProperty() {
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
    public void display(final State state) {
        this.state.setValue(state);
    }

    @Override
    public void setFieldInputHandler(final FieldInputHandler fieldInputHandler) {
        this.inputHandler = fieldInputHandler;
    }

    @Override
    public void setLoadHandler(final LoadHandler handler) {
        this.loadHandler = handler;
    }

    @Override
    public void setSaveHandler(final SaveHandler handler) {
        this.saveHandler = handler;
    }

    public void setParent(Window parent) {
        this.parent = parent;
    }

}
