package civitas.celestis.util.array;

import jakarta.annotation.Nonnull;

/**
 * An interface for classes which contain multiple elements which can be
 * safely treated as a single type.
 * @param <E> The type of element this class contains
 */
public interface TypeSafe<E> {
    /**
     * Converts this object into a type-safe array.
     * @return The type-safe array representation of this object
     */
    @Nonnull
    SafeArray<E> safeArray();
}
