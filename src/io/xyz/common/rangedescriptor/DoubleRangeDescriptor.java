/**
 * 
 */
package io.xyz.common.rangedescriptor;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;

/**
 * @author t
 *
 */
public interface DoubleRangeDescriptor extends BaseRangeDescriptor {

	IntToDoubleFunction getDescriptor();

	DoubleRangeDescriptor map(DoubleUnaryOperator f);

	IntRangeDescriptor mapToInt(DoubleToIntFunction f);

	<T> RangeDescriptor<T> mapToObj(DoubleFunction<T> f);

	DoubleRangeDescriptor filter(DoublePredicate p);

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
