package de.techfak.gse.ysander.view.gui.elements;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.model.State;

public class ChessUIController implements Output<State>{

    @FXML
    private GridPane grid;
    private ObjectProperty<State> state = new SimpleObjectProperty<>();


    @FXML
    public void initialize() {
        this.grid.getChildren().stream()
            .filter(n -> n instanceof Button)
            .map(n -> (Button) n)
            .forEach(tile -> tile.setOnMouseClicked(mouseEvent -> {
                int y  = GridPane.getRowIndex(tile);
                int x = GridPane.getColumnIndex(tile);
            }));
    }

    @Override
    public void display(final State state) {
        this.state.setValue(state);
    }
}
