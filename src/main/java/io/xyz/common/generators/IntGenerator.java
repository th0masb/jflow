/**
 *
 */
package io.xyz.common.generators;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import io.xyz.common.generators.impl.ImmutableIntGenerator;

/**
 * @author t
 *
 */
public interface IntGenerator extends BaseGenerator {

	static IntGenerator EMPTY = ImmutableIntGenerator.of(0, i -> i);

	IntUnaryOperator getDescriptor();

	IntGenerator asIntRange(IntUnaryOperator f);

	DoubleGenerator asDoubleRange(IntToDoubleFunction f);

	<T> Generator<T> asObjRange(IntFunction<T> f);

	IntGenerator filter(IntPredicate p);

	default int get(final int index)
	{
		assert indexIsInRange(index);
		return getDescriptor().applyAsInt(index);
	}

	default int[] toArray()
	{
		final int upper = rangeBound();
		final IntUnaryOperator descriptor = getDescriptor();
		final int[] result = new int[upper];
		for (int i = 0; i < upper; i++) {
			result[i] = descriptor.applyAsInt(i);
		}
		return result;
	}

	default Spliterator.OfInt spliterator()
	{
		return new IntGeneratorSpliterator(this);
	}

	default IntStream stream()
	{
		return StreamSupport.intStream(spliterator(), false);
	}

	default IntStream streamParallel()
	{
		return StreamSupport.intStream(spliterator(), true);
	}

	static class IntGeneratorSpliterator implements Spliterator.OfInt {

		private int upper, origin;
		private final IntUnaryOperator generatingFunction;

		public IntGeneratorSpliterator(final int origin, final int upper, final IntUnaryOperator generatingFunction)
		{
			this.upper = upper;
			this.origin = origin;
			this.generatingFunction = generatingFunction;
		}

		public IntGeneratorSpliterator(final IntGenerator generator)
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
		public boolean tryAdvance(final IntConsumer action)
		{
			if (origin < upper) {
				action.accept(generatingFunction.applyAsInt(origin++));
				return true;
			}
			return false;
		}

		@Override
		public OfInt trySplit()
		{
			final int lo = origin; // divide range in half
			final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
			if (lo < mid) { // split out left half
				origin = mid; // reset this Spliterator's origin
				return new IntGeneratorSpliterator(lo, mid, generatingFunction);
			}
			else       // too small to split
				return null;
		}
	}

	public static void main(final String[] args)
	{
		// final IntUnaryOperator f = i -> i;
	}
}
