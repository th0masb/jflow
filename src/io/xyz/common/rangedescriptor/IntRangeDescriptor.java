/**
 * 
 */
package io.xyz.common.rangedescriptor;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;

/**
 * @author t
 *
 */
public interface IntRangeDescriptor extends BaseRangeDescriptor {

	static IntRangeDescriptor EMPTY = new ImmutableIntRangeDescriptor(0, i -> i);

	IntUnaryOperator getDescriptor();

	IntRangeDescriptor map(IntUnaryOperator f);

	DoubleRangeDescriptor mapToDouble(IntToDoubleFunction f);

	<T> RangeDescriptor<T> mapToObj(IntFunction<T> f);

	IntRangeDescriptor filter(IntPredicate p);

	default int get(final int index) {
		assert 0 <= index && index < rangeBound();
		return getDescriptor().applyAsInt(index);
	}

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
