package civitas.celestis.ui;

import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.ui.component.UIComponent;
import civitas.celestis.ui.event.component.ComponentAddedEvent;
import civitas.celestis.ui.event.component.ComponentRemovedEvent;
import civitas.celestis.ui.image.RichImage;
import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;

/**
 * A frame which can contain UI components.
 * A frame has a single {@link RichImage}, which it renders every frame.
 */
public class UIFrame extends JFrame {
    //
    // Constants
    //

    /**
     * The initial size of the output image.
     * Changing this value does not affect the lifecycle of UI frames,
     * since this value is only used in the constructor to prevent an exception.
     */
    public static final IntVector2 DEFAULT_IMAGE_SIZE = new IntVector2(1920, 1080);

    //
    // Constructors
    //

    /**
     * Creates a new UI frame.
     */
    public UIFrame() {
        this.image = new RichImage(DEFAULT_IMAGE_SIZE);
        this.container = new UIContainer(this);
    }

    /**
     * Creates a new UI frame.
     *
     * @param title The title of this frame
     */
    public UIFrame(@Nonnull String title) {
        super(title);

        this.image = new RichImage(DEFAULT_IMAGE_SIZE);
        this.container = new UIContainer(this);
    }

    //
    // Variables
    //

    /**
     * The image to render to the screen.
     */
    @Nonnull
    protected RichImage image;

    /**
     * The container object which contains the UI components.
     */
    @Nonnull
    protected final UIContainer container;

    //
    // Getters
    //

    /**
     * Returns the container of this frame.
     *
     * @return The {@link UIContainer} instance of this frame
     */
    @Nonnull
    public UIContainer getContainer() {
        return container;
    }

    //
    // Methods
    //

    /**
     * Handles painting the rendered image to the screen.
     *
     * @param g The {@link Graphics} object to use to render the image
     */
    @Override
    public final void paint(@Nonnull Graphics g) {
        resizeImage(); // Resize image if the frame's dimensions have been changed

        // Call every component's render method
        for (final UIComponent component : container.getComponents()) {
            component.render(image);
        }

        g.drawImage(image, 0, 0, null); // Draw the image
        g.dispose(); // Dispose the graphics object

        image.getGraphics().dispose(); // Dispose the image's graphics object
    }

    /**
     * Resizes the image to match the frame's dimensions if necessary.
     * If the image is resized, all rendered data will be cleared.
     */
    protected final void resizeImage() {
        if (getWidth() == image.getWidth() && getHeight() == image.getHeight()) {
            return; // No need to resize
        }

        image = new RichImage(getWidth(), getHeight());
    }

    //
    // Events
    //

    /**
     * Called when a component has been added to the container of this frame.
     *
     * @param event The component event
     */
    public void onComponentAdded(@Nonnull ComponentAddedEvent event) {}

    /**
     * Called when a component has been removed from the container of this frame.
     *
     * @param event The component event
     */
    public void onComponentRemoved(@Nonnull ComponentRemovedEvent event) {}
}
