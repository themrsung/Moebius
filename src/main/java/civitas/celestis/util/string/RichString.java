package civitas.celestis.util.string;

import civitas.celestis.graphics.color.RichColor;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An immutable string with a color and format.
 */
public class RichString implements Serializable {
    //
    // Constants
    //

    /**
     * The default color of a rich string.
     */
    @Nonnull
    public static final RichColor DEFAULT_COLOR = RichColor.BLACK;

    /**
     * The default format of a rich string.
     */
    @Nonnull
    public static final TextFormat DEFAULT_FORMAT = TextFormat.NORMAL;

    //
    // Constructors
    //

    /**
     * Creates a new rich string.
     */
    public RichString() {
        this("", DEFAULT_COLOR, DEFAULT_FORMAT);
    }

    /**
     * Creates a new rich string.
     *
     * @param content The content of this string
     */
    public RichString(@Nonnull String content) {
        this(content, DEFAULT_COLOR, DEFAULT_FORMAT);
    }

    /**
     * Creates a new rich string.
     *
     * @param content The content of this string
     * @param color   The color of this string
     */
    public RichString(@Nonnull String content, @Nonnull RichColor color) {
        this(content, color, DEFAULT_FORMAT);
    }

    /**
     * Creates a new rich string.
     *
     * @param content The content of this string
     * @param format  The format of this string
     */
    public RichString(@Nonnull String content, @Nonnull TextFormat format) {
        this(content, DEFAULT_COLOR, format);
    }

    /**
     * Creates a new rich string.
     *
     * @param content The content of this string
     * @param color   The color of this string
     * @param format  The format of this string
     */
    public RichString(@Nonnull String content, @Nonnull RichColor color, @Nonnull TextFormat format) {
        this.content = content;
        this.color = color;
        this.format = format;
    }

    //
    // Variables
    //
    @Nonnull
    private final String content;
    @Nonnull
    private final RichColor color;
    @Nonnull
    private final TextFormat format;

    //
    // Getters
    //

    /**
     * Returns the content of this string.
     *
     * @return The content of this string
     */
    @Nonnull
    public final String content() {
        return content;
    }

    /**
     * Returns the color of this string.
     *
     * @return The color of this string
     */
    @Nonnull
    public final RichColor color() {
        return color;
    }

    /**
     * Returns the format of this string.
     *
     * @return The format of this string
     */
    @Nonnull
    public final TextFormat format() {
        return format;
    }

    //
    // Properties
    //

    /**
     * Returns the length of this string.
     *
     * @return The length of this string
     */
    public int length() {
        return content.length();
    }

    /**
     * Returns whether this string is empty. (the length is {@code 0})
     *
     * @return {@code true} if the string is empty
     * @see String#isEmpty()
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }

    /**
     * Returns whether this string is empty or only contains whitespace.
     *
     * @return {@code true} if the string is blank
     * @see String#isBlank()
     */
    public boolean isBlank() {
        return content.isBlank();
    }

    /**
     * Returns a stream of lines extracted from this string.
     *
     * @return The stream of lines extracted from this string
     * @see String#lines()
     */
    @Nonnull
    public Stream<String> lines() {
        return content.lines();
    }

    /**
     * Returns a stream of {@code int} zero-extending the {@code char} values.
     *
     * @return An {@link IntStream} of {@code char} values from this string
     * @see String#chars()
     */
    @Nonnull
    public IntStream chars() {
        return content.chars();
    }

    /**
     * Converts this string into a new character array.
     *
     * @return The character array representation of this string
     * @see String#toCharArray()
     */
    @Nonnull
    public char[] toCharArray() {
        return content.toCharArray();
    }

    /**
     * Checks if this string contains the given sequence.
     *
     * @param cs The sequence to search for
     * @return {@code true} if this string contains the sequence
     * @see String#contains(CharSequence)
     */
    public boolean contains(@Nonnull CharSequence cs) {
        return content.contains(cs);
    }

