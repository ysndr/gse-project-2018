package de.techfak.gse.ysander.model;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import de.techfak.gse.ysander.model.error.*;
import de.techfak.gse.ysander.model.figures.Figure;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.HintProvider;
import de.techfak.gse.ysander.model.rules.providers.SelectableHintProvider;
import de.techfak.gse.ysander.util.Cache;

import static de.techfak.gse.ysander.model.figures.Figure.Color;

/**
 * Runtime state of the game.
 * Contains current grid configuration a well as the current player who's turn it is
 */
public final class State {

    private final Grid grid;

    private final Color color;

    private final Field selection;

    private final Cache<Set<? extends Hint>> hints;


    State(Grid grid, Color color) {
        this(grid, color, null);
    }

    State(Grid grid, Color color, Field selection) {
        this.grid = grid;
        this.color = color;
        this.selection = selection;

        this.hints = this.createHintProvider().getHintsCached(this);
    }

    public Color getColor() {
        return color;
    }

    public Grid getGrid() {
        return grid;
    }

    public Field getSelection() {
        return selection;
    }

    public Optional<Figure> getSelectedFigure() {
        return grid.getFigureOnField(getSelection());
    }

    public Set<? extends Hint> getHints() {
        return this.hints.result();
    }


    private HintProvider createHintProvider() {
        HintProvider hintProvider = new SelectableHintProvider();

        if (this.getSelectedFigure().isPresent()) {
            hintProvider = hintProvider.chain(this.getSelectedFigure().get());
        }

        return hintProvider;
    }

    // Modifiers
    State withGrid(Grid grid) {
        return StateBuilder.of(this)
            .setGrid(grid)
            .createState();
    }

    State withColor(Color color) {
        return StateBuilder.of(this)
            .setColor(color)
            .createState();
    }

    private State withSelection(Field selection) {
        return StateBuilder.of(this)
            .setSelection(selection)
            .createState();
    }

    private State withoutSelection() {
        return new StateBuilder()
            .setGrid(this.grid)
            .setColor(this.color)
            .createState();
    }


    /**
     * Tries to apply a field onto the current state by setting the currently
     * selected field, resetting it called with the same field again and calls
     * apply move if a selecte field is set and is called with adifferent one.
     * <p>
     * Also validates the figures on field against current players color and
     * existence.
     *
     * @param field the field that should be (un)selected or with which a move
     *              should be committed
     * @return a new state that contains either a selected field and an
     * unchanged grid, no selection or no selection and a changed grid
     * @throws InvalidMoveException if the move of both fields combined  or the
     *                              to be selected field itself is somehow invalid
     */
    public State applyField(Field field) throws InvalidMoveException {

        // unselect if field requested again
        if (field.equals(this.selection)) {
            return this.withoutSelection();
        }

        Optional<Figure> onField = grid.getFigureOnField(field);


        if (this.selection != null) {
            Optional<Figure> onSelection= grid.getFigureOnField(this.selection);
            // switch to other figure of the same player
            if (onSelection.isPresent() && onField.isPresent() && onField.get().color().equals(onSelection.get().color())) {
                return this.withSelection(field);
            }

            return this.applyMove(new Move(this.selection, field));

        }



        if (!onField.isPresent()) {
            throw new NoFigureOnFieldException(field);
        }

        if (onField.get().color() != this.color) {
            throw new NotPlayersTurnException(onField.get().color());
        }

        return this.withSelection(field);

    }


    /**
     * Applies a {@link Move} onto the current State. If successful returns a
     * new State from containing the new current player in turn and the new Grid
     * configuration. Invalidates the current selected fiels.
     *
     * @param move the move to apply.
     * @return Updated state
     * @throws InvalidMoveException if the move can not be performed for some
     *                              reason.
     */
    public State applyMove(Move move) throws InvalidMoveException {

        if (move.getFrom().equals(move.getTo())) {
            throw new NoFigureMovedException();
        }

        Grid gridAfterMove = this.grid.applyMove(move, this.color);
        Color playerAfterMove = (this.color == Color.BLACK) ? Color.WHITE : Color.BLACK;

        return new StateBuilder().setGrid(gridAfterMove).setColor(playerAfterMove).createState();

    }

    /**
     * Applies itself to the given {@link Hint} thereby delegating the
     * construction of a new state to it. If the hint is not in the set of hints
     * valid for this state an {@link InvalidHintException} is thrown.
     * @param hint the hint to apply
     * @return a new state constructed by the hint
     * @throws InvalidHintException if the hint could not be derived from the
     * current configuration
     */
    public State applyHint(Hint hint) throws InvalidHintException {
        if (!this.getHints().contains(hint)) {
            throw new InvalidHintException(hint);
        }

        return hint.apply(this);
    }

    /**
     * Create a builder from ths intstance.
     * @return StateBuilder loaded with this
     */
    public StateBuilder builder() {
        return StateBuilder.of(this);
    }


    // Converters

    /**
     * Converts state into FEN notation.
     *
     * @return FEN notated State
     */
    public String toFEN() {
        return String.format("%s %s", this.grid.toFEN(), this.color.toFEN());
    }

    @Override
    public String toString() {
        return toFEN();
    }

    // Util
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final State state = (State) o;
        return Objects.equals(grid, state.grid) &&
               color == state.color &&
               Objects.equals(selection, state.selection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, color, selection);
    }
}
