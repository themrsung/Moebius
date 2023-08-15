package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker interface which marks a method as an event handler method.
 *
 * @see Event
 * @see Listener
 * @see HandlerPriority
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns the priority of this event handler.
     * The default priority is {@link HandlerPriority#MEDIUM}.
     *
     * @return The priority of this event handler
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.MEDIUM;
}
