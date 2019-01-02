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

import de.techfak.gse.ysander.communication.handlers.HintInputHandler;
import de.techfak.gse.ysander.communication.handlers.LoadHandler;
import de.techfak.gse.ysander.communication.handlers.SaveHandler;
import de.techfak.gse.ysander.communication.inputs.HintInput;
import de.techfak.gse.ysander.communication.inputs.LoadInput;
import de.techfak.gse.ysander.communication.inputs.SaveInput;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.View;

/**
 * Controller for the main view that implements all interfaces the backend (game controllers) needs.
 */
public class ChessUIController implements View, HintInput, LoadInput, SaveInput {

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

    private Runnable onInitCB = () -> { };

    private Runnable onStartCB = () -> {
        this.menuStart.setDisable(true);
        this.grid.setDisable(false);
        this.labelState.setText("started");
    };

    private Window parent;

    private HintInputHandler inputHandler = (h) -> { };

    private LoadHandler loadHandler = () -> { return this.state.get(); };

    private SaveHandler saveHandler = (state) -> { };

    /**
     * Component Controller
     * - Listen to all tile clicks and expose to {@link HintInputHandler}.
     * - Bind all tiles to the state property.
     * - Listen to state changes and apply current player to the indicator.
     * - on initialization disable grid until start is clicked (why?? :D)
     */
    @FXML
    public void initialize() {
        this.reset();
        this.grid.getChildren().stream()
            .filter(ChessTile.class::isInstance)
            .map(ChessTile.class::cast)
            .forEach(tile -> {
                tile.stateProperty().bind(state);
                tile.onClickProperty().addListener((observableValue, field, t1) -> {
                    if (t1 == null) {
                        return;
                    }
                    inputHandler.handleHintInput(t1);
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

            t1.hasWon().ifPresent(color -> {
                message(String.format("%s has won!"));
                reset();
            });

        });


        this.menuLoad.setOnAction((e) -> {
            this.reset();
            this.loadHandler.loadState(); // setting is left to the callee site
        });
        this.menuSave.setOnAction((e) -> saveHandler.saveState(state.get()));
        this.menuStart.setOnAction((e) -> this.onStartCB.run());

    }


    private void reset() {
        this.menuStart.setDisable(false);
        this.grid.setDisable(true);
        message("Game stopped");
    }


    // stuff

    private ObjectProperty<State> stateProperty() {
        return state;
    }

    public State getState() {
        return state.get();
    }

    @Override
    public void start() {
        onInitCB.run();
    }

    @Override
    public void message(final String message) {
        this.labelState.setText(message);
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
    public void setHintInputHandler(final HintInputHandler hintInputHandler) {
        this.inputHandler = hintInputHandler;
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
