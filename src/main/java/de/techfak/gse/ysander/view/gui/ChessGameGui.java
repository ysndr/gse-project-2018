package de.techfak.gse.ysander.view.gui;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.communication.inputs.FieldInput;
import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.view.View;
import de.techfak.gse.ysander.view.gui.elements.ChessUI;

public class ChessGameGui extends Application implements View, FieldInput {


    private final ChessUI ui;


    public ChessGameGui() {
        this.ui = new ChessUI();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private Runnable onInitCB = () -> {};
    private FieldInputHandler inputHandler = (f) -> {};


    @Override
    public void start(Stage stage) throws Exception {
        try {

            Parent root = new ChessUI();

            Scene scene = new Scene(root);

            stage.setTitle("FXML Welcome");
            stage.setScene(scene);

            this.ui.getController().fieldInputProperty().addListener((observable, old, field) -> {
                inputHandler.handleFieldInput(field);
            });



            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }


    @Override
    public void start() {
        launch();
        onInitCB.run();
    }

    @Override
    public void setOnInitCB(final Runnable onInitCB) {
        this.onInitCB = onInitCB;
    }

    @Override
    public void setFieldInputHandler(final FieldInputHandler fieldInputHandler) {
        this.setFieldInputHandler(fieldInputHandler);
    }

    @Override
    public void display(final State state) {
        this.ui.getController().stateProperty().setValue(state);
    }
}
