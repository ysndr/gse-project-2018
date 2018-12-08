package de.techfak.gse.ysander.view.fx;

import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ysander.communication.drivers.FieldDriver;
import de.techfak.gse.ysander.controller.MultiplayerChessController;
import de.techfak.gse.ysander.model.Player;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.StateBuilder;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.view.fx.elements.ChessUI;

/**
 * A javafx application used to setup a game with gui frontend and multiple
 * players.
 */
public class FXApplication extends javafx.application.Application {

    private static State initialState;

    /**
     * Launch the application with given {@code initialState}.
     * This is done quite "hacky" with a static State that will be picked up on
     * jfx event thread. I did not manage to find a better way to both separate
     * the applications AND hand over a parsed state.
     *
     * @param state a preconstructed state to be picked up by the application
     */
    public static void startGUI(final State state) {
        initialState = state;
        launch();
    }

    /**
     * Default launcher. Does not allow setting configuration as parameter.
     * @param args program arguments
     */
    public static void main(String... args) {
        System.err.println("Starting this class only allows starting with a default configuration");
        System.err.println("To apply a custom configuration as program argument, start `ChessGame --gui <fen>`");

        initialState = StateBuilder.defaultState();
        launch();
    }

    /**
     * JavaFX start method. Initializes Controller and View.
     *
     * @param stage JavaFX stage
     * @throws Exception if Application could not be shown
     */
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

            stage.setTitle("GSE project: Chess");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

}
