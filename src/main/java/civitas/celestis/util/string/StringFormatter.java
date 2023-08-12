package civitas.celestis.util.string;

import jakarta.annotation.Nonnull;

/**
 * Contains string formatting utilities.
 */
public final class StringFormatter {
    /**
     * Indents a string. A block is defined with either {@code {}} or {@code []}.
     * Each nested block adds two spaces.
     *
     * @param input The input string
     * @return The indented string
     */
    @Nonnull
    public static String indentString(@Nonnull String input) {
        final String[] lines = input.split("\n");
        final StringBuilder result = new StringBuilder();

        int indentLevel = 0;
        final String indent = "  "; // 2 spaces

        for (String line : lines) {
            line = line.trim(); // Remove leading/trailing spaces
            if (line.startsWith("}") || line.startsWith("]")) {
                indentLevel--;
            }

            for (int i = 0; i < indentLevel; i++) {
                result.append(indent);
            }

            result.append(line).append('\n');

            if (line.endsWith("{") || line.endsWith("[")) {
                indentLevel++;
            }
        }

        return result.toString();
    }

    /**
     * Removes the indents and trims each line of the given string.
     *
     * @param input The input string
     * @return The unindented string
     */
    @Nonnull
    public static String unindentString(@Nonnull String input) {
        final String[] lines = input.split("\n");
        final StringBuilder result = new StringBuilder();

        for (final String line : lines) {
            result.append(line.trim());
            result.append("\n");
        }

        return result.toString();
    }
}
