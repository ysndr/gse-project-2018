package de.techfak.gse.ysander.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.ChessGameException;


public class Cli implements Output<State>, View<ChessGameException>, RawInput {

    private final Scanner scanner;


    private BiConsumer<ChessGameException, Output<State>> exceptionCB = (e, o) -> {};

    private BiConsumer<String, Output<State>> rawInputCB = (m, o) -> {};

    private Consumer<Output<State>> onInitCB = (o) -> {};

    public Cli() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setExceptionCB(BiConsumer<ChessGameException, Output<State>> exceptionCB) {
        this.exceptionCB = exceptionCB;
    }

    @Override
    public void setRawInputCB(BiConsumer<String, Output<State>> rawInputCB) {
        this.rawInputCB = rawInputCB;
    }

    @Override
    public void setOnInitCB(Consumer<Output<State>> onInitCB) {
        this.onInitCB = onInitCB;
    }

    @Override
    public void display(State state) {
        System.out.println(state.toFEN());
    }


    public void start() {
        onInitCB.accept(this);
        new BufferedReader(new InputStreamReader(System.in))
            .lines()
            .forEach(m -> {
                try {
                    rawInputCB.accept(m, this);
                } catch (ChessGameException e) {
                    exceptionCB.accept(e, this);
                }
            });
    }


}
