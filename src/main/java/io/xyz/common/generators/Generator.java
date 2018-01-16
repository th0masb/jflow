/**
 *
 */
package io.xyz.common.generators;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.ImmutableList;

/**
 * @author t
 *
 */
public interface Generator<T> extends BaseGenerator {

	IntFunction<? extends T> getDescriptor();

	<R> Generator<R> asObjRange(Function<T, R> f);

	IntGenerator asIntRange(ToIntFunction<T> f);

	DoubleGenerator asDoubleRange(ToDoubleFunction<T> f);

	Generator<T> filter(Predicate<T> p);

	default T get(final int index) {
		assert 0 <= index && index < rangeBound();
		return getDescriptor().apply(index);
	}

	default ImmutableList<T> toList() {
		final int upper = rangeBound();
		final IntFunction<? extends T> descriptor = getDescriptor();
		final List<T> result = new ArrayList<>(upper);
		for (int i = 0; i < upper; i++) {
			result.add(descriptor.apply(i));
		}
		return ImmutableList.copyOf(result);
	}

	default Spliterator<T> spliterator()
	{
		return new GeneratorSpliterator<>(this);
	}

	default Stream<T> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}

	default Stream<T> streamParallel()
	{
		return StreamSupport.stream(spliterator(), true);
	}

	static class GeneratorSpliterator<T> implements Spliterator<T> {

		private int upper, origin;
		private final IntFunction<? extends T> generatingFunction;

		public GeneratorSpliterator(final int origin, final int upper, final IntFunction<? extends T> generatingFunction)
		{
			this.upper = upper;
			this.origin = origin;
			this.generatingFunction = generatingFunction;
		}

		public GeneratorSpliterator(final Generator<T> generator)
		{
			this(0, len(generator), generator.getDescriptor());
		}

		@Override
		public int characteristics()
		{
			return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
		}

		@Override
		public long estimateSize()
		{
			return upper - origin;
		}

		@Override
		public boolean tryAdvance(final Consumer<? super T> action)
		{
			if (origin < upper) {
				action.accept(generatingFunction.apply(origin++));
				return true;
			}
			return false;
		}

		@Override
		public Spliterator<T> trySplit()
		{
			final int lo = origin; // divide range in half
			final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
			if (lo < mid) { // split out left half
				origin = mid; // reset this Spliterator's origin
				return new GeneratorSpliterator<>(lo, mid, generatingFunction);
			}
			else       // too small to split
				return null;
		}
	}
}
