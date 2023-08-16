package civitas.celestis.util.io;

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
            @Nonnull Function<T, String> serializer
    ) throws RuntimeException {
        try {
            return serializer.apply(obj);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
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
            @Nonnull Function<String, T> deserializer
    ) throws IOException {
        try {
            return deserializer.apply(str);
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }
}
