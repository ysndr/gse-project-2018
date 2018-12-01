package de.techfak.gse.ysander.model.figures;


import java.util.Objects;

import de.techfak.gse.ysander.model.error.FENParseException;

/**
 * The abstract figure class.
 * All possible figures must extend this class.
 * It is used to abstract over the symbolc representation of each Figure.
 */
public abstract class Figure {

    /**
     * The figures color.
     */
    private final Color color;

    /**
     * The figures Symbol if black.
     */
    private final char symbolBlack;

    /**
     * The figures Symbol if white.
     */
    private final char symbolWhite;


    Figure(Color color, char symbolBlack, char symbolWhite) {
        this.color = color;
        this.symbolBlack = symbolBlack;
        this.symbolWhite = symbolWhite;
    }


    /**
     * @return the figures symbolic representation (must take its color into consideration)
     */
    public char symbol() {
        if (this.color == Color.WHITE) {
            return symbolWhite;
        }
        if (this.color == Color.BLACK) {
            return symbolBlack;
        }
        return '·';
    }

    /**
     * @return the figures color
     */
    public Color color() {
        return this.color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Figure figure = (Figure) o;
        return symbolBlack == figure.symbolBlack &&
               symbolWhite == figure.symbolWhite &&
               color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, symbolBlack, symbolWhite);
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
    }

}
