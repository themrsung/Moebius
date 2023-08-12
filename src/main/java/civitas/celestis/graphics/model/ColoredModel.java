package civitas.celestis.graphics.model;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.face.ColoredFace;
import civitas.celestis.math.vertex.Vertex;
import civitas.celestis.util.string.StringFormatter;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

/**
 * A model which uses single-color faces.
 */
public class ColoredModel implements Model {
    //
    // Constructors
    //

    /**
     * Creates a new colored model.
     *
     * @param faces The collection of faces to create this model from
     */
    public ColoredModel(@Nonnull List<ColoredFace> faces) {
        this.faces = List.copyOf(faces);
    }

    /**
     * Creates a new colored model.
     *
     * @param obj   The {@link Obj} object to copy vertices from
     * @param color The initial color of all faces
     * @param scale The scale to apply to the input model
     */
    public ColoredModel(@Nonnull Obj obj, @Nonnull RichColor color, double scale) {
        final List<ColoredFace> tempFaces = new ArrayList<>(obj.getNumFaces());

        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace face = obj.getFace(i);

            final Vertex a = Vertex.fromObjTuple(obj.getVertex(face.getVertexIndex(0)));
            final Vertex b = Vertex.fromObjTuple(obj.getVertex(face.getVertexIndex(1)));
            final Vertex c = Vertex.fromObjTuple(obj.getVertex(face.getVertexIndex(2)));

            tempFaces.add(new ColoredFace(a, b, c, color).apply(v -> v.multiply(scale)));
        }

        this.faces = List.copyOf(tempFaces);
    }

    //
    // Variables
    //

    @Nonnull
    private final List<ColoredFace> faces;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return The list of all faces of this model
     */
    @Override
    @Nonnull
    public List<ColoredFace> getFaces() {
        return faces;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the face to get
     * @return The face of specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public ColoredFace getFace(int i) throws IndexOutOfBoundsException {
        return faces.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return The number of faces this model has
     */
    @Override
    public int getFaceCount() {
        return faces.size();
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a colored model.
     *
     * @param input The input string
     * @return The parsed model
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static ColoredModel parseModel(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("ColoredModel{")) {
            throw new IllegalArgumentException("Given string does not represent a ColoredModel.");
        }

        final String cleanInput = StringFormatter.unindentString(input).replaceAll("ColoredModel\\{\nfaces=\\[\n\\{\n|}\n]\n}", "");
        final String[] faceStrings = cleanInput.split("\n},\n\\{\n");

        final List<ColoredFace> faces = new ArrayList<>();

        for (final String faceString : faceStrings) {
            faces.add(ColoredFace.parseFace(faceString.trim()));
        }

        return new ColoredModel(faces);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this model
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder out = new StringBuilder("ColoredModel{\n");
        out.append("faces=[\n");

        faces.forEach(face -> {
            out.append("{\n");
            out.append(face);
            out.append("\n");
            out.append("},\n");
        });

        // Remove last comma
        out.replace(out.length() - 2, out.length() - 1, "");

        out.append("]\n");
        out.append("}");

        return StringFormatter.indentString(out.toString());
    }
}
