/**
 *
 */
package io.xyz.common.rangedescriptor;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;

import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author t
 *
 */
public interface DoubleRangeDescriptor extends BaseRangeDescriptor {

	static DoubleRangeDescriptor EMPTY = new ImmutableDoubleRangeDescriptor(0, i -> i);

	IntToDoubleFunction getDescriptor();

	DoubleRangeDescriptor asDoubleRange(DoubleUnaryOperator f);

	IntRangeDescriptor asIntRange(DoubleToIntFunction f);

	<T> RangeDescriptor<T> asObjRange(DoubleFunction<T> f);

	DoubleRangeDescriptor filter(DoublePredicate p);

	default double get(final int index) {
		assert 0 <= index && index < rangeBound();
		return getDescriptor().applyAsDouble(index);
	}

	default double[] toArray() {
		final int upper = len(this);
		final IntToDoubleFunction descriptor = getDescriptor();
		final double[] result = new double[upper];
		for (int i = 0; i < upper; i++) {
			result[i] = descriptor.applyAsDouble(i);
		}
		return result;
	}
}
