/**
 * 
 */
package io.xyz.common.rangedescriptor;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

/**
 * @author t
 *
 */
public interface IntRangeDescriptor extends BaseRangeDescriptor {

	IntUnaryOperator getDescriptor();

	IntRangeDescriptor map(IntUnaryOperator f);

	DoubleRangeDescriptor mapToDouble(IntToDoubleFunction f);

	<T> RangeDescriptor<T> mapToObj(IntFunction<T> f);

	IntRangeDescriptor filter(IntPredicate p);

	default int[] toArray() {
		final int upper = rangeBound();
		final IntUnaryOperator descriptor = getDescriptor();
		final int[] result = new int[upper];
		for (int i = 0; i < upper; i++) {
			result[i] = descriptor.applyAsInt(i);
		}
		return result;
	}

	// List<IntPredicate> getFilters();
	//
	// default BitArray unfilteredIndices() {
	// final List<IntPredicate> filter = getFilters();
	// final BitArray bitArr = rangeToBool(i -> all(filter, i), rangeBound());
	// assert len(bitArr) == rangeBound();
	// return bitArr;
	// }

	public static void main(final String[] args) {
		// final IntUnaryOperator f = i -> i;
	}
}
