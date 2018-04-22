/**
 * 
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
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

import xawd.jflow.impl.SlicedFlow;
import xawd.jflow.iterators.Skippable;
import xawd.jflow.utilities.Iter;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 *
 */
public abstract class AbstractFlow<T> implements Flow<T> {
	
	@Override
	public Flow<T> slice(final IntUnaryOperator f) {
		return new SlicedFlow<>(this, f);
	}

	@Override
	public <R> Flow<R> map(final Function<? super T, R> f) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<R>() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public R next() {
				return f.apply(src.next());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#mapToInt(java.util.function.ToIntFunction)
	 */
	@Override
	public IntFlow mapToInt(final ToIntFunction<? super T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#mapToDouble(java.util.function.ToDoubleFunction)
	 */
	@Override
	public DoubleFlow mapToDouble(final ToDoubleFunction<? super T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LongFlow mapToLong(final ToLongFunction<? super T> f) {
		final AbstractFlow<T> src = this;
		
		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				return f.applyAsLong(src.next());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <R> Flow<Pair<T, R>> zipWith(final Iterator<R> other) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<Pair<T,R>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public Pair<T, R> next() {
				return Pair.of(src.next(), other.next());
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

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#zipWith(java.util.PrimitiveIterator.OfInt)
	 */
	@Override
	public Flow<IntWith<T>> zipWith(final OfInt other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#zipWith(java.util.PrimitiveIterator.OfDouble)
	 */
	@Override
	public Flow<DoubleWith<T>> zipWith(final OfDouble other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<LongWith<T>> zipWith(final OfLong other) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<LongWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongWith<T> next() {
				return LongWith.of(other.nextLong(), src.next());
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
	public <U, R> Flow<R> combineWith(final Iterator<U> other, final BiFunction<T, U, R> f) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<R>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public R next() {
				return f.apply(src.next(), other.next());
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

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#enumerate()
	 */
	@Override
	public Flow<IntWith<T>> enumerate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<T> take(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public T next() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.next();
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
	public Flow<T> takeWhile(final Predicate<? super T> p) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			T cached = null;
			boolean nextReady = false;
			boolean iteratorExhausted = false;
			@Override
			public boolean hasNext() {
				if (nextReady) {
					return true;
				}
				if (src.hasNext()) {
					cached = src.next();
					if (p.test(cached)) {
						nextReady = true;
						return true;
					}
					else {
						iteratorExhausted = true;
						return false;
					}
				}
				else {
					iteratorExhausted = true;
					return false;
				}
			}
			@Override
			public T next() {
				if (iteratorExhausted) {
					throw new NoSuchElementException();
				}
				else if (nextReady) {
					nextReady = false;
					return cached;
				}
				else { // hasnext wasn't called
					if (hasNext()) {
						nextReady = false;
						return cached;
					}
					else {
						throw new NoSuchElementException();
					}
				}
			}
			@Override
			public void skip() {
				if (iteratorExhausted) {
					throw new NoSuchElementException();
				}
				else if (nextReady) {
					nextReady = false;
					src.skip();
				}
				else { // hasnext wasn't called
					if (hasNext()) {
						nextReady = false;
						src.skip();
					}
					else {
						throw new NoSuchElementException();
					}
				}
			}
		};
	}

	@Override
	public Flow<T> drop(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			{
				for (int i = 0; i < n && src.hasNext(); i++) {
					src.skip();
				}
			}
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return src.next();
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public Flow<T> dropWhile(final Predicate<? super T> p) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			T firstFailure = null;
			{
				while (src.hasNext() && firstFailure == null)
				{
					final T next = src.next();
					if (!p.test(next)) {
						firstFailure = next;
					}
				}
			}
			@Override
			public boolean hasNext() {
				return src.hasNext() || firstFailure != null;
			}

			@Override
			public T next() {
				if (firstFailure != null) {
					final T tmp = firstFailure;
					firstFailure = null;
					return tmp;
				}
				else {
					return src.next();
				}
			}
			@Override
			public void skip() {
				if (firstFailure != null) {
					firstFailure = null;
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public Flow<T> filter(final Predicate<? super T> p) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			T cached = null;
			@Override
			public boolean hasNext() {
				while (cached == null && src.hasNext()) {
					final T next = src.next();
					if (p.test(next)) {
						cached = next;
					}
				}
				return cached != null;
			}
			@Override
			public T next() {
				if (hasNext()) {
					final T tmp = cached;
					cached = null;
					return tmp;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				if (hasNext()) {
					cached = null;
					src.skip();
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	@Override
	public Flow<T> append(final Iterator<? extends T> other) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() || other.hasNext();
			}
			@Override
			public T next() {
				return src.hasNext()? src.next() : other.next();
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
						other.next();
					}
				}
			}
		};
	}

	@Override
	public Flow<T> append(final T t) {
		return append(Iter.of(t));
	}

	@Override
	public Flow<T> insert(final Iterator<? extends T> other) {
		final AbstractFlow<T> src = this;

		return new AbstractFlow<T>() {
			@Override
			public boolean hasNext() {
				return other.hasNext() || src.hasNext();
			}
			@Override
			public T next() {
				return other.hasNext()? other.next() : src.next();
			}

			@Override
			public void skip() {
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
	public Flow<T> insert(final T t) {
		return insert(Iter.of(t));
	}

	@Override
	public Optional<T> minByKey(final ToDoubleFunction<? super T> key) {
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
	public <C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key) {
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
	public Optional<T> maxByKey(final ToDoubleFunction<T> key) {
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
	public <C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key) {
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
	public boolean allMatch(final Predicate<? super T> predicate) {
		while (hasNext()) {
			if (!predicate.test(next())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean anyMatch(final Predicate<? super T> predicate) {
		while (hasNext()) {
			if (predicate.test(next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final Predicate<? super T> predicate) {
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
	public T reduce(final T id, final BinaryOperator<T> reducer) {
		T reduction = id;
		while (hasNext()) {
			reduction = reducer.apply(reduction, next());
		}
		return reduction;
	}

	@Override
	public Optional<T> reduce(final BinaryOperator<T> reducer) {
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
	public Flow<T> accumulate(final BinaryOperator<T> accumulator) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
			T accumulationValue = null;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				final T accVal = accumulationValue, next = src.next();
				accumulationValue = accVal == null ? next : accumulator.apply(accVal, next);
				return accumulationValue;
			}
			@Override
			public void skip() {
				// Can't skip here
				next();
			}
		};
	}

	@Override
	public <R> Flow<R> accumulate(final R id, final BiFunction<R, T, R> accumulator) {
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<R>() {
			R accumulationValue = id;
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public R next() {
				accumulationValue = accumulator.apply(accumulationValue, src.next());
				return accumulationValue;
			}

			@Override
			public void skip() {
				// Can't skip here
				next();
			}
		};
	}
}
