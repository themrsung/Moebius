package civitas.celestis.graphics.util;

import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * The position of an object on a two-dimensional plane.
 * This is used to designate the coordinates of an object to render on-screen.
 */
public class Position extends Vector2 {
    //
    // Constants
    //

    /**
     * Represents origin.
     */
    public static final Position ZERO = new Position(0, 0);

    /**
     * The unit position which corresponds to positive X.
     */
    public static final Position RIGHT = new Position(1, 0);

    /**
     * The unit position which corresponds to positive Y.
     */
    public static final Position UP = new Position(0, 1);

    /**
     * The unit position which corresponds to negative X.
     */
    public static final Position LEFT = new Position(-1, 0);

    /**
     * The unit position which corresponds to negative Y.
     */
    public static final Position DOWN = new Position(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new position.
     *
     * @param x X coordinate of this position
     * @param y Y coordinate of this position
     */
    public Position(double x, double y) {
        super(x, y);
    }

    /**
     * Creates a new position.
     *
     * @param values An array of {@code double}s containing the coordinates
     */
    public Position(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new position from a {@link Vector2}.
     *
     * @param other Vector to copy values from
     */
    public Position(@Nonnull Vector2 other) {
        super(other);
    }

    //
    // Getters
    //

    /**
     * Returns the integer value of the X coordinate.
     *
     * @return Integer value of X
     */
    public int xInt() {
        return (int) x();
    }

    /**
     * Returns the integer value of the Y coordinate.
     *
     * @return Integer value of Y
     */
    public int yInt() {
        return (int) y();
    }

    /**
     * Checks if the coordinates of this position can be safely converted into integers.
     *
     * @return {@code true} if both X and Y coordinates are int-safe
     */
    public boolean isIntSafe() {
        final double x = x();
        final double y = y();

        return ((x < Integer.MAX_VALUE && x > -Integer.MAX_VALUE) && (y < Integer.MAX_VALUE && y > -Integer.MAX_VALUE));
    }

    /**
     * Checks if this position is on-screen.
     *
     * @param minPos Minimum boundary of the screen
     * @param maxPos Maximum boundary of the screen
     * @return {@code true} if this position is on-screen
     */
    public boolean isOnScreen(@Nonnull Position minPos, @Nonnull Position maxPos) {
        return clamp(minPos, maxPos).equals(this);
    }

    /**
     * Checks if this position is on-screen.
     *
     * @param minX The minimum X coordinate
     * @param minY The minimum Y coordinate
     * @param maxX The maximum X coordinate
     * @param maxY The maximum Y coordinate
     * @return {@code true} if this position is on-screen
     */
    public boolean isOnScreen(int minX, int minY, int maxX, int maxY) {
        if (!isIntSafe()) return false;

        final int x = xInt();
        final int y = yInt();

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    //
    // Setters
    //

    /**
     * Changes only the X coordinate of this position.
     *
     * @param x The new value to set the X component to
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position x(double x) {
        return new Position(super.x(x));
    }

    /**
     * Changes only the Y coordinate of this position.
     *
     * @param y The new value to set the Y component to
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position y(double y) {
        return new Position(super.y(y));
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this position.
     *
     * @param s Scalar to add to this position
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position add(double s) {
        return new Position(super.add(s));
    }

    /**
     * Subtracts a scalar from this position.
     *
     * @param s Scalar to subtract from this position
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position subtract(double s) {
        return new Position(super.subtract(s));
    }

    /**
     * Scales this position.
     *
     * @param s Scalar to multiply with this position
     * @return The scaled position
     */
    @Nonnull
    @Override
    public Position multiply(double s) {
        return new Position(super.multiply(s));
    }

    /**
     * Divides this position by a scalar.
     *
     * @param s Scalar to divide this position by
     * @return The resulting position
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Position divide(double s) throws ArithmeticException {
        return new Position(super.divide(s));
    }

    /**
     * Adds a vector to this position.
     *
     * @param v Vector to add to this position
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position add(@Nonnull Vector2 v) {
        return new Position(super.add(v));
    }

    /**
     * Subtracts a vector from this position.
     *
     * @param v Vector to subtract from this position
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position subtract(@Nonnull Vector2 v) {
        return new Position(super.subtract(v));
    }

    /**
     * Multiplies this position by a vector.
     *
     * @param v Vector to multiply this position by
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position multiply(@Nonnull Vector2 v) {
        return new Position(super.multiply(v));
    }

    //
    // Utility
    //

    /**
     * Given an array of positions, this returns the sum of the positions.
     *
     * @param positions Positions to sum
     * @return The sum of the given positions
     */
    @Nonnull
    public static Position sum(@Nonnull Position... positions) {
        return new Position(Vector2.sum(positions));
    }

    /**
     * Given an array of positions, this returns the average of the positions.
     *
     * @param positions Positions to average
     * @return The average of the given positions
     */
    @Nonnull
    public static Position avg(@Nonnull Position... positions) {
        return new Position(Vector2.avg(positions));
    }

    /**
     * Applies given operator to all components of this position.
     *
     * @param operator The operator to apply to all components of this position
     * @return The resulting position
     */
    @Nonnull
    @Override
    public Position apply(@Nonnull UnaryOperator<Double> operator) {
        return new Position(super.apply(operator));
    }

    /**
     * Negates this position.
     *
     * @return The negated position
     */
    @Nonnull
    @Override
    public Position negate() {
        return new Position(super.negate());
    }

    /**
     * Negates the X coordinate of this position.
     *
     * @return The negated position
     */
    @Nonnull
    @Override
    public Position negateX() {
        return new Position(super.negateX());
    }

    /**
     * Negates the Y coordinate of this position.
     *
     * @return The negated position
     */
    @Nonnull
    @Override
    public Position negateY() {
        return new Position(super.negateY());
    }

    /**
     * Returns the normalizes position of this position.
     * If the magnitude of this position is zero, this returns {@link Position#ZERO}.
     *
     * @return The normalized position of this position
     */
    @Nonnull
    @Override
    public Position normalize() {
        return new Position(super.normalize());
    }

    /**
     * {@inheritDoc}
     *
     * @param v Vector to get the maximum between
     * @return The maximum position
     */
    @Nonnull
    @Override
    public Position max(@Nonnull Vector2 v) {
        return new Position(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v Vector to get the minimum between
     * @return The minimum position
     */
    @Nonnull
    @Override
    public Position min(@Nonnull Vector2 v) {
        return new Position(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min Minimum allowed boundaries
     * @param max Maximum allowed boundaries
     * @return The clamped position
     */
    @Nonnull
    @Override
    public Position clamp(@Nonnull Vector2 min, @Nonnull Vector2 max) {
        return new Position(super.clamp(min, max));
    }

    /**
     * Rotates this position counter-clockwise by given angle.
     *
     * @param angle Angle to rotate this vector by in radians
     * @return The rotated position
     */
    @Nonnull
    @Override
    public Position rotate(double angle) {
        return new Position(super.rotate(angle));
    }

    /**
     * Checks for equality between this position and given object
     *
     * @param obj Object to compare this position to
     * @return {@code true} if the object is a position, and the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position p)) return false;
        return x() == p.x() && y() == p.y();
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Position}.
     *
     * @param s String to parse into a position
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Position parsePosition(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Position\\{|}", "").split(", ");
        final double[] values = new double[2];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2)
                throw new NumberFormatException("The provided string does not represent a Position.");

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Position.");
            }] = Double.parseDouble(split[1]);
        }

        return new Position(values);
    }

    /**
     * Serializes this {@link Position} into a string.
     *
     * @return The string representation of this position
     */
    @Override
    @Nonnull
    public String toString() {
        return "Position{" +
                "x=" + x() +
                ", y=" + y() +
                '}';
    }
}
