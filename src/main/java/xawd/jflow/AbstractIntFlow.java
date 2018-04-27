package xawd.jflow;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.construction.Iter;
import xawd.jflow.construction.Numbers;
import xawd.jflow.impl.AccumulationFlow;
import xawd.jflow.impl.DropFlow;
import xawd.jflow.impl.DropWhileFlow;
import xawd.jflow.impl.FilteredFlow;
import xawd.jflow.impl.TakeFlow;
import xawd.jflow.impl.TakeWhileFlow;
import xawd.jflow.iterators.Skippable;
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
	public AbstractIntFlow map(final IntUnaryOperator f)
	{
		final AbstractIntFlow src = this;

		return new AbstractIntFlow()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public int nextInt()
			{
				return f.applyAsInt(src.nextInt());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <T> AbstractFlow<T> mapToObject(final IntFunction<T> f)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<T>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public T next()
			{
				return f.apply(src.nextInt());
			}
			@Override
			public void skip()
			{
				src.nextInt();
			}
		};
	}

	@Override
	public AbstractDoubleFlow mapToDouble(final IntToDoubleFunction f)
	{
		final AbstractIntFlow src = this;

		return new AbstractDoubleFlow()
		{
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
	public AbstractLongFlow mapToLong(final IntToLongFunction f)
	{
		final AbstractIntFlow src = this;

		return new AbstractLongFlow()
		{
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
	public <T> AbstractFlow<IntWith<T>> zipWith(final Iterator<T> other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWith<T>>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWith<T> next()
			{
				return IntWith.of(src.nextInt(), other.next());
			}
			@Override
			public void skip()
			{
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
	public AbstractFlow<IntPair> zipWith(final OfInt other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntPair>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntPair next()
			{
				return IntPair.of(src.nextInt(), other.nextInt());
			}
			@Override
			public void skip()
			{
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
	public AbstractFlow<IntWithDouble> zipWith(final OfDouble other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWithDouble>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithDouble next()
			{
				return IntWithDouble.of(src.nextInt(), other.nextDouble());
			}
			@Override
			public void skip()
			{
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
	public AbstractFlow<IntWithLong> zipWith(final OfLong other)
	{
		final AbstractIntFlow src = this;

		return new AbstractFlow<IntWithLong>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithLong next()
			{
				return IntWithLong.of(src.nextInt(), other.nextLong());
			}
			@Override
			public void skip()
			{
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
	public AbstractIntFlow combineWith(final PrimitiveIterator.OfInt other, final IntBinaryOperator combiner)
	{
		final AbstractIntFlow src = this;

		return new AbstractIntFlow()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}

			@Override
			public int nextInt()
			{
				return combiner.applyAsInt(src.nextInt(), other.nextInt());
			}

			@Override
			public void skip()
			{
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
	public AbstractFlow<IntPair> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractIntFlow take(final int n)
	{
		return new TakeFlow.OfInt(this, n);
	}

	@Override
	public AbstractIntFlow takeWhile(final IntPredicate predicate)
	{
		return new TakeWhileFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow drop(final int n)
	{
		return new DropFlow.OfInt(this, n);
	}

	@Override
	public AbstractIntFlow dropWhile(final IntPredicate predicate)
	{
		return new DropWhileFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow filter(final IntPredicate predicate)
	{
		return new FilteredFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow append(final OfInt other)
	{
		final AbstractIntFlow src = this;

		return new AbstractIntFlow()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() || other.hasNext();
			}
			@Override
			public int nextInt()
			{
				return src.hasNext() ? src.nextInt() : other.nextInt();
			}
			@Override
			public void skip()
			{
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
	public AbstractIntFlow append(final int... xs)
	{
		return append(Iter.of(xs));
	}

	@Override
	public AbstractIntFlow insert(final OfInt other)
	{
		final AbstractIntFlow src = this;

		return new AbstractIntFlow()
		{
			@Override
			public boolean hasNext()
			{
				return other.hasNext() || src.hasNext();
			}
			@Override
			public int nextInt()
			{
				return other.hasNext() ? other.nextInt() : src.nextInt();
			}
			@Override
			public void skip()
			{
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
	public AbstractIntFlow insert(final int... xs)
	{
		return insert(Iter.of(xs));
	}

	@Override
	public OptionalInt min()
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
	public boolean anyMatch(final IntPredicate predicate)
	{
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final IntPredicate predicate)
	{
		while (hasNext()) {
			if (predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int count()
	{
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
	public AbstractIntFlow accumulate(final IntBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfInt(this, accumulator);
	}

	@Override
	public AbstractIntFlow accumulate(final int id, final IntBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfInt(this, id, accumulator);
	}
}