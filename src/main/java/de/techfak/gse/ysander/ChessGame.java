package de.techfak.gse.ysander;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ysander.communication.drivers.FieldDriver;
import de.techfak.gse.ysander.communication.drivers.RawDriver;
import de.techfak.gse.ysander.communication.drivers.RawInputDriver;
import de.techfak.gse.ysander.communication.handlers.FieldInputHandler;
import de.techfak.gse.ysander.controller.GlobalErrorEmergencyExit;
import de.techfak.gse.ysander.controller.MultiplayerChessController;
import de.techfak.gse.ysander.controller.RawChessController;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.CLI;
import de.techfak.gse.ysander.view.gui.elements.ChessUI;

public class ChessGame extends Application {

    private Runnable onInitCB = () -> {};

    private FieldInputHandler inputHandler = (f) -> {System.out.println("default handler");};

    private ObjectProperty<State> state = new SimpleObjectProperty<>();

    public FieldInputHandler getInputHandler() {
        return inputHandler;
    }


    @Override
    public void start(Stage stage) throws Exception {
        State state = StateBuilder.defaultState();
        List<String> parameters = new ArrayList<>(getParameters().getUnnamed());

        boolean withGUI = parameters.remove("--gui");

        if (!parameters.isEmpty()) {
            try {
                state = StateBuilder.fromFEN(parameters.get(0));
            } catch (FENParseException e) {
                GlobalErrorEmergencyExit.getInstance().handleError(e);
            }
        }


        if (withGUI) {
            this.startGUI(stage, state);
        } else {
            this.startCLI(state);
        }

    }

    private void startGUI(final Stage stage, final State state) {

        try {

            ChessUI root = new ChessUI();
            MultiplayerChessController controller = new MultiplayerChessController(
                root.getController(),
                state,
                new FieldDriver(new Player(Figure.Color.WHITE), root.getController()),
                new FieldDriver(new Player(Figure.Color.BLACK), root.getController()));

            root.getController().setLoadHandler(controller);
            root.getController().setSaveHandler(controller);

            root.getController().start();


            Scene scene = new Scene(root);

            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
    private void startCLI(final State state) {
        CLI view = new CLI();
        RawInputDriver rawDriver = new RawDriver();
        view.setRawInputHandler(rawDriver);

        RawChessController controller = new RawChessController(view, rawDriver, state);
        view.start();
    }
}
