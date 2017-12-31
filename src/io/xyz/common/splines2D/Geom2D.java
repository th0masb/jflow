/**
 *
 */
package io.xyz.common.splines2D;

/**
 * @author t
 *
 */
public final class Geom2D {
	private Geom2D() {
	}

	// public static double gradOfUnitVector(final RPoint p) {
	// assert p.magnitude() == 1;
	// if (allEqual(p.x(), 0)) {
	// return (p.y() > 0? 1 : -1) * INFINITY;
	// }
	// return
	// }

	public static void main(final String[] args) {
		final double x = Double.POSITIVE_INFINITY;
		System.out.println(x);
		System.out.println(-1 / 0);
	}
}
