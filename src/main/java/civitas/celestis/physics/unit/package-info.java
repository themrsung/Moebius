/**
 * <p>
 * This package contains unit conversion utilities.
 * Note that due to the floating point nature of {@code double},
 * almost all unit conversions are lossy. (Powers of 10 are lossless)
 * </p>
 * <p>
 * All physics calculations assume that the input parameters are denoted in SI standard units.
 * SI standard units are:
 * </p>
 * <ul>
 *     <li>Length: meters (m)</li>
 *     <li>Mass: kilograms (kg)</li>
 *     <li>Speed: meters per second (m/s)</li>
 *     <li>Acceleration: meters per second squared (m/s2)</li>
 *     <li>Energy: Joules (J)</li>
 *     <li>Density: kilograms per cubic meter (kg/m3)</li>
 * </ul>
 * Other standards used in Moebius include:
 * <ul>
 *     <li>Angle of rotation: radians</li>
 *     <li>Rate of rotation: radians per second</li>
 *     <li>Direction of rotation: right-hand rule</li>
 * </ul>
 */
package civitas.celestis.physics.unit;