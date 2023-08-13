package civitas.celestis.ui.filter;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Interpolation;
import civitas.celestis.math.Numbers;
import civitas.celestis.ui.image.RichImage;
import jakarta.annotation.Nonnull;

/**
 * A filter which induces an arbitrary amount of noise to the image.
 */
public class NoiseFilter implements ImageFilter {
    //
    // Constants
    //

    /**
     * A filter which induces a low amount of noise. The image is still visually pleasant.
     */
    public static final NoiseFilter LOW_NOISE = new NoiseFilter(0.1);

    /**
     * A filter which induces a medium amount of noise. The image is still distinguishable from random noise.
     */
    public static final NoiseFilter MEDIUM_NOISE = new NoiseFilter(0.25);

    /**
     * A filter which induces a high amount of noise. The image is hardly distinguishable from random noise.
     */
    public static final NoiseFilter HIGH_NOISE = new NoiseFilter(0.65);

    //
    // Constructors
    //

    /**
     * Creates a new noise filter.
     * @param intensity The intensity of this filter within a range of {@code 0-1}
     */
    public NoiseFilter(double intensity) {
        this.intensity = Numbers.requireRange(intensity, 0, 1);
    }

    //
    // Randomization
    //

    /**
     * Returns a noise filter with random intensity.
     * @return A random intensity noise filter
     */
    @Nonnull
    public static NoiseFilter random() {
        return new NoiseFilter(Math.random());
    }

    //
    // Variables
    //
    private final double intensity;

    //
    // Methods
    //

    /**
     * Applies this filter to the given image.
     * @param image The image to apply this filter to
     */
    @Override
    public void accept(@Nonnull RichImage image) {
        image.modify(c -> Interpolation.lerp(c, RichColor.random(), intensity));
    }
}
