/**
 * 
 */
package io.xyz.common.rangedescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * @author t
 *
 */
public interface RangeDescriptor<T> extends BaseRangeDescriptor {

	IntFunction<T> getDescriptor();

	RangeDescriptor<T> map(UnaryOperator<T> f);

	IntRangeDescriptor mapToInt(ToIntFunction<T> f);

	DoubleRangeDescriptor mapToDouble(ToDoubleFunction<T> f);

	RangeDescriptor<T> filter(Predicate<T> p);

	default List<T> toList() {
		final int upper = rangeBound();
		final IntFunction<T> descriptor = getDescriptor();
		final List<T> result = new ArrayList<>(upper);
		for (int i = 0; i < upper; i++) {
			result.add(descriptor.apply(i));
		}
		return result;
	}
}
