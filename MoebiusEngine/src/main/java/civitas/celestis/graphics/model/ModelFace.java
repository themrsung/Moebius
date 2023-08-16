package civitas.celestis.graphics.model;

import civitas.celestis.util.group.Group;
import civitas.celestis.util.tuple.Triple;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;

public class ModelFace extends Triple<Integer> {
    //
    // Constructors
    //

    public ModelFace(int a, int b, int c) {
        super(a, b, c);
    }

    public ModelFace(@Nonnull Iterable<Integer> i) {
        super(i);
    }

    public ModelFace(@Nonnull Group<Integer> g) {
        super(g);
    }

    public ModelFace(@Nonnull Tuple<Integer, ?> t) {
        super(t);
    }

    public ModelFace(@Nonnull Triple<Integer> t) {
        super(t);
    }
}
