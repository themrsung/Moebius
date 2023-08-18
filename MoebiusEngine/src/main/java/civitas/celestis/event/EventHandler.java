package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.*;

/**
 * A marker annotation which marks a method as an event handler.
 *
 * @see Handleable
 * @see Event
 * @see Listener
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns the priority of this event handler. Defaults to {@link HandlerPriority#MEDIUM}.
     *
     * @return The priority of this event handler
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.MEDIUM;
}
