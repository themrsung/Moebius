package civitas.celestis;

import civitas.celestis.math.vector.*;
import civitas.celestis.util.group.Group;

import java.util.Objects;

import static java.lang.System.*;

public class Test2 {
    public static void main(String[] args) {
        // Testing around vector conversion
        final Double3 v1 = new Double3(123, 440, 22);
        out.println(v1);

        final Long3 v2 = new Long3(v1);
        out.println(v2);

        final Int3 v3 = new Int3(v2);
        out.println(v3);

        final Float3 v4 = new Float3(v3);
        out.println(v4);

        final Decimal3 v5 = new Decimal3(v4);
        out.println(v5);

        final Integer3 v6 = v5.toBigInteger();
        out.println(v6);

        final Decimal3 v7 = new Decimal3(v6);
        out.println(v7);

        final Double3 v8 = v7.toDouble();
        out.println(v8);

        final Group<Vector<?, ?>> group = Group.of(v1, v2, v3, v4, v5, v6, v7, v8);
        out.println(group);
    }
}
