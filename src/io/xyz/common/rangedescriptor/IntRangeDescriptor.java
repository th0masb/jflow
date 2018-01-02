/**
 *
 */
package io.xyz.common.rangedescriptor;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

/**
 * @author t
 *
 */
public class IntRangeDescriptor extends AbstractRangeDescriptor {

	private static final int DEFAULT_FILTER_CAPACITY = 1;

	private final IntUnaryOperator descriptor;
	private final List<IntPredicate> filters = new ArrayList<>(DEFAULT_FILTER_CAPACITY);

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

	public

	public int[] toArray() {
		throw new RuntimeException("NYI");
	}

	public static IntRangeDescriptor from(final int... xs) {
		return new IntRangeDescriptor(IntUnaryOperator.identity(), len(xs));
	}
}
