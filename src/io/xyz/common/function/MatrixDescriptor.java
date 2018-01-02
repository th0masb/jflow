/**
 *
 */
package io.xyz.common.function;

import static io.xyz.common.funcutils.PrimitiveUtil.cos;
import static io.xyz.common.funcutils.PrimitiveUtil.sin;

import java.util.Random;

/**
 * @author t
 *
 */
public interface MatrixDescriptor extends BiIntToDoubleFunction {

	static Random R = new Random(0x110894L);

	static MatrixDescriptor identity() {
		return (i, j) -> i == j? 1 : 0;
	}

	static MatrixDescriptor constant(final double value) {
		return (i, j) -> value;
	}

	static MatrixDescriptor rand(final double upper) {
		return (i, j) -> (2 * R.nextInt(2) - 1) * upper * R.nextDouble();
	}

	static MatrixDescriptor randInt(final int upper) {
		return (i, j) -> (2 * R.nextInt(2) - 1) * upper * R.nextInt(upper);
	}

	static MatrixDescriptor rotate2D(final double radians) {
		final double[] flattened = { cos(radians), -sin(radians), sin(radians), cos(radians) };
		return (i, j) -> flattened[i + 2 * j];
	}
}
