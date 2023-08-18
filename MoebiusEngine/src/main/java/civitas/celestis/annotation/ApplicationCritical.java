package civitas.celestis.annotation;

import jakarta.annotation.Nonnull;

import java.lang.annotation.*;

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
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({

        /*
         * Do not add TYPE.
         * Maintaining last version fields for classes is both ambiguous and tedious.
         */

        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.CONSTRUCTOR,
        ElementType.ANNOTATION_TYPE,
        ElementType.PACKAGE,
        ElementType.RECORD_COMPONENT
})
@ApplicationCritical(created = "0.3", lastUpdated = "0.3")
public @interface ApplicationCritical {
    /**
     * The version of which this field was most recently updated in.
     *
     * @return The most recent version this field was modified in
     */
    @Nonnull
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    String lastUpdated() default "";

    /**
     * The version of which this field was created in.
     *
     * @return The version this field was created in
     */
    @Nonnull
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    String created() default "";
}
