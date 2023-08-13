package civitas.celestis.exception.math;

import jakarta.annotation.Nonnull;

/**
 * An exception which is thrown when a floating point number cannot be safely cast to an {@code int}.
 */
public class NotIntSafeException extends IllegalArgumentException {
    /**
     * Creates a new exception.
     */
    public NotIntSafeException() {
    }

    /**
     * Creates a new exception with a message.
     *
     * @param s The message of the exception
     */
    public NotIntSafeException(@Nonnull String s) {
        super(s);
    }

    /**
     * Creates a new exception with a message and a cause.
     *
     * @param message The message of the exception
     * @param cause   The cause of the exception
     */
    public NotIntSafeException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception with a cause.
     *
     * @param cause The cause of the exception
     */
    public NotIntSafeException(@Nonnull Throwable cause) {
        super(cause);
    }
}