    /**
     * Checks if this string contains another string.
     *
     * @param string The string to search for
     * @return {@code true} if this string contains the other string
     * @see String#contains(CharSequence)
     */
    public boolean contains(@Nonnull RichString string) {
        return content.contains(string.content);
    }

    /**
     * Returns the index of the first occurrence of the given string within this string.
     *
     * @param string The string to search for
     * @return The index of the first occurrence if found, {@code -1} if not found
     * @see String#indexOf(String)
     */
    public int indexOf(@Nonnull String string) {
        return content.indexOf(string);
    }

    /**
     * Returns the index of the first occurrence of the given string within this string.
     *
     * @param string The string to search for
     * @return The index of the first occurrence if found, {@code -1} if not found
     * @see String#indexOf(String)
     */
    public int indexOf(@Nonnull RichString string) {
        return content.indexOf(string.content);
    }

    /**
     * Returns the index of the first occurrence of the given string within this string.
     *
     * @param string    The string to search for
     * @param fromIndex The index from which to start the search
     * @return The index of the first occurrence if found, {@code -1} if not found
     * @see String#indexOf(String, int)
     */
    public int indexOf(@Nonnull String string, int fromIndex) {
        return content.indexOf(string, fromIndex);
    }

    /**
     * Returns the index of the first occurrence of the given string within this string.
     *
     * @param string    The string to search for
     * @param fromIndex The index from which to start the search
     * @return The index of the first occurrence if found, {@code -1} if not found
     * @see String#indexOf(String, int)
     */
    public int indexOf(@Nonnull RichString string, int fromIndex) {
        return content.indexOf(string.content, fromIndex);
    }

    /**
     * Checks if this string starts with the given prefix.
     *
     * @param prefix The prefix to search for
     * @return {@code true} if this string starts with {@code prefix}
     * @see String#startsWith(String)
     */
    public boolean startsWith(@Nonnull String prefix) {
        return content.startsWith(prefix);
    }

    /**
     * Checks if this string starts with the given prefix.
     *
     * @param prefix The prefix to search for
     * @return {@code true} if this string starts with {@code prefix}
     * @see String#startsWith(String)
     */
    public boolean startsWith(@Nonnull RichString prefix) {
        return content.startsWith(prefix.content);
    }

    /**
     * Checks if this string starts with the given prefix after the given offset.
     *
     * @param prefix The prefix to search for
     * @param offset The offset to ignore before starting the search
     * @return {@code true} if this string starts with {@code prefix}
     * @see String#startsWith(String, int)
     */
    public boolean startsWith(@Nonnull String prefix, int offset) {
        return content.startsWith(prefix, offset);
    }

    /**
     * Checks if this string starts with the given prefix after the given offset.
     *
     * @param prefix The prefix to search for
     * @param offset The offset to ignore before starting the search
     * @return {@code true} if this string starts with {@code prefix}
     * @see String#startsWith(String, int)
     */
    public boolean startsWith(@Nonnull RichString prefix, int offset) {
        return content.startsWith(prefix.content, offset);
    }

    /**
     * Checks if this string ends with the given suffix.
     *
     * @param suffix The suffix to search for
     * @return {@code true} if this string ends with {@code suffix}
     * @see String#endsWith(String)
     */
    public boolean endsWith(@Nonnull String suffix) {
        return content.endsWith(suffix);
    }

    /**
     * Checks if this string ends with the given suffix.
     *
     * @param suffix The suffix to search for
     * @return {@code true} if this string ends with {@code suffix}
     * @see String#endsWith
     */
    public boolean endsWith(@Nonnull RichString suffix) {
        return content.endsWith(suffix.content);
    }

    //
    // Methods
    //

    /**
     * Applies given operator to the content of this string, then returns the resulting object.
     *
     * @param operator The operator to apply to the content of this string
     * @return The resulting string
     */
    @Nonnull
    public RichString apply(@Nonnull UnaryOperator<String> operator) {
        return new RichString(operator.apply(content), color, format);
    }

