/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.PrimitiveUtil.abs;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.signum;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.Arrays;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;

/**
 * @author t
 *
 */
public final class RangeUtil {
	private RangeUtil() {
	}

	/**
	 * Think geometrically
	 *
	 * @param startBound
	 * @param endBound
	 * @param step
	 * @return
	 */
	public static IntRangeDescriptor range(final int startBound, final int endBound, final int step) {
		assert step != 0 : "Cannot have 0 step";
		final int boundDiff = endBound - startBound, stepSign = signum(step);

		if (startBound == endBound) {
			return IntRangeDescriptor.EMPTY;
		} else if (signum(boundDiff) != stepSign) {
			return new ImmutableIntRangeDescriptor(1, i -> startBound);
		} else {
			final int n = (int) max(Math.ceil(((double) boundDiff) / step), 1);
			return new ImmutableIntRangeDescriptor(n, i -> startBound + i * step);
		}
	}

	public static IntRangeDescriptor range(final int endBound) {
		return range(0, endBound);
	}

	public static IntRangeDescriptor rangei(final int upperBound) {
		return range(upperBound + (upperBound > 0? 1 : -1));
	}

	public static IntRangeDescriptor range(final int startBound, final int endBound) {
		return range(startBound, endBound, 1);
	}

	public static IntRangeDescriptor rangei(final int startBound, final int endBound) {
		return range(startBound, endBound + (startBound < endBound? 1 : -1), 1);
	}

	/**
	 * @param lower
	 * @param upper
	 * @param step
	 * @return
	 */

	public static DoubleRangeDescriptor drange(final double lower, final double upper, final double step) {
		assert abs(step) >= EPSILON : "Cannot have 0 step";
		final double boundDiff = upper - lower, stepSign = signum(step);

		if (abs(upper - lower) < EPSILON) {
			return DoubleRangeDescriptor.EMPTY;
		} else if (signum(boundDiff) != stepSign) {
			return new ImmutableDoubleRangeDescriptor(1, i -> lower);
		} else {
			final int n = max((int) Math.ceil(boundDiff / step), 1);
			return new ImmutableDoubleRangeDescriptor(n, i -> lower + i * step);
		}
	}

	public static DoubleRangeDescriptor drange(final int upper) {
		return drange(0, upper, 1);
	}

	public static void main(final String[] args) {
		System.out.println(Arrays.toString(drange(0, -1, -0.01).toArray()));
	}
}
