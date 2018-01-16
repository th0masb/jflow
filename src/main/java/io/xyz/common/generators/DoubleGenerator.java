/**
 *
 */
package io.xyz.common.generators;

import static io.xyz.common.funcutils.CollectionUtil.asString;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.range;
import static io.xyz.common.funcutils.PredicateUtil.all;

import java.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

import io.xyz.common.generators.impl.ImmutableDoubleGenerator;

/**
 * @author t
 *
 */
public interface DoubleGenerator extends BaseGenerator {

	static DoubleGenerator EMPTY = ImmutableDoubleGenerator.of(0, i -> i);

	IntToDoubleFunction getDescriptor();

	DoubleGenerator asDoubleRange(DoubleUnaryOperator f);

	IntGenerator asIntRange(DoubleToIntFunction f);

	<T> Generator<T> asObjRange(DoubleFunction<T> f);

	DoubleGenerator filter(DoublePredicate p);

	default boolean isSorted()
	{
		return all(i -> get(i) < get(i + 1), range(rangeBound() - 1));
	}

	default double get(final int index)
	{
		assert 0 <= index && index < rangeBound();
		return getDescriptor().applyAsDouble(index);
	}

	default double[] toArray()
	{
		final int upper = len(this);
		final IntToDoubleFunction descriptor = getDescriptor();
		final double[] result = new double[upper];
		for (int i = 0; i < upper; i++) {
			result[i] = descriptor.applyAsDouble(i);
		}
		return result;
	}

	default Spliterator.OfDouble spliterator()
	{
		return new DoubleGeneratorSpliterator(this);
	}

	default DoubleStream stream()
	{
		return StreamSupport.doubleStream(spliterator(), false);
	}

	default DoubleStream streamParallel()
	{
		return StreamSupport.doubleStream(spliterator(), true);
	}

	static class DoubleGeneratorSpliterator implements Spliterator.OfDouble {

		private int upper, origin;
		private final IntToDoubleFunction generatingFunction;

		public DoubleGeneratorSpliterator(final int origin, final int upper, final IntToDoubleFunction generatingFunction)
		{
			this.upper = upper;
			this.origin = origin;
			this.generatingFunction = generatingFunction;
		}

		public DoubleGeneratorSpliterator(final DoubleGenerator generator)
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
		public boolean tryAdvance(final DoubleConsumer action)
		{
			if (origin < upper) {
				action.accept(generatingFunction.applyAsDouble(origin++));
				return true;
			}
			return false;
		}

		@Override
		public OfDouble trySplit()
		{
			final int lo = origin; // divide range in half
			final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
			if (lo < mid) { // split out left half
				origin = mid; // reset this Spliterator's origin
				return new DoubleGeneratorSpliterator(lo, mid, generatingFunction);
			}
			else       // too small to split
				return null;
		}
	}

	public static void main(final String[] args) {
		final String y = asString(doubleRange(x -> 2*x*x - 5, 5).stream().toArray());
		System.out.println(y);
	}
}
