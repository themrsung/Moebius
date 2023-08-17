package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Double3;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import jakarta.annotation.Nonnull;

import java.util.function.Function;

/**
 * A three-dimensional graphical model which uses 64-bit double precision vertices.
 */
public class Model64 extends AbstractModel<Double3> {
    //
    // Constants
    //

    /**
     * The vertex mapper function.
     */
    protected static final Function<? super FloatTuple, Double3> VERTEX_MAPPER = ft -> switch (ft.getDimensions()) {
        case 1 -> new Double3(ft.get(0), 0, 0);
        case 2 -> new Double3(ft.get(0), ft.get(1), 0);

        // Invert the X and Y components to translate between coordinate systems
        default -> new Double3(ft.getZ(), ft.getY(), ft.getX());
    };

    //
    // Constructors
    //

    /**
     * Creates a new model.
     *
     * @param obj The {@link Obj} object to copy data from
     */
    public Model64(@Nonnull Obj obj) {
        super(obj, VERTEX_MAPPER, ModelFace::new, ModelGroup::new);
    }
}
