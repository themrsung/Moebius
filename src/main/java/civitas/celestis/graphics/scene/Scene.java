package civitas.celestis.graphics.scene;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.object.BaseObject;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An object used to render worlds.
 */
public class Scene {
    //
    // Constructors
    //

    /**
     * Creates a new scene.
     *
     * @param world World to render
     */
    public Scene(@Nonnull World world) {
        this.world = world;
    }

    //
    // Variables
    //
    @Nonnull
    private World world;

    //
    // Properties
    //

    /**
     * Gets the world this scene is rendering.
     *
     * @return The world this scene is rendering
     */
    @Nonnull
    public World getWorld() {
        return world;
    }

    /**
     * Sets the world this scene should render.
     *
     * @param world The world this scene should render
     */
    public void setWorld(@Nonnull World world) {
        this.world = world;
    }

    //
    // Lifecycle
    //

    /**
     * Cleans this scene.
     */
    public void clean() {
        if (state != State.IDLE) return;

        // Mark state as cleaning
        state = State.CLEANING;

        // Clear faces
        faces.clear();

        // Reset state
        state = State.IDLE;
    }

    /**
     * Renders this scene.
     */
    public void render() {
        if (state != State.IDLE) return;

        // Mark state as rendering
        state = State.RENDERING;

        // Add faces to scene
        for (final BaseObject object : world.getObjects()) {
            faces.addAll(Arrays.asList(object.getFaces()));
        }

        // Handle lighting
        // TODO Implement light rays

        // Reset state
        state = State.IDLE;
    }

    private State state = State.IDLE;

    /**
     * Gets the current state of this scene.
     *
     * @return The state of this scene
     */
    @Nonnull
    public State getState() {
        return state;
    }

    /**
     * The state of a scene.
     */
    public enum State {
        IDLE,
        CLEANING,
        RENDERING
    }

    //
    // Output
    //

    @Nonnull
    private final List<Face> faces = new ArrayList<>();

    /**
     * Gets the rendered list of faces.
     *
     * @return List of rendered faces
     */
    @Nonnull
    public List<Face> getFaces() {
        return faces;
    }
}
