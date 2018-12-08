package de.techfak.gse.ysander.view.fx;

import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ysander.communication.drivers.FieldDriver;
import de.techfak.gse.ysander.controller.MultiplayerChessController;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.fx.elements.ChessUI;

public class FXApplication extends javafx.application.Application {

    private static State initialState;

    @Override
    public void start(final Stage stage) throws Exception {
        try {

            ChessUI root = new ChessUI();
            MultiplayerChessController controller = new MultiplayerChessController(
                root.getController(),
                FXApplication.initialState,
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


    public static void startGUI(final State state) {
        initialState = state;
        launch();
    }

}
