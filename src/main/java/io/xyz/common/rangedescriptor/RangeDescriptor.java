/**
 *
 */
package io.xyz.common.rangedescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableList;

/**
 * @author t
 *
 */
public interface RangeDescriptor<T> extends BaseRangeDescriptor {

	IntFunction<? extends T> getDescriptor();

	<R> RangeDescriptor<R> asObjRange(Function<T, R> f);

	IntRangeDescriptor asIntRange(ToIntFunction<T> f);

	DoubleRangeDescriptor asDoubleRange(ToDoubleFunction<T> f);

	RangeDescriptor<T> filter(Predicate<T> p);

	default T get(final int index) {
		assert 0 <= index && index < rangeBound();
		return getDescriptor().apply(index);
	}

	default List<T> toList() {
		final int upper = rangeBound();
		final IntFunction<? extends T> descriptor = getDescriptor();
		final List<T> result = new ArrayList<>(upper);
		for (int i = 0; i < upper; i++) {
			result.add(descriptor.apply(i));
		}
		return ImmutableList.copyOf(result);
	}
}
