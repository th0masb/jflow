/**
 *
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import xawd.jflow.impl.PrimitiveDropWhileFlow;
import xawd.jflow.impl.PrimitiveFilteredFlow;
import xawd.jflow.impl.PrimitiveTakeWhileFlow;
import xawd.jflow.iterators.Skippable;
import xawd.jflow.utilities.Iter;
import xawd.jflow.zippedpairs.DoubleWithLong;
import xawd.jflow.zippedpairs.IntWithLong;
import xawd.jflow.zippedpairs.LongPair;
import xawd.jflow.zippedpairs.LongWith;

/**
 * @author t
 */
public abstract class AbstractLongFlow implements LongFlow
{
	@Override
	public LongFlow map(final LongUnaryOperator f) {
		final AbstractLongFlow src = this;
		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				return f.applyAsLong(src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<T> mapToObject(final LongFunction<T> f) {
		final AbstractLongFlow src = this;
		return new AbstractFlow<T>() {

			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return f.apply(src.nextLong());
			}
			@Override
			public void skip() {
				src.nextLong();
			}
		};
	}

	@Override
	public DoubleFlow mapToDouble(final LongToDoubleFunction f)
	{
		final AbstractLongFlow src = this;

		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public double nextDouble()
			{
				return f.applyAsDouble(src.nextLong());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public IntFlow mapToInt(final LongToIntFunction f)
	{
		final AbstractLongFlow src = this;

		return new AbstractIntFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public int nextInt()
			{
				return f.applyAsInt(src.nextLong());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<LongWith<T>> zipWith(final Iterator<T> other) {
		final AbstractLongFlow src = this;

		return new AbstractFlow<LongWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongWith<T> next() {
				return LongWith.of(src.nextLong(), other.next());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.next();
				}
			}
		};
	}

	@Override
	public Flow<LongPair> zipWith(final OfLong other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<LongPair>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongPair next() {
				return LongPair.of(src.nextLong(), other.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextLong();
				}
			}
		};
	}

	@Override
	public Flow<DoubleWithLong> zipWith(final OfDouble other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<DoubleWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoubleWithLong next() {
				return DoubleWithLong.of(other.nextDouble(), src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextDouble();
				}
			}
		};
	}

	@Override
	public Flow<IntWithLong> zipWith(final OfInt other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<IntWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithLong next() {
				return IntWithLong.of(other.nextInt(), src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextInt();
				}
			}
		};
	}

	@Override
	public Flow<IntWithLong> enumerate() {
		return zipWith(Iter.naturalNumbers());
	}

