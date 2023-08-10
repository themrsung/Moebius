package civitas.celestis.graphics.scene;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.graphics.util.GraphicsUtils;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.RaySource;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

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

        // Cache list of objects for consistency
        final List<BaseObject> objects = world.getObjects();

        // Add faces to scene
        for (final BaseObject object : objects) {
            faces.addAll(Arrays.asList(object.getFaces()));
        }

        // Handle lighting
        for (BaseObject object : objects) {
            if (!(object instanceof RaySource raySource)) continue;
            for (final Ray ray : raySource.generateRays()) {
                initiateRay(ray, 10);
            }
        }

        // Reset state
        state = State.IDLE;
    }

    /**
     * Initiates a ray sequence.
     *
     * @param ray   Ray to shoot
     * @param limit Maximum allowed number of recursions
     */
    private void initiateRay(@Nonnull Ray ray, long limit) {
        shootRay(ray, limit, null);
    }

    /**
     * Shoots a ray into this scene.
     *
     * @param ray    Ray to shoot
     * @param limit  Remaining number of recursions
     * @param source The source face
     */
    private void shootRay(@Nonnull Ray ray, long limit, @Nullable Face source) {
        for (final Face face : faces) {
            if (face.equals(source)) continue;

            final Vector3 intersection = GraphicsUtils.intersection(ray, face);
            if (intersection == null) continue;

            // AHHH HELP
        }
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
