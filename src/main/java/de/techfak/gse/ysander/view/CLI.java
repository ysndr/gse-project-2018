package de.techfak.gse.ysander.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.BiConsumer;

import de.techfak.gse.ysander.communication.handlers.RawInputHandler;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;
import de.techfak.gse.ysander.communication.output.Output;
import de.techfak.gse.ysander.communication.inputs.RawInput;

/**
 * Implementation for Input, Output and view_v1 View based on stdin/-out.
 */
public class CLI implements View, RawInput {

    private final Scanner scanner;


    private BiConsumer<ChessGameException, Output<State>> exceptionCB = (e, o) -> { };

    private RawInputHandler rawInputHandler =  input -> {};

    private Runnable onInitCB = () -> { };

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setRawInputHandler(RawInputHandler rawInputHandler) {
        this.rawInputHandler = rawInputHandler;
    }

    @Override
    public void setOnInitCB(Runnable onInitCB) {
        this.onInitCB = onInitCB;
    }

    /**
     * Start the CLI view_v1 by looping over the inputs and calling the
     * {@link #rawInputHandler} on each inputs line.
     */
    public void start() {
        onInitCB.run();
        new BufferedReader(new InputStreamReader(System.in))
            .lines()
            .forEach(rawInputHandler::handleRawInput);
    }

    @Override
    public void display(State state) {
        System.out.println(state.toFEN());
    }


}
