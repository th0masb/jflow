/**
 *
 */
package io.xyz.common.rangedescriptor;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.function.IntUnaryOperator;

/**
 * @author t
 *
 */
public class IntRangeDescriptor extends AbstractRangeDescriptor {

	private final IntUnaryOperator descriptor;

	/**
	 * @param rangeBound
	 */
	public IntRangeDescriptor(final IntUnaryOperator f, final int rangeBound) {
		super(rangeBound);
		this.descriptor = f;
	}

	public IntUnaryOperator f() {
		return descriptor;
	}

	public int[] toArray() {
		throw new RuntimeException("NYI");
	}

	public static IntRangeDescriptor from(final int... xs) {
		return new IntRangeDescriptor(IntUnaryOperator.identity(), len(xs));
	}
}