    /**
     * Converts this string to all lowercase.
     *
     * @return The resulting string
     * @see String#toLowerCase()
     */
    @Nonnull
    public RichString toLowerCase() {
        return new RichString(content.toLowerCase(), color, format);
    }

    /**
     * Converts this string to all lowercase.
     *
     * @param locale The locale to use
     * @return The resulting string
     * @see String#toLowerCase(Locale)
     */
    @Nonnull
    public RichString toLowerCase(@Nonnull Locale locale) {
        return new RichString(content.toLowerCase(locale), color, format);
    }

    /**
     * Converts this string to all uppercase.
     *
     * @return The resulting string
     * @see String#toUpperCase()
     */
    @Nonnull
    public RichString toUpperCase() {
        return new RichString(content.toUpperCase(), color, format);
    }

    /**
     * Converts this string to all uppercase.
     *
     * @param locale The locale to use
     * @return The resulting string
     * @see String#toUpperCase(Locale)
     */
    @Nonnull
    public RichString toUpperCase(@Nonnull Locale locale) {
        return new RichString(content.toUpperCase(locale), color, format);
    }

    /**
     * Trims this string of all leading and trailing spaces.
     *
     * @return The trimmed string
     * @see String#trim()
     */
    @Nonnull
    public RichString trim() {
        return new RichString(content.trim(), color, format);
    }

    /**
     * Strips this string of all leading and trailing whitespaces.
     *
     * @return The stripped string
     * @see String#strip()
     */
    @Nonnull
    public RichString strip() {
        return new RichString(content.strip(), color, format);
    }

    /**
     * Adjusts the indentation of each line of this string.
     *
     * @param n The number of leading whitespaces to add or remove
     * @return The adjusted string
     * @see String#indent(int)
     */
    @Nonnull
    public RichString indent(int n) {
        return new RichString(content.indent(n), color, format);
    }

    /**
     * Strips this string of indentation.
     *
     * @return The resulting string
     * @see String#stripIndent()
     */
    @Nonnull
    public RichString stripIndent() {
        return new RichString(content.stripIndent(), color, format);
    }

    /**
     * Applies a function to this string, then returns the return value.
     *
     * @param f   The function to apply to this string
     * @param <R> The type of return value of the function
     * @return The return value of the function
     * @see String#transform(Function)
     */
    @Nonnull
    public <R> R transform(@Nonnull Function<? super RichString, ? extends R> f) {
        return f.apply(this);
    }

    /**
     * Repeats this string {@code count} times.
     *
     * @param count The number of times to repeat this string
     * @return The resulting string
     * @see String#repeat(int)
     */
    @Nonnull
    public RichString repeat(int count) {
        return new RichString(content.repeat(count), color, format);
    }

    /**
     * Splits this string into multiple substrings.
     *
     * @param regex The delimiting regular expression
     * @return An array of split strings
     * @see String#split(String)
     */
    @Nonnull
    public RichString[] split(@Nonnull String regex) {
        final String[] contents = content.split(regex);
        final RichString[] results = new RichString[contents.length];

        for (int i = 0; i < contents.length; i++) {
            results[i] = new RichString(contents[i], color, format);
        }

        return results;
    }

    /**
     * Splits this string into multiple substrings.
     *
     * @param regex The delimiting regular expression
     * @return An array of split strings
     * @see String#split(String)
     */
    @Nonnull
    public RichString[] split(@Nonnull RichString regex) {
        return split(regex.content);
    }

    /**
     * Splits this string into multiple substrings.
     *
     * @param regex The delimiting regular expression
     * @param limit The result threshold
     * @return An array of split strings
     * @see String#split(String, int)
     */
    @Nonnull
    public RichString[] split(@Nonnull String regex, int limit) {
        final String[] contents = content.split(regex, limit);
        final RichString[] results = new RichString[contents.length];

        for (int i = 0; i < contents.length; i++) {
            results[i] = new RichString(contents[i], color, format);
        }

        return results;
    }

