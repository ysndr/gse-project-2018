package de.techfak.gse.ysander.view.gui.elements;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ChessUI extends BorderPane {
    public ChessUI() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/techfak/gse/ysander/view/gui/elements/ChessUI.fxml"));
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
