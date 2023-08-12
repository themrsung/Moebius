package civitas.celestis.util.string;

import jakarta.annotation.Nonnull;

/**
 * The formatting of a text or string.
 *
 * @param bold      Whether this text is bold
 * @param italic    Whether this text is italic
 * @param strike    Whether this text has a strike
 * @param underline Whether this text is underlined
 */
public record TextFormat(boolean bold, boolean italic, boolean strike, boolean underline) {
    //
    // Constants
    //

    /**
     * The normal format of a text.
     */
    public static TextFormat NORMAL = new TextFormat(false, false, false, false);

    /**
     * A text which is only bold.
     */
    public static TextFormat BOLD = new TextFormat(true, false, false, false);

    /**
     * A text which is only italic.
     */
    public static TextFormat ITALIC = new TextFormat(false, true, false, false);

    /**
     * A text which only has a strike.
     */
    public static TextFormat STRIKE = new TextFormat(false, false, true, false);

    /**
     * A text which is only underlined.
     */
    public static TextFormat UNDERLINE = new TextFormat(false, false, false, true);

    //
    // Toggling
    //

    /**
     * Toggles whether this text is bold.
     *
     * @return The toggled format
     */
    @Nonnull
    public TextFormat toggleBold() {
        return new TextFormat(!bold, italic, strike, underline);
    }

    /**
     * Toggles whether this text is italic.
     *
     * @return The toggled format
     */
    @Nonnull
    public TextFormat toggleItalic() {
        return new TextFormat(bold, !italic, strike, underline);
    }

    /**
     * Toggles whether this text has a strike.
     *
     * @return The toggled format
     */
    @Nonnull
    public TextFormat toggleStrike() {
        return new TextFormat(bold, italic, !strike, underline);
    }

    /**
     * Toggles whether this text is underlined.
     *
     * @return The toggled format
     */
    @Nonnull
    public TextFormat toggleUnderline() {
        return new TextFormat(bold, italic, strike, !underline);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link TextFormat}.
     *
     * @param input The input string to parse
     * @return The parsed text format object
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static TextFormat parseFormat(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("TextFormat{")) {
            throw new IllegalArgumentException("Given string does not represent a TextFormat.");
        }

        final String cleanInput = input.replaceAll("TextFormat\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");

        final boolean[] values = new boolean[4];

        for (final String valueString : valueStrings) {
            final String[] splitByEquals = valueString.split("=");
            values[switch (splitByEquals[0]) {
                case "bold" -> 0;
                case "italic" -> 1;
                case "strike" -> 2;
                case "underline" -> 3;
                default -> throw new IllegalArgumentException("Given string does not represent a TextFormat.");
            }] = Boolean.parseBoolean(splitByEquals[1]);
        }

        return new TextFormat(values[0], values[1], values[2], values[3]);
    }

    /**
     * Serializes this format into a string.
     *
     * @return The string representation of this format
     */
    @Override
    @Nonnull
    public String toString() {
        return "TextFormat{" +
                "bold=" + bold +
                ", italic=" + italic +
                ", strike=" + strike +
                ", underline=" + underline +
                '}';
    }
}