	@Override
	public LongFlow take(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public long nextLong() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.nextLong();
				}
			}
			@Override
			public void skip() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public LongFlow takeWhile(final LongPredicate predicate)
	{
		return new PrimitiveTakeWhileFlow.OfLong(this, predicate);
	}

	@Override
	public LongFlow drop(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractLongFlow src = this;

		return new AbstractLongFlow()
		{
			boolean initialized = false;

			@Override
			public boolean hasNext() {
				initialiseIfRequired();
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				initialiseIfRequired();
				return src.nextLong();
			}
			@Override
			public void skip() {
				initialiseIfRequired();
				src.skip();
			}

			private void initialiseIfRequired() {
				if (!initialized) {
					for (int count = 0; count < n && src.hasNext(); count++) {
						src.skip();
					}
					initialized = true;
				}
			}
		};
	}

	@Override
	public LongFlow dropWhile(final LongPredicate predicate)
	{
		return new PrimitiveDropWhileFlow.OfLong(this, predicate);
	}

	@Override
	public LongFlow filter(final LongPredicate predicate)
	{
		return new PrimitiveFilteredFlow.OfLong(this, predicate);
	}

	@Override
	public LongFlow append(final OfLong other) {
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext() || other.hasNext();
			}
			@Override
			public long nextLong() {
				return src.hasNext() ? src.nextLong() : other.nextLong();
			}
			@Override
			public void skip() {
				if (src.hasNext()) {
					src.skip();
				}
				else {
					if (other instanceof Skippable) {
						((Skippable) other).skip();
					}
					else {
						other.nextLong();
					}
				}
			}
		};
	}

	@Override
	public LongFlow append(final long... xs)
	{
		return append(Iter.of(xs));
	}

	@Override
	public LongFlow insert(final OfLong other) {
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return other.hasNext() || src.hasNext();
			}
			@Override
			public long nextLong() {
				return other.hasNext() ? other.nextLong() : src.nextLong();
			}
			@Override
			public void skip() {
				if (other.hasNext()) {
					if (other instanceof Skippable) {
						((Skippable) other).skip();
					}
					else {
						other.nextLong();
					}
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public LongFlow insert(final long... xs)
	{
		return insert(Iter.of(xs));
	}

	@Override
	public OptionalLong min() {
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (hasNext()) {
			final long next = nextLong();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? OptionalLong.of(min) : OptionalLong.empty();
	}

	@Override
	public long min(final long defaultValue)
	{
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (hasNext()) {
			final long next = nextLong();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? min : defaultValue;
	}

	@Override
	public long minByKey(final long defaultValue, final LongToDoubleFunction key)
	{
		boolean found = false;
		long minKey = -1;
		double minVal = Double.MAX_VALUE;
		while (hasNext()) {
			final long nextKey = nextLong();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? minKey : defaultValue;
	}

	@Override
	public OptionalLong minByKey(final LongToDoubleFunction key)
	{
		boolean found = false;
		long minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (hasNext()) {
			final long nextKey = nextLong();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalLong.of(minKey) : OptionalLong.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalLong minByObjectKey(final LongFunction<C> key)
	{
		boolean found = false;
		long minKey = -1;
		C minVal = null;
		while (hasNext()) {
			final long nextKey = nextLong();
			final C nextVal = key.apply(nextKey);
			if (minVal == null || nextVal.compareTo(minVal) < 0) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalLong.of(minKey) : OptionalLong.empty();
	}

	@Override
	public OptionalLong max() {
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (hasNext()) {
			final long next = nextLong();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? OptionalLong.of(max) : OptionalLong.empty();
	}

	@Override
	public long max(final long defaultValue)
	{
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (hasNext()) {
			final long next = nextLong();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? max : defaultValue;
	}

	@Override
	public long maxByKey(final long defaultValue, final LongToDoubleFunction key)
	{
		boolean found = false;
		long maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final long nextKey = nextLong();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? maxKey : defaultValue;
	}

	@Override
	public OptionalLong maxByKey(final LongToDoubleFunction key)
	{
		boolean found = false;
		long maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final long nextKey = nextLong();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalLong.of(maxKey) : OptionalLong.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalLong maxByObjectKey(final LongFunction<C> key)
	{
		boolean found = false;
		long maxKey = -1;
		C maxVal = null;
		while (hasNext()) {
			final long nextKey = nextLong();
			final C nextVal = key.apply(nextKey);
			if (maxVal == null || nextVal.compareTo(maxVal) > 0) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalLong.of(maxKey) : OptionalLong.empty();
	}

	@Override
	public boolean allMatch(final LongPredicate predicate)
	{
		while (hasNext()) {
			if (!predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean anyMatch(final LongPredicate predicate) {
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final LongPredicate predicate) {
		while (hasNext()) {
			if (predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int count() {
		int count = 0;
		while (hasNext()) {
			skip();
			count++;
		}
		return count;
	}

	@Override
	public long reduce(final long id, final LongBinaryOperator reducer)
	{
		long reduction = id;
		while (hasNext()) {
			reduction = reducer.applyAsLong(reduction, nextLong());
		}
		return reduction;
	}

	@Override
	public OptionalLong reduce(final LongBinaryOperator reducer)
	{
		boolean unIntialized = true;
		long reduction = -1L;
		while (hasNext()) {
			if (unIntialized) {
				unIntialized = false;
				reduction = nextLong();
			}
			else {
				reduction = reducer.applyAsLong(reduction, nextLong());
			}
		}
		return unIntialized ? OptionalLong.empty() : OptionalLong.of(reduction);
	}

	@Override
	public LongFlow accumulate(final LongBinaryOperator accumulator)
	{
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			boolean unInitialized = true;
			long accumulationValue = -1;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				if (unInitialized) {
					unInitialized = false;
					accumulationValue = src.nextLong();
					return accumulationValue;
				}
				else {
					accumulationValue = accumulator.applyAsLong(accumulationValue, src.nextLong());
					return accumulationValue;
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	@Override
	public LongFlow accumulate(final long id, final LongBinaryOperator accumulator) {
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			long accumulationValue = id;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				accumulationValue = accumulator.applyAsLong(accumulationValue, src.nextLong());
				return accumulationValue;
			}

			@Override
			public void skip() {
				next();
			}
		};
	}
}
