package de.techfak.gse.ysander.view;

import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.error.InvalidMoveException;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cli implements MovesInput {

    private final Scanner scanner;

    public Cli() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<Move> requestMoves() throws InvalidMoveException {
        String line = scanner.next();
        String[] moveCommands = line.trim().split(";");

        return Arrays.stream(moveCommands).map(Move::fromString).collect(Collectors.toList());
    }

}