    /**
     * Splits this string into multiple substrings.
     *
     * @param regex The delimiting regular expression
     * @param limit The result threshold
     * @return An array of split strings
     * @see String#split(String, int)
     */
    @Nonnull
    public RichString[] split(@Nonnull RichString regex, int limit) {
        return split(regex.content, limit);
    }

    /**
     * Replaces all instance of the target character to the replacement character.
     *
     * @param target      The target character
     * @param replacement The replacement character
     * @return The resulting string
     * @see String#replace(char, char)
     */
    @Nonnull
    public RichString replace(char target, char replacement) {
        return new RichString(content.replace(target, replacement), color, format);
    }

    /**
     * Replaces each substring of this string that matches the specified target.
     *
     * @param target      The target sequence
     * @param replacement The replacement sequence
     * @return The resulting string
     * @see String#replace(CharSequence, CharSequence)
     */
    @Nonnull
    public RichString replace(@Nonnull CharSequence target, @Nonnull CharSequence replacement) {
        return new RichString(content.replace(target, replacement), color, format);
    }

    /**
     * Replaces all instances of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceAll(String, String)
     */
    @Nonnull
    public RichString replaceAll(@Nonnull String regex, @Nonnull String replacement) {
        return new RichString(content.replace(regex, replacement), color, format);
    }

    /**
     * Replaces all instances of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceAll(String, String)
     */
    @Nonnull
    public RichString replaceAll(@Nonnull String regex, @Nonnull RichString replacement) {
        return new RichString(content.replace(regex, replacement.content), color, format);
    }

    /**
     * Replaces all instances of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceAll(String, String)
     */
    @Nonnull
    public RichString replaceAll(@Nonnull RichString regex, @Nonnull String replacement) {
        return new RichString(content.replace(regex.content, replacement), color, format);
    }

    /**
     * Replaces all instances of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceAll(String, String)
     */
    @Nonnull
    public RichString replaceAll(@Nonnull RichString regex, @Nonnull RichString replacement) {
        return new RichString(content.replace(regex.content, replacement.content), color, format);
    }

    /**
     * Replaces the first instance of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceFirst(String, String)
     */
    @Nonnull
    public RichString replaceFirst(@Nonnull String regex, @Nonnull String replacement) {
        return new RichString(content.replaceFirst(regex, replacement), color, format);
    }

    /**
     * Replaces the first instance of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceFirst(String, String)
     */
    @Nonnull
    public RichString replaceFirst(@Nonnull String regex, @Nonnull RichString replacement) {
        return new RichString(content.replaceFirst(regex, replacement.content), color, format);
    }

    /**
     * Replaces the first instance of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceFirst(String, String)
     */
    @Nonnull
    public RichString replaceFirst(@Nonnull RichString regex, @Nonnull String replacement) {
        return new RichString(content.replaceFirst(regex.content, replacement), color, format);
    }

    /**
     * Replaces the first instance of the target string.
     *
     * @param regex       The regular expression to target
     * @param replacement The replacement string
     * @return The resulting string
     * @see String#replaceFirst(String, String)
     */
    @Nonnull
    public RichString replaceFirst(@Nonnull RichString regex, @Nonnull RichString replacement) {
        return new RichString(content.replaceFirst(regex.content, replacement.content), color, format);
    }

    /**
     * Concatenates the specified string to the end of this string.
     *
     * @param string The string to concatenate to the end of this string
     * @return The resulting string
     * @see String#concat(String)
     */
    @Nonnull
    public RichString concat(@Nonnull String string) {
        return new RichString(content.concat(string), color, format);
    }

    /**
     * Concatenates the specified string to the end of this string.
     *
     * @param string The string to concatenate to the end of this string
     * @return The resulting string
     * @see String#concat(String)
     */
    @Nonnull
    public RichString concat(@Nonnull RichString string) {
        return new RichString(content.concat(string.content), color, format);
    }

