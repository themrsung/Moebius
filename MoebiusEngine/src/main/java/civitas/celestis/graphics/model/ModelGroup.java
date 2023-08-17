package civitas.celestis.graphics.model;

import civitas.celestis.util.tuple.ArrayTuple;
import civitas.celestis.util.tuple.Tuple;
import de.javagl.obj.ObjFace;
import de.javagl.obj.ObjGroup;
import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.function.Function;

/**
 * A group of faces of a model.
 *
 * @see ObjGroup
 */
public class ModelGroup extends ArrayTuple<ModelFace> {
    //
    // Constructors
    //

    /**
     * Creates a new model group.
     *
     * @param values The faces of this model group
     */
    public ModelGroup(@Nonnull ModelFace... values) {
        super(values);
    }

    /**
     * Creates a new model group.
     *
     * @param t The tuple of which to copy face references from
     */
    public ModelGroup(@Nonnull Tuple<ModelFace, ?> t) {
        super(t);
    }

    /**
     * Creates a new model group.
     *
     * @param t The tuple of which to copy face references from
     */
    public ModelGroup(@Nonnull ArrayTuple<ModelFace> t) {
        super(t);
    }

    /**
     * Creates a new model group.
     *
     * @param c The collection of which to copy face references from
     */
    public ModelGroup(@Nonnull Collection<ModelFace> c) {
        super(c);
    }

    /**
     * Creates a new model group from an {@link ObjGroup}.
     *
     * @param g The group of which to copy face references from
     * @param f The mapper function to use to translate face references
     */
    public ModelGroup(@Nonnull ObjGroup g, @Nonnull Function<? super ObjFace, ? extends ModelFace> f) {
        super(unpackObjGroup(g, f));
    }

    //
    // Internal
    //

    /**
     * Extracts the array of faces from an {@link ObjGroup}.
     *
     * @param g The group of which to extract faces from
     * @param f The mapper function to extract an {@link ObjFace} into
     *          a face of this group's type {@code F}
     * @return The extracted array of faces
     */
    @Nonnull
    private static ModelFace[] unpackObjGroup(
            @Nonnull ObjGroup g,
            @Nonnull Function<? super ObjFace, ? extends ModelFace> f
    ) {
        final ModelFace[] faces = new ModelFace[g.getNumFaces()];

        for (int i = 0; i < g.getNumFaces(); i++) {
            faces[i] = f.apply(g.getFace(i));
        }

        return faces;
    }
}
