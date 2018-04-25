/**
 *
 */
package xawd.jflow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.construction.Iter;
import xawd.jflow.construction.Numbers;
import xawd.jflow.impl.AccumulationFlow;
import xawd.jflow.impl.DropFlow;
import xawd.jflow.impl.DropWhileFlow;
import xawd.jflow.impl.FilteredFlow;
import xawd.jflow.impl.SlicedFlow;
import xawd.jflow.impl.TakeFlow;
import xawd.jflow.impl.TakeWhileFlow;
import xawd.jflow.iterators.Skippable;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 *
 */
public abstract class AbstractFlow<T> implements Flow<T>
{
	@Override
	public <R> Flow<R> flatten(final Function<? super T, ? extends Flow<? extends R>> mapping)
	{
		throw new RuntimeException();
	}
	
	@Override
	public IntFlow flattenToInts(final Function<? super T, ? extends IntFlow> mapping)
	{
		throw new RuntimeException();
	}
	
	@Override
	public Flow<T> slice(final IntUnaryOperator f)
	{
		return new SlicedFlow<>(this, f);
	}

	@Override
	public <R> Flow<R> map(final Function<? super T, R> f)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<R>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public R next()
			{
				return f.apply(src.next());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public IntFlow mapToInt(final ToIntFunction<? super T> f)
	{
		final AbstractFlow<T> src = this;

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
				return f.applyAsInt(src.next());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public DoubleFlow mapToDouble(final ToDoubleFunction<? super T> f)
	{
		final AbstractFlow<T> src = this;

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
				return f.applyAsDouble(src.next());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public LongFlow mapToLong(final ToLongFunction<? super T> f)
	{
		final AbstractFlow<T> src = this;

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
				return f.applyAsLong(src.next());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <R> Flow<Pair<T, R>> zipWith(final Iterator<R> other)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<Pair<T,R>>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public Pair<T, R> next()
			{
				return Pair.of(src.next(), other.next());
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
	public Flow<IntWith<T>> zipWith(final OfInt other)
	{
		final AbstractFlow<T> src = this;

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
				return IntWith.of(other.nextInt(), src.next());
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
	public Flow<DoubleWith<T>> zipWith(final OfDouble other)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<DoubleWith<T>>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoubleWith<T> next()
			{
				return DoubleWith.of(other.nextDouble(), src.next());
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
	public Flow<LongWith<T>> zipWith(final OfLong other)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<LongWith<T>>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongWith<T> next()
			{
				return LongWith.of(other.nextLong(), src.next());
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
	public <U, R> Flow<R> combineWith(final Iterator<U> other, final BiFunction<T, U, R> f)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<R>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() && other.hasNext();
			}
			@Override
			public R next()
			{
				return f.apply(src.next(), other.next());
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
	public Flow<IntWith<T>> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public Flow<T> take(final int n)
	{
		return new TakeFlow.OfObject<>(this, n);
	}

	@Override
	public Flow<T> takeWhile(final Predicate<? super T> predicate)
	{
		return new TakeWhileFlow.OfObject<>(this, predicate);
	}

	@Override
	public Flow<T> drop(final int n)
	{
		return new DropFlow.OfObject<>(this, n);
	}

	@Override
	public Flow<T> dropWhile(final Predicate<? super T> predicate)
	{
		return new DropWhileFlow.OfObject<>(this, predicate);
	}

	@Override
	public Flow<T> filter(final Predicate<? super T> predicate)
	{
		return new FilteredFlow.OfObject<>(this, predicate);
	}

	@Override
	public Flow<T> append(final Iterator<? extends T> other)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<T>()
		{
			@Override
			public boolean hasNext()
			{
				return src.hasNext() || other.hasNext();
			}
			@Override
			public T next()
			{
				return src.hasNext()? src.next() : other.next();
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
						other.next();
					}
				}
			}
		};
	}

	@Override
	public Flow<T> append(@SuppressWarnings("unchecked") final T... ts) {
		return append(Iter.of(Arrays.asList(ts)));
	}

	@Override
	public Flow<T> insert(final Iterator<? extends T> other)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<T>()
		{
			@Override
			public boolean hasNext()
			{
				return other.hasNext() || src.hasNext();
			}
			@Override
			public T next()
			{
				return other.hasNext()? other.next() : src.next();
			}

			@Override
			public void skip()
			{
				if (other.hasNext()) {
					if (other instanceof Skippable) {
						((Skippable) other).skip();
					}
					else {
						other.next();
					}
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public Flow<T> insert(@SuppressWarnings("unchecked") final T... ts) {
		return insert(Iter.of(Arrays.asList(ts)));
	}

	@Override
	public Optional<T> minByKey(final ToDoubleFunction<? super T> key)
	{
		T min = null;
		double minVal = -1;
		while (hasNext()) {
			final T next = next();
			final double nextVal = key.applyAsDouble(next);
			min = minVal <= nextVal ? min : next;
			minVal = next == min ? nextVal : minVal;
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	@Override
	public <C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key)
	{
		T min = null;
		C minVal = null;
		while (hasNext()) {
			final T next = next();
			final C nextVal = key.apply(next);
			min = minVal.compareTo(nextVal) < 1 ? min : next;
			minVal = next == min ? nextVal : minVal;
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	@Override
	public Optional<T> maxByKey(final ToDoubleFunction<T> key)
	{
		T max = null;
		double maxVal = -1;
		while (hasNext()) {
			final T next = next();
			final double nextVal = key.applyAsDouble(next);
			max = maxVal >= nextVal ? max : next;
			maxVal = next == max ? nextVal : maxVal;
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	@Override
	public <C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key)
	{
		T max = null;
		C maxVal = null;
		while (hasNext()) {
			final T next = next();
			final C nextVal = key.apply(next);
			max = maxVal.compareTo(nextVal) > -1 ? max : next;
			maxVal = next == max ? nextVal : maxVal;
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	@Override
	public boolean allMatch(final Predicate<? super T> predicate)
	{
		while (hasNext()) {
			if (!predicate.test(next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean anyMatch(final Predicate<? super T> predicate)
	{
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final Predicate<? super T> predicate)
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
	public T reduce(final T id, final BinaryOperator<T> reducer)
	{
		T reduction = id;
		while (hasNext()) {
			reduction = reducer.apply(reduction, next());
		}
		return reduction;
	}

	@Override
	public Optional<T> reduce(final BinaryOperator<T> reducer)
	{
		T reduction = null;
		while (hasNext()) {
			if (reduction == null) {
				reduction = next();
			}
			else {
				reduction = reducer.apply(reduction, next());
			}
		}
		return reduction == null ? Optional.empty() : Optional.of(reduction);
	}

	@Override
	public Flow<T> accumulate(final BinaryOperator<T> accumulator)
	{
		return new AccumulationFlow.OfObject<>(this, accumulator);
	}

	@Override
	public <R> Flow<R> accumulate(final R id, final BiFunction<R, T, R> accumulator)
	{
		return new AccumulationFlow.OfObjectWithMixedTypes<>(this, id, accumulator);
	}
}
