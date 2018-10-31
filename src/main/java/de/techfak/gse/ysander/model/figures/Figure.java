package de.techfak.gse.ysander.model.figures;


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
     * The figures Symbol if black
     */
    private final char symbolBlack;

    /**
     * The figures Symbol if white
     */
    private final char symbolWhite;


    public Figure(Color color, char symbolBlack, char symbolWhite) {
        this.color = color;
        this.symbolBlack = symbolBlack;
        this.symbolWhite = symbolWhite;
    }


    /**
     * @return the figures symbolic representation (must take its color into consideration)
     */
    public char symbol() {
        if (this.color == Color.WHITE) return symbolWhite;
        if (this.color == Color.BLACK) return  symbolBlack;
        return '·';
    };

    /**
     * @return the figures color
     */
    public Color color() {
        return this.color;
    };

    /**
     * Possible Colors for figures.
     */
    public enum Color {
        BLACK,
        WHITE;

        public String toFEN() {
            return (this == Color.BLACK) ? "b" : "w";
        }
    }

}
