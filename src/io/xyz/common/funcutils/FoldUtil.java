package io.xyz.common.funcutils;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 *
 * @author t
 *
 */
public final class FoldUtil {

	public FoldUtil() {
	}

	public static int foldr(final IntBinaryOperator f, final int start, final int[] xs) {
		int cumulativeFold = start;
		for (int i = xs.length-1; i>-1; i--) {
			cumulativeFold = f.applyAsInt(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	public static double foldr(final DoubleBinaryOperator f, final double start, final double[] xs) {
		double cumulativeFold = start;
		for (int i = xs.length-1; i>-1; i--) {
			cumulativeFold = f.applyAsDouble(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	public static int foldl(final IntBinaryOperator f, final int start, final int[] xs) {
		int cumulativeFold = start;
		for (int i = 0; i<xs.length; i++) {
			cumulativeFold = f.applyAsInt(cumulativeFold, xs[i]);
		}
		return cumulativeFold;
	}

	public static double foldl(final DoubleBinaryOperator f, final double start, final double[] xs) {
		double cumulativeFold = start;
		for (int i = 0; i<xs.length; i++) {
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs[i]);
		}
		return cumulativeFold;
	}

	public static double accumulate(final DoubleBinaryOperator f, final double start, final double[] xs) {
		final int n = xs.length;
		double accumulation = start;
		for (int i = 0; i<n-1; i++) {
			accumulation += f.applyAsDouble(xs[i], xs[i+1]);
		}
		return accumulation;
	}

	public static int accumulate(final IntBinaryOperator f, final int start, final int[] xs) {
		final int n = xs.length;
		int accumulation = start;
		for (int i = 0; i<n-1; i++) {
			accumulation += f.applyAsInt(xs[i], xs[i+1]);
		}
		return accumulation;
	}
}
