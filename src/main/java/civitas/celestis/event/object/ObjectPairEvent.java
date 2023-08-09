package civitas.celestis.event.object;

import civitas.celestis.event.Event;
import civitas.celestis.object.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * An event involving a pair of objects.
 */
public abstract class ObjectPairEvent implements Event {
    /**
     * Creates a new object pair event.
     *
     * @param objects The pair of objects involved in this event.
     */
    public ObjectPairEvent(@Nonnull Pair<BaseObject> objects) {
        this.objects = objects;
    }

    @Nonnull
    private final Pair<BaseObject> objects;

    /**
     * Gets the pair of events involved in this event.
     *
     * @return The pair of objects involved in this event
     */
    @Nonnull
    public Pair<BaseObject> getObjects() {
        return objects;
    }
}
