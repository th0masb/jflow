/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.FoldUtil.foldr;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;

/**
 * @author t
 *
 */
public final class PrimitiveUtil {
	private PrimitiveUtil() {
	}

	public static boolean isZero(final int x) {
		return x == 0;
	}

	public static boolean isZero(final double x) {
		return Math.abs(x) < EPSILON;
	}

	public static int digitLength(int n) {
		int dlen = 1;
		n /= 10;
		while (!isZero(n)) {
			n /= 10;
			dlen++;
		}
		return dlen;
	}

	public static int digitLength(final int decimalplaces, double d) {
		int dlen = 1 + decimalplaces;
		d /= 10;
		while (Math.abs(d) > 1) {
			d /= 10;
			dlen++;
		}
		return dlen;
	}

	public static <T> boolean isNull(final T t) {
		return t == null;
	}

	public static OptionalDouble sum(final double... xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static OptionalDouble sum(final DoubleRangeDescriptor xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static OptionalInt sum(final int... xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static OptionalInt sum(final IntRangeDescriptor xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static OptionalDouble product(final double... xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static OptionalDouble product(final DoubleRangeDescriptor xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static OptionalInt product(final int... xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static OptionalInt product(final IntRangeDescriptor xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static int min(final int a, final int b) {
		return a < b? a : b;
	}

	public static OptionalInt min(final int... xs) {
		return foldr(PrimitiveUtil::min, Integer.MAX_VALUE, xs);
	}

	public static OptionalInt min(final IntRangeDescriptor xs) {
		return foldr(PrimitiveUtil::min, Integer.MAX_VALUE, xs);
	}

	public static double min(final double a, final double b) {
		return a < b? a : b;
	}

	public static OptionalDouble min(final double... xs) {
		return foldr(PrimitiveUtil::min, Double.MAX_VALUE, xs);
	}

	public static OptionalDouble min(final DoubleRangeDescriptor xs) {
		return foldr(PrimitiveUtil::min, Double.MAX_VALUE, xs);
	}

	public static int max(final int a, final int b) {
		return a < b? b : a;
	}

	public static OptionalInt max(final int... xs) {
		return foldr(PrimitiveUtil::max, Integer.MIN_VALUE, xs);
	}

	public static OptionalInt max(final IntRangeDescriptor xs) {
		return foldr(PrimitiveUtil::max, Integer.MAX_VALUE, xs);
	}

	public static double max(final double a, final double b) {
		return a < b? b : a;
	}

	public static OptionalDouble max(final double... xs) {
		return foldr(PrimitiveUtil::max, Double.MIN_VALUE, xs);
	}

	public static OptionalDouble max(final DoubleRangeDescriptor xs) {
		return foldr(PrimitiveUtil::max, Double.MAX_VALUE, xs);
	}

	public static double pow(final double x, final double power) {
		return Math.pow(x, power);
	}

	public static double sqrt(final double x) {
		return Math.sqrt(x);
	}

	public static double square(final double x) {
		return x * x;
	}

	public static int square(final int x) {
		return x * x;
	}

	public static double abs(final double x) {
		return Math.abs(x);
	}

	public static int abs(final int x) {
		return Math.abs(x);
	}

	public static double signum(final double x) {
		return Math.signum(x);
	}

	public static int signum(final int x) {
		return (int) Math.signum(x);
	}

	public static void main(final String[] args) {
		System.out.println(digitLength(2));// , 134423.3245523));

		System.out.println(max(2, 4, -2, 6, 7));
	}
}
