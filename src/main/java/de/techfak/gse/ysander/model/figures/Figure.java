package de.techfak.gse.ysander.model.figures;


import java.util.Objects;
import java.util.Set;

import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.error.FENParseException;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.providers.HintProvider;

/**
 * The abstract figures class.
 * All possible figures must extend this class.
 * It is used to abstract over the symbolc representation of each Figure.
 */
public abstract class Figure implements HintProvider {


    private final Color color;
    private final boolean moved;

    public Figure(final Color color) {
        this(color, false);
    }

    Figure(final Color color, final boolean moved) {
        this.color = color;
        this.moved = moved;
    }


    public Figure moved() {
        return this;
    }

    protected boolean getMoved() {
        return moved;
    }

    /**
     * @return the figures symbolic representation (must take its color into consideration)
     */
    public abstract String symbol();

    /**
     * @return the canonical of this figure
     */
    public abstract String canonicalName();

    /**
     * GetHints implementation for {@link HintProvider}.
     * Used to get the hints for the selected field. Therefore it does not take
     * the specific {@link Figure}s {@link Color} into account. Color regards are left to the
     * underlying {@link HintProvider}s.
     * @param state the state to create rules for
     * @return hints for a figure of this kind
     */
    @Override
    public abstract Set<? extends Hint> getHints(final State state);

    /**
     * @return the figures color
     */
    public Color color() {
        return this.color;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Figure)) {
            return false;
        }
        final Figure figure = (Figure) o;
        return color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    /**
     * Possible Colors for figures.
     */
    public enum Color {
        BLACK,
        WHITE;


        private static final String COLOR_STRING_BLACK = "b";

        private static final String COLOR_STRING_WHITE = "w";

        /**
         * Converts a string serialized color into a color object.
         *
         * @param fen the serialized color
         * @return Deserialized Color
         * @throws FENParseException if `fen` is no valid color representation
         */
        public static Color fromFEN(String fen) throws FENParseException {
            if (fen.equals(COLOR_STRING_BLACK)) {
                return BLACK;
            }
            if (fen.equals(COLOR_STRING_WHITE)) {
                return WHITE;
            }
            throw new FENParseException();
        }

        /**
         * turns the color into fen notation (possibly different from a more
         * meaningful string representation).
         *
         * @return FEN notation of the color
         */
        public String toFEN() {
            return (this == Color.BLACK) ? COLOR_STRING_BLACK : COLOR_STRING_WHITE;
        }

        @Override
        public String toString() {
            if (this == Color.BLACK) {
                return "black";
            } else {
                return "white";
            }
        }
    }
}
