package civitas.celestis.annotation;

import jakarta.annotation.Nonnull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A marker interface used to mark various fields and methods which are
 * application-critical.
 * <p>
 * Being application-critical means the usage of this field or method will
 * result in the control flow of the main thread of the application being altered.
 * If you encounter a field which is annotated as application-critical,
 * double check if you are using it properly.
 * </p>
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ApplicationCritical {
    /**
     * The version of which this field was most recently updated in.
     *
     * @return The most recent version this field was modified in
     */
    @Nonnull
    String lastUpdated() default "";
}