    /**
     * Returns a substring of this string beginning at the given index,
     * and ending at the last character of this string.
     *
     * @param beginIndex The beginning index of the substring
     * @return The substring of this string
     * @see String#substring(int)
     */
    @Nonnull
    public RichString substring(int beginIndex) {
        return substring(beginIndex, length());
    }

    /**
     * Returns a substring of this string.
     *
     * @param beginIndex The beginning index of the substring
     * @param endIndex   The end index of ths substring
     * @return The substring of this string
     * @see String#substring(int, int)
     */
    @Nonnull
    public RichString substring(int beginIndex, int endIndex) {
        return new RichString(content.substring(beginIndex, endIndex), color, format);
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this string and another object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is an instance of {@link RichString}, and all the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof RichString rs)) return false;
        return content.equals(rs.content) && color.equals(rs.color) && format.equals(rs.format);
    }

    /**
     * Checks for equality with a {@link String}.
     *
     * @param str The string to compare to
     * @return {@code true} if the content is equal to the given string {@code str}
     */
    public final boolean equals(@Nonnull String str) {
        return content.equals(str);
    }

    /**
     * Checks for equality with a {@link String} without regard to casing.
     *
     * @param str The string to compare to
     * @return {@code true} if the content is equal to the given string {@code str} without regard to case
     */
    public final boolean equalsIgnoreCase(@Nonnull String str) {
        return content.equalsIgnoreCase(str);
    }

    /**
     * Checks for equality with a {@link RichString} without regard to casing or formatting.
     *
     * @param str The string to compare to
     * @return {@code true} if {@code content.equalsIgnoreCase(str.content)} is {@code true}
     */
    public final boolean equalsIgnoreCase(@Nonnull RichString str) {
        return content.equalsIgnoreCase(str.content);
    }

    /**
     * Checks for equality with a {@link RichString} without regard to formatting.
     *
     * @param str The string to compare to
     * @return {@code true} if the contents are equal
     */
    public final boolean equalsIgnoreFormat(@Nonnull RichString str) {
        return content.equals(str.content);
    }

    /**
     * Checks for equality with a {@link RichString} without regard to casing.
     *
     * @param str The string to compare to
     * @return {@code true} if the content is equal without regard to case, and the formatting is equal
     */
    public boolean equalsIgnoreCaseButCheckFormat(@Nonnull RichString str) {
        return content.equalsIgnoreCase(str.content) && color.equals(str.color) && format.equals(str.format);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link RichString}.
     *
     * @param input The input string to parse
     * @return The parsed {@link RichString}
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static RichString parseString(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RichString{")) {
            throw new IllegalArgumentException("Given string does not represent a RichString.");
        }

        final String cleanInput = input.replaceAll("RichString\\{\n|\n}", "");
        final String[] lines = cleanInput.split("\n");

        final String[] content = new String[1];
        final RichColor[] color = new RichColor[1];
        final TextFormat[] format = new TextFormat[1];

        for (final String line : lines) {
            final String[] splitByEquals = line.split("=", 2);
            switch (splitByEquals[0].trim()) {
                case "content" -> {
                    content[0] = splitByEquals[1].replaceFirst("'", "");
                    content[0] = content[0].substring(0, content[0].length() - 1);
                }
                case "color" -> color[0] = RichColor.parseColor(splitByEquals[1]);
                case "format" -> format[0] = TextFormat.parseFormat(splitByEquals[1]);
            }
        }

        if (content[0] == null || color[0] == null || format[0] == null) {
            throw new IllegalArgumentException("At least one of the components is null");
        }

        return new RichString(content[0], color[0], format[0]);
    }

    /**
     * Serializes this object into a string.
     *
     * @return The string representation of this object
     */
    @Override
    @Nonnull
    public String toString() {
        return "RichString{" + "\n" +
                "  content='" + content + '\'' + "\n" +
                "  color=" + color + "\n" +
                "  format=" + format + "\n" +
                '}';
    }
}
