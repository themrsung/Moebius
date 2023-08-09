package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a method as an event handler.
 * @see Listener
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * Gets the priority of this event handler.
     * @return The priority of this event handler
     * @see HandlerPriority
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;
}
