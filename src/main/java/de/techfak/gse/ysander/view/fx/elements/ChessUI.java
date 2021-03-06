package de.techfak.gse.ysander.view.fx.elements;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Wrapper class for the whole screen.
 */
public class ChessUI extends BorderPane {

    private final ChessUIController controller;

    /**
     * Component Constructor.
     */
    public ChessUI() {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/de/techfak/gse/ysander/view/fx/elements/ChessUI.fxml"));
        loader.setRoot(this);

        try {
            loader.load();
            this.controller = loader.getController();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public ChessUIController getController() {
        return controller;
    }


}
