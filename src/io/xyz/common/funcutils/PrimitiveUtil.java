/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.FoldUtil.foldr;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToDoubleIP;
import static io.xyz.common.geometry.Constants.EPSILON;

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
		return abs(x) < EPSILON;
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
		while (abs(d) > 1) {
			d /= 10;
			dlen++;
		}
		return dlen;
	}

	public static <T> boolean isNull(final T t) {
		return t == null;
	}

	public static double sum(final double... xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static int sum(final int... xs) {
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static double product(final double... xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static int product(final int... xs) {
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static int min(final int a, final int b) {
		return a < b? a : b;
	}

	public static int min(final int... xs) {
		return foldr((a, b) -> min(a, b), Integer.MAX_VALUE, xs);
	}

	public static double min(final double a, final double b) {
		return a < b? a : b;
	}

	public static double min(final double... xs) {
		return foldr((a, b) -> min(a, b), Double.MAX_VALUE, xs);
	}

	public static int max(final int a, final int b) {
		return a < b? b : a;
	}

	public static int max(final int... xs) {
		return foldr((a, b) -> max(a, b), Integer.MIN_VALUE, xs);
	}

	public static double max(final double a, final double b) {
		return a < b? b : a;
	}

	public static double max(final double... xs) {
		return foldr((a, b) -> max(a, b), Double.MIN_VALUE, xs);
	}

	public static double[] pow(final double power, final double... xs) {
		return map(x -> Math.pow(x, power), xs);
	}

	public static double pow(final double power, final double x) {
		return Math.pow(x, power);
	}

	public static double[] pow(final double power, final int... xs) {
		return mapToDoubleIP(x -> Math.pow(x, power), xs);
	}

	public static double[] sqrt(final double... xs) {
		return map(x -> Math.sqrt(x), xs);
	}

	public static double sqrt(final double x) {
		return Math.sqrt(x);
	}

	public static double[] sqrt(final int... xs) {
		return mapToDoubleIP(x -> sqrt(x), xs);
	}

	public static double[] square(final double... xs) {
		return map(x -> x * x, xs);
	}

	public static double square(final double x) {
		return x * x;
	}

	public static int square(final int x) {
		return x * x;
	}

	public static int[] square(final int... xs) {
		return map(x -> x * x, xs);
	}

	public static double abs(final double x) {
		return Math.abs(x);
	}

	public static double[] abs(final double... xs) {
		return map(x -> abs(x), xs);
	}

	public static int abs(final int x) {
		return Math.abs(x);
	}

	public static int[] abs(final int... xs) {
		return map(x -> abs(x), xs);
	}

	public static double signum(final double x) {
		return Math.signum(x);
	}

	public static double[] signum(final double... xs) {
		return map(x -> signum(x), xs);
	}

	public static int signum(final int x) {
		return (int) Math.signum(x);
	}

	public static int[] signum(final int... xs) {
		return map(x -> signum(x), xs);
	}

	public static double cos(final double x) {
		return Math.cos(x);
	}

	public static double[] cos(final double... xs) {
		return map(x -> cos(x), xs);
	}

	public static double[] cos(final int... xs) {
		return mapToDoubleIP(x -> cos(x), xs);
	}

	public static double sin(final double x) {
		return Math.sin(x);
	}

	public static double[] sin(final double... xs) {
		return map(x -> sin(x), xs);
	}

	public static double[] sin(final int... xs) {
		return mapToDoubleIP(x -> sin(x), xs);
	}

	public static void main(final String[] args) {
		System.out.println(digitLength(2));// , 134423.3245523));
	}
}
