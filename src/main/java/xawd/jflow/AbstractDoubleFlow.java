package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

import xawd.jflow.impl.PrimitiveDropWhileFlow;
import xawd.jflow.impl.PrimitiveFilteredFlow;
import xawd.jflow.impl.PrimitiveTakeWhileFlow;
import xawd.jflow.iterators.Skippable;
import xawd.jflow.utilities.Iter;
import xawd.jflow.zippedpairs.DoublePair;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.DoubleWithLong;
import xawd.jflow.zippedpairs.IntWithDouble;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleFlow implements DoubleFlow
{
	@Override
	public DoubleFlow map(final DoubleUnaryOperator f) {
		final AbstractDoubleFlow src = this;
		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public double nextDouble() {
				return f.applyAsDouble(src.nextDouble());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<T> mapToObject(final DoubleFunction<T> f) {
		final AbstractDoubleFlow src = this;
		return new AbstractFlow<T>() {

			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return f.apply(src.nextDouble());
			}
			@Override
			public void skip() {
				src.nextDouble();
			}
		};
	}

	@Override
	public LongFlow mapToLong(final DoubleToLongFunction f)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractLongFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public long nextLong()
			{
				return f.applyAsLong(src.nextDouble());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public IntFlow mapToInt(final DoubleToIntFunction f)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractIntFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public int nextInt()
			{
				return f.applyAsInt(src.nextDouble());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<DoubleWith<T>> zipWith(final Iterator<T> other) {
		final AbstractDoubleFlow src = this;

		return new AbstractFlow<DoubleWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoubleWith<T> next() {
				return DoubleWith.of(src.nextDouble(), other.next());
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
	public Flow<DoublePair> zipWith(final OfDouble other)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractFlow<DoublePair>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoublePair next() {
				return DoublePair.of(src.nextDouble(), other.nextDouble());
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
	public Flow<DoubleWithLong> zipWith(final OfLong other)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractFlow<DoubleWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoubleWithLong next() {
				return DoubleWithLong.of(src.nextDouble(), other.nextLong());
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
	public Flow<IntWithDouble> zipWith(final OfInt other)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractFlow<IntWithDouble>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithDouble next() {
				return IntWithDouble.of(other.nextInt(), src.nextDouble());
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
	public Flow<IntWithDouble> enumerate() {
		return zipWith(Iter.naturalNumbers());
	}

	@Override
	public DoubleFlow take(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public double nextDouble() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.nextDouble();
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
	public DoubleFlow takeWhile(final DoublePredicate predicate)
	{
		return new PrimitiveTakeWhileFlow.OfDouble(this, predicate);
	}

	@Override
	public DoubleFlow drop(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow()
		{
			boolean initialized = false;

			@Override
			public boolean hasNext() {
				initialiseIfRequired();
				return src.hasNext();
			}
			@Override
			public double nextDouble() {
				initialiseIfRequired();
				return src.nextDouble();
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
	public DoubleFlow dropWhile(final DoublePredicate predicate)
	{
		return new PrimitiveDropWhileFlow.OfDouble(this, predicate);
	}

	@Override
	public DoubleFlow filter(final DoublePredicate predicate)
	{
		return new PrimitiveFilteredFlow.OfDouble(this, predicate);
	}

	@Override
	public DoubleFlow append(final OfDouble other) {
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext() || other.hasNext();
			}
			@Override
			public double nextDouble() {
				return src.hasNext() ? src.nextDouble() : other.nextDouble();
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
						other.nextDouble();
					}
				}
			}
		};
	}

	@Override
	public DoubleFlow append(final double... xs)
	{
		return append(Iter.of(xs));
	}

	@Override
	public DoubleFlow insert(final OfDouble other) {
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext() {
				return other.hasNext() || src.hasNext();
			}
			@Override
			public double nextDouble() {
				return other.hasNext() ? other.nextDouble() : src.nextDouble();
			}
			@Override
			public void skip() {
				if (other.hasNext()) {
					if (other instanceof Skippable) {
						((Skippable) other).skip();
					}
					else {
						other.nextDouble();
					}
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public DoubleFlow insert(final double... xs)
	{
		return insert(Iter.of(xs));
	}

	@Override
	public OptionalDouble min() {
		boolean found = false;
		double min = Double.MAX_VALUE;
		while (hasNext()) {
			final double next = nextDouble();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? OptionalDouble.of(min) : OptionalDouble.empty();
	}

	@Override
	public double min(final double defaultValue)
	{
		boolean found = false;
		double min = Double.MAX_VALUE;
		while (hasNext()) {
			final double next = nextDouble();
			if (next < min) {
				min = next;
				found = true;
			}
		}
		return found? min : defaultValue;
	}

	@Override
	public double minByKey(final double defaultValue, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.MAX_VALUE;
		while (hasNext()) {
			final double nextKey = nextDouble();
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
	public OptionalDouble minByKey(final DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (hasNext()) {
			final double nextKey = nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalDouble.of(minKey) : OptionalDouble.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalDouble minByObjectKey(final DoubleFunction<C> key)
	{
		boolean found = false;
		double minKey = -1;
		C minVal = null;
		while (hasNext()) {
			final double nextKey = nextDouble();
			final C nextVal = key.apply(nextKey);
			if (minVal == null || nextVal.compareTo(minVal) < 0) {
				minVal = nextVal;
				minKey = nextKey;
				found = true;
			}
		}
		return found? OptionalDouble.of(minKey) : OptionalDouble.empty();
	}

	@Override
	public OptionalDouble max() {
		boolean found = false;
		double max = Double.MIN_VALUE;
		while (hasNext()) {
			final double next = nextDouble();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? OptionalDouble.of(max) : OptionalDouble.empty();
	}

	@Override
	public double max(final double defaultValue)
	{
		boolean found = false;
		double max = Double.MIN_VALUE;
		while (hasNext()) {
			final double next = nextDouble();
			if (next > max) {
				max = next;
				found = true;
			}
		}
		return found? max : defaultValue;
	}

	@Override
	public double maxByKey(final double defaultValue, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final double nextKey = nextDouble();
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
	public OptionalDouble maxByKey(final DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (hasNext()) {
			final double nextKey = nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);
			if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalDouble.of(maxKey) : OptionalDouble.empty();
	}

	@Override
	public <C extends Comparable<C>> OptionalDouble maxByObjectKey(final DoubleFunction<C> key)
	{
		boolean found = false;
		double maxKey = -1;
		C maxVal = null;
		while (hasNext()) {
			final double nextKey = nextDouble();
			final C nextVal = key.apply(nextKey);
			if (maxVal == null || nextVal.compareTo(maxVal) > 0) {
				maxVal = nextVal;
				maxKey = nextKey;
				found = true;
			}
		}
		return found? OptionalDouble.of(maxKey) : OptionalDouble.empty();
	}

	@Override
	public boolean allMatch(final DoublePredicate predicate)
	{
		while (hasNext()) {
			if (!predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean anyMatch(final DoublePredicate predicate) {
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final DoublePredicate predicate) {
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
	public double reduce(final double id, final DoubleBinaryOperator reducer)
	{
		double reduction = id;
		while (hasNext()) {
			reduction = reducer.applyAsDouble(reduction, nextDouble());
		}
		return reduction;
	}

	@Override
	public OptionalDouble reduce(final DoubleBinaryOperator reducer)
	{
		boolean unIntialized = true;
		double reduction = -1L;
		while (hasNext()) {
			if (unIntialized) {
				unIntialized = false;
				reduction = nextDouble();
			}
			else {
				reduction = reducer.applyAsDouble(reduction, nextDouble());
			}
		}
		return unIntialized ? OptionalDouble.empty() : OptionalDouble.of(reduction);
	}

	@Override
	public DoubleFlow accumulate(final DoubleBinaryOperator accumulator)
	{
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow() {
			boolean unInitialized = true;
			double accumulationValue = -1;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public double nextDouble() {
				if (unInitialized) {
					unInitialized = false;
					accumulationValue = src.nextDouble();
					return accumulationValue;
				}
				else {
					accumulationValue = accumulator.applyAsDouble(accumulationValue, src.nextDouble());
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
	public DoubleFlow accumulate(final double id, final DoubleBinaryOperator accumulator) {
		final AbstractDoubleFlow src = this;

		return new AbstractDoubleFlow() {
			double accumulationValue = id;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public double nextDouble() {
				accumulationValue = accumulator.applyAsDouble(accumulationValue, src.nextDouble());
				return accumulationValue;
			}

			@Override
			public void skip() {
				next();
			}
		};
	}

	@Override
	public void forEach(final DoubleConsumer action)
	{
		while (hasNext()) {
			action.accept(nextDouble());
		}
	}
}