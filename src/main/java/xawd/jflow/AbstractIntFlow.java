package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.impl.PrimitiveDropWhileFlow;
import xawd.jflow.impl.PrimitiveFilteredFlow;
import xawd.jflow.impl.PrimitiveTakeWhileFlow;
import xawd.jflow.iterators.Skippable;
import xawd.jflow.utilities.Iter;
import xawd.jflow.zippedpairs.IntPair;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.IntWithDouble;
import xawd.jflow.zippedpairs.IntWithLong;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractIntFlow implements IntFlow
{
	@Override
	public IntFlow map(final IntUnaryOperator f) {
		final AbstractIntFlow src = this;
		return new AbstractIntFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public int nextInt() {
				return f.applyAsInt(src.nextInt());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<T> mapToObject(final IntFunction<T> f) {
		final AbstractIntFlow src = this;
		return new AbstractFlow<T>() {

			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return f.apply(src.nextInt());
			}
			@Override
			public void skip() {
				src.nextInt();
			}
		};
	}

	@Override
	public DoubleFlow mapToDouble(final IntToDoubleFunction f)
	{
		final AbstractIntFlow src = this;

		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public double nextDouble()
			{
				return f.applyAsDouble(src.nextInt());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public LongFlow mapToLong(final IntToLongFunction f)
	{
		final AbstractIntFlow src = this;

		return new AbstractLongFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public long nextLong()
			{
				return f.applyAsLong(src.nextInt());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<IntWith<T>> zipWith(final Iterator<T> other) {
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWith<T> next() {
				return IntWith.of(src.nextInt(), other.next());
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
	public Flow<IntPair> zipWith(final OfInt other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntPair>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntPair next() {
				return IntPair.of(src.nextInt(), other.nextInt());
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
	public Flow<IntWithDouble> zipWith(final OfDouble other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWithDouble>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithDouble next() {
				return IntWithDouble.of(src.nextInt(), other.nextDouble());
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
	public Flow<IntWithLong> zipWith(final OfLong other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithLong next() {
				return IntWithLong.of(src.nextInt(), other.nextLong());
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
	public Flow<IntPair> enumerate() {
		return zipWith(Iter.naturalNumbers());
	}

	@Override
	public IntFlow take(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractIntFlow src = this;

		return new AbstractIntFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public int nextInt() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.nextInt();
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
	public IntFlow takeWhile(final IntPredicate predicate)
	{
		return new PrimitiveTakeWhileFlow.OfInt(this, predicate);
	}

	@Override
	public IntFlow drop(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractIntFlow src = this;

		return new AbstractIntFlow()
		{
			boolean initialized = false;

			@Override
			public boolean hasNext() {
				initialiseIfRequired();
				return src.hasNext();
			}
			@Override
			public int nextInt() {
				initialiseIfRequired();
				return src.nextInt();
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
	public IntFlow dropWhile(final IntPredicate predicate)
	{
		return new PrimitiveDropWhileFlow.OfInt(this, predicate);
	}

	@Override
	public IntFlow filter(final IntPredicate predicate)
	{
		return new PrimitiveFilteredFlow.OfInt(this, predicate);
	}

	@Override
	public IntFlow append(final OfInt other) {
		final AbstractIntFlow src = this;

		return new AbstractIntFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext() || other.hasNext();
			}
			@Override
			public int nextInt() {
				return src.hasNext() ? src.nextInt() : other.nextInt();
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
						other.nextInt();
					}
				}
			}
		};
	}

	@Override
	public IntFlow append(final int... xs)
	{
		return append(Iter.of(xs));
	}

	@Override
	public IntFlow insert(final OfInt other) {
		final AbstractIntFlow src = this;

		return new AbstractIntFlow() {
			@Override
			public boolean hasNext() {
				return other.hasNext() || src.hasNext();
			}
			@Override
			public int nextInt() {
				return other.hasNext() ? other.nextInt() : src.nextInt();
			}
			@Override
			public void skip() {
				if (other.hasNext()) {
					if (other instanceof Skippable) {
						((Skippable) other).skip();
					}
					else {
						other.nextInt();
					}
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public IntFlow insert(final int... xs)
	{
		return insert(Iter.of(xs));
	}

	@Override
	public OptionalInt min() {
		boolean found = false;
		int min = Integer.MAX_VALUE;
		while (hasNext()) {
			final int next = nextInt();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? OptionalInt.of(min) : OptionalInt.empty();
	}

	@Override
	public int min(final int defaultValue)
	{
		boolean found = false;
		int min = Integer.MAX_VALUE;
		while (hasNext()) {
			final int next = nextInt();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? min : defaultValue;
	}

	@Override
	public int minByKey(final int defaultValue, final IntToDoubleFunction key)
	{
		boolean found = false;
		int minKey = -1;
		double minVal = Double.MAX_VALUE;
		while (hasNext()) {
			final int nextKey = nextInt();
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
	public OptionalInt minByKey(final IntToDoubleFunction key)
	{
		boolean found = false;
		int minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (hasNext()) {
			final int nextKey = nextInt();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalInt.of(minKey) : OptionalInt.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalInt minByObjectKey(final IntFunction<C> key)
	{
		boolean found = false;
		int minKey = -1;
		C minVal = null;
		while (hasNext()) {
			final int nextKey = nextInt();
			final C nextVal = key.apply(nextKey);
			if (minVal == null || nextVal.compareTo(minVal) < 0) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalInt.of(minKey) : OptionalInt.empty();
	}

	@Override
	public OptionalInt max() {
		boolean found = false;
		int max = Integer.MIN_VALUE;
		while (hasNext()) {
			final int next = nextInt();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? OptionalInt.of(max) : OptionalInt.empty();
	}

	@Override
	public int max(final int defaultValue)
	{
		boolean found = false;
		int max = Integer.MIN_VALUE;
		while (hasNext()) {
			final int next = nextInt();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? max : defaultValue;
	}

	@Override
	public int maxByKey(final int defaultValue, final IntToDoubleFunction key)
	{
		boolean found = false;
		int maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final int nextKey = nextInt();
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
	public OptionalInt maxByKey(final IntToDoubleFunction key)
	{
		boolean found = false;
		int maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final int nextKey = nextInt();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalInt.of(maxKey) : OptionalInt.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalInt maxByObjectKey(final IntFunction<C> key)
	{
		boolean found = false;
		int maxKey = -1;
		C maxVal = null;
		while (hasNext()) {
			final int nextKey = nextInt();
			final C nextVal = key.apply(nextKey);
			if (maxVal == null || nextVal.compareTo(maxVal) > 0) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalInt.of(maxKey) : OptionalInt.empty();
	}

	@Override
	public boolean allMatch(final IntPredicate predicate)
	{
		while (hasNext()) {
			if (!predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean anyMatch(final IntPredicate predicate) {
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final IntPredicate predicate) {
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
	public int reduce(final int id, final IntBinaryOperator reducer)
	{
		int reduction = id;
		while (hasNext()) {
			reduction = reducer.applyAsInt(reduction, nextInt());
		}
		return reduction;
	}

	@Override
	public OptionalInt reduce(final IntBinaryOperator reducer)
	{
		boolean unIntialized = true;
		int reduction = -1;
		while (hasNext()) {
			if (unIntialized) {
				unIntialized = false;
				reduction = nextInt();
			}
			else {
				reduction = reducer.applyAsInt(reduction, nextInt());
			}
		}
		return unIntialized ? OptionalInt.empty() : OptionalInt.of(reduction);
	}

	@Override
	public IntFlow accumulate(final IntBinaryOperator accumulator)
	{
		final AbstractIntFlow src = this;

		return new AbstractIntFlow() {
			boolean unInitialized = true;
			int accumulationValue = -1;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public int nextInt() {
				if (unInitialized) {
					unInitialized = false;
					accumulationValue = src.nextInt();
					return accumulationValue;
				}
				else {
					accumulationValue = accumulator.applyAsInt(accumulationValue, src.nextInt());
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
	public IntFlow accumulate(final int id, final IntBinaryOperator accumulator) {
		final AbstractIntFlow src = this;

		return new AbstractIntFlow() {
			int accumulationValue = id;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public int nextInt() {
				accumulationValue = accumulator.applyAsInt(accumulationValue, src.nextInt());
				return accumulationValue;
			}

			@Override
			public void skip() {
				next();
			}
		};
	}
}