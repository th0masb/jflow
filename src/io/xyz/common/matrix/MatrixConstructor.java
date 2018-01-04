/**
 *
 */
package io.xyz.common.matrix;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.Random;

import io.xyz.common.function.BiIntToDoubleFunction;

/**
 * @author t
 *
 */
public interface MatrixConstructor extends BiIntToDoubleFunction {

	static Random R = new Random(0x110894L);

	static MatrixConstructor identity() {
		return (i, j) -> i == j? 1 : 0;
	}

	static MatrixConstructor constant(final double value) {
		return (i, j) -> value;
	}

	static MatrixConstructor rand(final double upper) {
		return (i, j) -> (2 * R.nextInt(2) - 1) * upper * R.nextDouble();
	}

	static MatrixConstructor randInt(final int upper) {
		return (i, j) -> (2 * R.nextInt(2) - 1) * upper * R.nextInt(upper);
	}

	static MatrixConstructor rotate2D(final double radians) {
		final double[] flattened = { cos(radians), -sin(radians), sin(radians), cos(radians) };
		return (i, j) -> flattened[i + 2 * j];
	}
}
