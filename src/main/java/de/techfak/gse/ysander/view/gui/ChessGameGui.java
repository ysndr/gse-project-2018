package de.techfak.gse.ysander.view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ysander.view.gui.elements.ChessUI;

public class ChessGameGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

            Parent root = new ChessUI();

            Scene scene = new Scene(root, 300, 275);

            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
}
