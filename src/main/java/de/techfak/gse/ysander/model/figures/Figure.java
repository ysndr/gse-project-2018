package de.techfak.gse.ysander.model.figures;


import de.techfak.gse.ysander.model.error.FENParseException;

/**
 * The abstract figures class.
 * All possible figures must extend this class.
 * It is used to abstract over the symbolc representation of each Figure.
 */
public abstract class Figure {


    private Color color;


    public Figure(final Color color) {
        this.color = color;
    }


    /**
     * @return the figures symbolic representation (must take its color into consideration)
     */
    public abstract String symbol();

    public abstract String canonicalName();

    /**
     * @return the figures color
     */
    public Color color() {
        return this.color;
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
