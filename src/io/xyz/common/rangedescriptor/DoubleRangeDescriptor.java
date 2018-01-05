/**
 *
 */
package io.xyz.common.rangedescriptor;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

/**
 * @author t
 *
 */
public interface DoubleRangeDescriptor<E> extends BaseRangeDescriptor, Function<DoubleUnaryOperator, E> {

	//	static DoubleRangeDescriptor EMPTY = new ImmutableDoubleRangeDescriptor(0, i -> i);

	IntToDoubleFunction getDescriptor();

	DoubleRangeDescriptor<E> mapToSameDescriptor(DoubleUnaryOperator f);

	IntRangeDescriptor mapToIntDescriptor(DoubleToIntFunction f);

	<T> RangeDescriptor<T> mapToObjDescriptor(DoubleFunction<T> f);

	DoubleRangeDescriptor<E> filter(DoublePredicate p);

	default double get(final int index) {
		assert 0 <= index && index < rangeBound();
		return getDescriptor().applyAsDouble(index);
	}

	default double[] toArray() {
		final int upper = rangeBound();
		final IntToDoubleFunction descriptor = getDescriptor();
		final double[] result = new double[upper];
		for (int i = 0; i < upper; i++) {
			result[i] = descriptor.applyAsDouble(i);
		}
		return result;
	}
}
