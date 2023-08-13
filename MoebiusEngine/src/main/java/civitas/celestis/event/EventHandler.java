package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker annotation which marks a method as an event handler.
 *
 * @see Event
 * @see Listener
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns the priority of this handler.
     * <p>
     * While lower priority handlers are guaranteed to be called before
     * higher priority handlers, there is no set order between handlers of the
     * same priority.
     * </p>
     *
     * @return The priority of this handler
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;
}
