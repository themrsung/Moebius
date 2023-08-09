package civitas.celestis.event.object;

import civitas.celestis.event.Cancellable;
import civitas.celestis.object.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * This event is called when two objects overlap for the first time.
 */
public final class ObjectsCollidedEvent extends ObjectPairEvent implements Cancellable {
    /**
     * Creates a new object collision event.
     *
     * @param objects The pair of objects which collided
     */
    public ObjectsCollidedEvent(@Nonnull Pair<BaseObject> objects) {
        super(objects);
    }

    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
