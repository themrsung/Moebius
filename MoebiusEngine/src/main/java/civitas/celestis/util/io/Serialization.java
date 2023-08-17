package civitas.celestis.util.io;

import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.math.complex.Complex;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.vector.Double2;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.math.vector.Double4;
import civitas.celestis.util.group.Group;
import jakarta.annotation.Nonnull;

import java.io.IOException;
import java.io.Serializable;
import java.util.function.Function;

/**
 * Contains utilities related to the serialization of objects.
 */
public final class Serialization {
    //
    // Serialization
    //

    /**
     * Serializes an object into a string using {@link Object#toString()}.
     *
     * @param obj The object to serialize
     * @param <T> The type of object to serialize
     * @return The string representation of the provided object {@code obj}
     */
    @Nonnull
    public static <T extends Serializable> String serialize(@Nonnull T obj) {
        return obj.toString();
    }

    /**
     * Serializes an object into a string using a custom serializer function.
     *
     * @param obj        The object to serialize
     * @param serializer The serializer function
     * @param <T>        The type of object to serialize
     * @return The string representation of the provided object {@code obj}
     * @throws RuntimeException When an exception occurs during serialization
     *                          (the invoked exception will be wrapped in a {@link RuntimeException})
     */
    @Nonnull
    public static <T extends Serializable> String serialize(
            @Nonnull T obj,
            @Nonnull Function<? super T, String> serializer
    ) throws RuntimeException {
        try {
            return serializer.apply(obj);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes a group into a string. This method forcefully copies the components
     * of the provided group into an immutable copy, (which is achieved by {@link Group#copyOf(Group)})
     * then calls {@link Group#toString()}.
     * <p>
     *     While this operation guarantees successful serialization, any type-specific data
     *     will be lost. For example, serializing a {@link civitas.celestis.graphics.color.LinearColor LinearColor}
     *     using this method will return a different string
     *     from directly serializing it using {@link LinearColor#toString()}.
     * </p>
     * @param g The group to serialize into a string
     * @return The string representation of the provided group {@code g}.
     * @param <E> The type of element the group is holding
     */
    @Nonnull
    public static <E> String serializeGroup(@Nonnull Group<E> g) {
        return Group.copyOf(g).toString();
    }

    /**
     * Deserializes a string into an object.
     *
     * @param str          The string to deserialize
     * @param deserializer The deserializer function
     * @param <T>          The type of object to deserialize
     * @return The deserialized object
     * @throws IOException When an exception occurs in the process of deserialization
     *                     (the invoked exception will be wrapped in an {@link IOException})
     */
    @Nonnull
    public static <T extends Serializable> T deserialize(
            @Nonnull String str,
            @Nonnull Function<String, ? extends T> deserializer
    ) throws IOException {
        try {
            return deserializer.apply(str);
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }

//    @Nonnull
//    public static <E> Group<E> deserialzeGroup(@Nonnull String str) {
//
//    }

    @Nonnull
    public static Group<Double> deserializeDoubles(@Nonnull String str) throws IOException {
        try {return Double2.parseVector(str);} catch (final Exception ignored) {}
        try {return Double3.parseVector(str);} catch (final Exception ignored) {}
        try {return Double4.parseVector(str);} catch (final Exception ignored) {}

        try {return Complex.parseComplex(str);} catch (final Exception ignored) {}
        try {return Quaternion.parseQuaternion(str);} catch (final Exception ignored) {}

        throw new IOException("The provided string is not a group of type double.");
    }
}
