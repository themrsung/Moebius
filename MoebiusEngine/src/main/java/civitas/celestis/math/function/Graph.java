package civitas.celestis.math.function;

import jakarta.annotation.Nonnull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A mathematical graph
 *
 * @param <I> The input parameter (any object)
 * @param <O> The output parameter (a {@link Number})
 */
public class Graph<I, O extends Number> {
    //
    // Constructors
    //

    /**
     * Creates a new graph.
     *
     * @param function The function to graph
     */
    public Graph(@Nonnull MathFunction<I, O> function) {
        this.function = function;
    }

    /**
     * Creates a new graph.
     *
     * @param g The graph of which to copy the function of
     */
    public Graph(@Nonnull Graph<I, O> g) {
        this.function = g.function;
    }

    //
    // Variables
    //

    /**
     * The mathematical function to graph.
     */
    protected final MathFunction<I, O> function;

    //
    // Getters
    //

    /**
     * Returns the mathematical function this graph is using.
     *
     * @return The function of this graph
     */
    public MathFunction<I, O> function() {
        return function;
    }

    //
    // Methods
    //

    /**
     * Graphs this value into a {@link Map}.
     *
     * @param values The iterable object of values to graph
     * @return The graphed map
     */
    @Nonnull
    public Map<I, O> graph(@Nonnull Iterable<I> values) {
        final Map<I, O> result = new HashMap<>();

        for (final I value : values) {
            result.put(value, function.apply(value));
        }

        return result;
    }

    /**
     * Prints this graph.
     * Input the values to graph as an {@link Iterable} object,
     * and specify the printer function to use.
     * (e.g. {@link java.util.logging.Logger#info(String) Logger::info},
     * {@link java.io.PrintStream#println(Object) System.out::println})
     *
     * @param values  The iterable object of values to graph
     * @param printer The printer function to call
     */
    public void print(@Nonnull Iterable<I> values, @Nonnull Consumer<O> printer) {
        for (final I value : values) {
            printer.accept(function.apply(value));
        }
    }
}
