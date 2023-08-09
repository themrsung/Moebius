package civitas.celestis.world.lifecycle;

import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;

/**
 * A synchronous world manager with one thread ticking worlds.
 */
public final class SyncWorldManager implements WorldManager {
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Nonnull
    @Override
    public List<World> getWorlds() {
        return List.copyOf(worlds);
    }

    @Nonnull
    @Override
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final World world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    @Nullable
    @Override
    public World getWorld(@Nonnull String name) {
        for (final World world : getWorlds()) {
            if (world.getName().equals(name)) return world;
        }

        return null;
    }

    @Override
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
        times.put(world, System.currentTimeMillis());
    }

    @Override
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
        times.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();
    private final Map<World, Long> times = new HashMap<>();
    private final Thread thread = new Thread(() -> {
        while (true) {
            if (Thread.interrupted()) return;

            for (final World world : List.copyOf(worlds)) {
                final long now = System.currentTimeMillis();
                final long previous = times.getOrDefault(world, now);
                final long delta = now - previous;

                // Worlds and objects are time-sensitive
                if (delta < 1) continue;

                world.tick(delta);
                times.put(world, now);
            }
        }
    }, "SyncWorldManager");
}
