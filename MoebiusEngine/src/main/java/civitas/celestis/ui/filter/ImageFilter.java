package civitas.celestis.ui.filter;

import civitas.celestis.ui.image.RichImage;

import java.util.function.Consumer;

/**
 * A filter which can be applied to an image by calling {@link RichImage#apply(ImageFilter)}.
 */
public interface ImageFilter extends Consumer<RichImage> {
}
