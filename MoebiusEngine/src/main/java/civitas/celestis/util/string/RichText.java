package civitas.celestis.util.string;

import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * A mutable text object with multi-format support.
 */
public class RichText implements Serializable {
    //
    // Constructors
    //

    /**
     * Creates an empty rich text object.
     */
    public RichText() {
        this.components = new ArrayList<>();
    }

    /**
     * Creates a new rich text object.
     *
     * @param components The initial array of components to contain
     */
    public RichText(@Nonnull RichString... components) {
        this.components = new ArrayList<>(Arrays.asList(components));
    }

    /**
     * Creates a new rich text object.
     *
     * @param components The collection of components to use
     */
    public RichText(@Nonnull Collection<RichString> components) {
        this.components = new ArrayList<>(components);
    }


    //
    // Variables
    //

    @Nonnull
    private final List<RichString> components;

    //
    // Getters
    //

    /**
     * Returns a copied list of components which make up this text.
     *
     * @return A copied list of components
     */
    @Nonnull
    public List<RichString> getComponents() {
        return List.copyOf(components);
    }

    /**
     * Returns the {@code i}th component of this text.
     *
     * @param i The index of the component to get
     * @return The component of the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public RichString getComponent(int i) throws IndexOutOfBoundsException {
        return components.get(i);
    }

    /**
     * Returns the component count of this text.
     *
     * @return The number of components this text currently contains
     */
    public int getComponentCount() {
        return components.size();
    }

    /**
     * Returns the content of this text.
     *
     * @return The content of this text
     */
    @Nonnull
    public String getContent() {
        final StringBuilder builder = new StringBuilder();

        for (final RichString component : getComponents()) {
            builder.append(component.content());
        }

        return builder.toString();
    }

    //
    // Setters
    //

    /**
     * Sets the {@code i}th component to the given component.
     *
     * @param i         The index of component to set
     * @param component The component to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public void setComponent(int i, @Nonnull RichString component) throws IndexOutOfBoundsException {
        components.set(i, component);
    }

    /**
     * Adds a component to the end of this text.
     *
     * @param component The component to add to the end of this text
     */
    public void addComponent(@Nonnull RichString component) {
        components.add(component);
    }

    /**
     * Applies the given operator to the {@code i}th component of this text.
     *
     * @param i        The index of the component to modify
     * @param operator The operator to apply to the component
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public void modifyComponent(int i, @Nonnull UnaryOperator<RichString> operator) throws IndexOutOfBoundsException {
        components.set(i, operator.apply(components.get(i)));
    }

    /**
     * Applies the given operator to all components of this text.
     *
     * @param operator The operator to apply to each component of this text
     */
    public void modify(@Nonnull UnaryOperator<RichString> operator) {
        components.replaceAll(operator);
    }

    //
    // Properties
    //

    /**
     * Returns the length of this text.
     *
     * @return The sum of every component's length
     */
    public long length() {
        long length = 0;

        for (final RichString component : getComponents()) {
            length += component.length();
        }

        return length;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link RichText}.
     *
     * @param input The input string to parse
     * @return The parsed rich text object
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static RichText parseText(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RichText{")) {
            throw new IllegalArgumentException("Given string does not represent a RichText.");
        }

        final String cleanInput = StringFormatter.unindentString(input).replaceAll("RichText\\{\ncomponents=\\[\n\\{\n|}\n]\n}", "");
        final String[] componentStrings = cleanInput.split("\n},\n\\{\n");

        final List<RichString> components = new ArrayList<>();

        for (final String componentString : componentStrings) {
            components.add(RichString.parseString(componentString.trim()));
        }

        return new RichText(components);
    }

    /**
     * Serializes this text into a string.
     *
     * @return The string representation of this text
     */
    @Nonnull
    public String toString() {
        final StringBuilder out = new StringBuilder("RichText{\n");
        out.append("components=[\n");

        getComponents().forEach(component -> {
            out.append("{\n");
            out.append(component);
            out.append("\n");
            out.append("},\n");
        });

        // Remove last comma
        out.replace(out.length() - 2, out.length() - 1, "");

        out.append("]\n");
        out.append("}");

        return StringFormatter.indentString(out.toString());
    }
}
