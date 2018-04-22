/**
 * 
 */
package xawd.jflow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.SkippableIterator;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 *
 */
public abstract class AbstractObjectFlow<T> implements ObjectFlow<T> 
{
	@Override
	public <R> ObjectFlow<R> map(final Function<? super T, R> f) 
	{
		final AbstractObjectFlow<T> src = this;

		return new AbstractObjectFlow<R>() {
			@Override
			public SkippableIterator<R> iterator() {
				return new SkippableIterator<R>() {
					SkippableIterator<T> iterT = src.iterator();
					@Override
					public boolean hasNext() {
						return iterT.hasNext();
					}
					@Override
					public R next() {
						return f.apply(iterT.next());
					}
					@Override
					public void skip() {
						iterT.skip();
					}
				};
			}
		};
	}

	@Override
	public IntFlow mapToInt(final ToIntFunction<? extends T> f) 
	{
		throw new RuntimeException();
	}

	@Override
	public DoubleFlow mapToDouble(final ToDoubleFunction<? extends T> f)
	{
		throw new RuntimeException();
	}

	@Override
	public LongFlow mapToLong(final ToLongFunction<? extends T> f)
	{
		throw new RuntimeException();
	}

	@Override
	public <R> ObjectFlow<Pair<T, R>> zipWith(final Iterable<R> other) 
	{
		final AbstractObjectFlow<T> src = this;

		return new AbstractObjectFlow<Pair<T, R>>() {
			@Override
			public SkippableIterator<Pair<T, R>> iterator() {
				return new SkippableIterator<Pair<T,R>>() {
					SkippableIterator<T> iterT = src.iterator();
					Iterator<R> iterR = other.iterator();
					@Override
					public boolean hasNext() {
						return iterT.hasNext() && iterR.hasNext();
					}
					@Override
					public Pair<T, R> next() {
						return Pair.of(iterT.next(), iterR.next());
					}
					@Override
					public void skip() {
						iterT.skip();
						if (iterR instanceof SkippableIterator<?>) {
							((SkippableIterator<?>) iterR).skip();
						}
						else {
							iterR.next();
						}
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<IntWith<T>> zipWith(final OfInt other) 
	{
		throw new RuntimeException();
	}

	@Override
	public ObjectFlow<DoubleWith<T>> zipWith(final OfDouble other) 
	{
		throw new RuntimeException();
	}

	@Override
	public ObjectFlow<LongWith<T>> zipWith(final OfLong other) 
	{
		throw new RuntimeException();
	}

	@Override
	public <U, R> ObjectFlow<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f)
	{
		final AbstractObjectFlow<T> src = this;

		return new AbstractObjectFlow<R>() {
			@Override
			public SkippableIterator<R> iterator() {
				return new SkippableIterator<R>() {
					SkippableIterator<T> iterT = src.iterator();
					Iterator<U> iterU = other.iterator();
					@Override
					public boolean hasNext() {
						return iterT.hasNext() && iterU.hasNext();
					}
					@Override
					public R next() {
						return f.apply(iterT.next(), iterU.next());
					}
					@Override
					public void skip() {
						iterT.skip();
						if (iterU instanceof SkippableIterator<?>) {
							((SkippableIterator<?>) iterU).skip();
						}
						else {
							iterU.next();
						}
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<IntWith<T>> enumerate() 
	{
		final ObjectFlow<T> src = this;

		return new AbstractObjectFlow<IntWith<T>>() {
			@Override
			public SkippableIterator<IntWith<T>> iterator() {
				return new SkippableIterator<IntWith<T>>() {
					SkippableIterator<T> iterT = src.iterator();
					int count = 0;
					@Override
					public boolean hasNext() {
						return iterT.hasNext();
					}
					@Override
					public IntWith<T> next() {
						return IntWith.of(count++, iterT.next());
					}
					@Override
					public void skip() {
						count++;
						iterT.skip();
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> cycle() 
	{
		final AbstractObjectFlow<T> src = this;

		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> iterT = src.iterator();
					{
						if (!iterT.hasNext()) {
							throw new IllegalStateException("Cannot cycle an empty iterable!");
						}
					}
					@Override
					public boolean hasNext() {
						if (!iterT.hasNext()) {
							iterT = src.iterator();
						}
						return true;
					}
					@Override
					public T next() 
					{
						hasNext();
						return iterT.next();
					}
					@Override
					public void skip() 
					{
						hasNext();
						iterT.skip();
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> take(final int n) 
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		final ObjectFlow<T> src = this;
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> iterT = src.iterator();
					int count = 0;
					@Override
					public boolean hasNext() {
						return iterT.hasNext() && count < n;
					}
					@Override
					public T next() {
						count++;
						return iterT.next();
					}
					@Override
					public void skip() {
						count++;
						iterT.skip();
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> takeWhile(final Predicate<? super T> p) 
	{
		final AbstractObjectFlow<T> src = this;
		
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> iterT = src.iterator();
					T cached = null;
					boolean nextReady = false;
					boolean iteratorExhausted = false;
					
					@Override
					public boolean hasNext() {
						if (nextReady) {
							return true;
						}
						if (iterT.hasNext()) {
							cached = iterT.next();
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
					public void skip() 
					{
						if (iteratorExhausted) {
							throw new NoSuchElementException();
						}
						else if (nextReady) {
							nextReady = false;
							iterT.skip();
						}
						else { // hasnext wasn't called
							if (hasNext()) {
								nextReady = false;
								iterT.skip();
							}
							else {
								throw new NoSuchElementException();
							}
						}
					}
				};
			}
			
		};
	}

	@Override
	public ObjectFlow<T> drop(final int n) 
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		final ObjectFlow<T> src = this;
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				
				return new SkippableIterator<T>() {
					SkippableIterator<T> iterT = src.iterator();
					{
						for (int i = 0; i < n && iterT.hasNext(); i++) {
							iterT.skip();
						}
					}
					@Override
					public boolean hasNext() {
						return iterT.hasNext();
					}

					@Override
					public T next() {
						return iterT.next();
					}

					@Override
					public void skip() {
						iterT.skip();
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> dropWhile(final Predicate<? super T> p) 
	{
		final AbstractObjectFlow<T> src = this;
		
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> srcT = src.iterator();
					T firstFailure = null;
					{
						while (srcT.hasNext() && firstFailure == null)
						{
							final T next = srcT.next();
							if (!p.test(next)) {
								firstFailure = next;
							}
						}
					}
					@Override
					public boolean hasNext() {
						return srcT.hasNext();
					}

					@Override
					public T next() {
						return srcT.next();
					}

					@Override
					public void skip() {
						srcT.skip();
					}
				};
			}
		};
	}

	@Override
	public Pair<ObjectFlow<T>, ObjectFlow<T>> split(final int leftSize) 
	{
		if (leftSize < 0) {
			throw new IllegalArgumentException();
		}
		return Pair.of(take(leftSize), drop(leftSize));
	}

	@Override
	public Pair<ObjectFlow<T>, ObjectFlow<T>> splitByPredicate(final Predicate<? super T> p)
	{
		return Pair.of(takeWhile(p), dropWhile(p));
	}


	@Override
	public ObjectFlow<T> filter(final Predicate<? super T> p) 
	{
		final AbstractObjectFlow<T> src = this;
		
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() 
				{
					SkippableIterator<T> srcT = src.iterator();
					T cached = null;
					@Override
					public boolean hasNext() {
						while (cached == null && srcT.hasNext()) {
							final T next = srcT.next();
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
					public void skip() 
					{
						if (hasNext()) {
							cached = null;
							srcT.skip();;
						}
						else {
							throw new NoSuchElementException();
						}
					}
				};
			}
			
		};
	}

	@Override
	public ObjectFlow<T> append(final Iterable<? extends T> other) 
	{
		final AbstractObjectFlow<T> src = this;
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> srcT = src.iterator();
					Iterator<? extends T> src$ = other.iterator();
					
					@Override
					public boolean hasNext() {
						return srcT.hasNext() || src$.hasNext();
					}
					@Override
					public T next() {
						return srcT.hasNext()? srcT.next() : src$.next();
					}
					@Override
					public void skip() {
						if (srcT.hasNext()) {
							srcT.skip();
						}
						else {
							if (src$ instanceof SkippableIterator<?>) {
								((SkippableIterator<?>) src$).skip();
							}
							else {
								src$.next();
							}
						}
					}
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> append(final T t) 
	{
		return append(Arrays.asList(t));
	}

	@Override
	public ObjectFlow<T> insert(final Iterable<? extends T> other) {
		final AbstractObjectFlow<T> src = this;
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return new SkippableIterator<T>() {
					SkippableIterator<T> srcT = src.iterator();
					Iterator<? extends T> src$ = other.iterator();
					@Override
					public boolean hasNext() {
						return src$.hasNext() || srcT.hasNext();
					}

					@Override
					public T next() {
						return src$.hasNext()? src$.next() : srcT.next();
					}

					@Override
					public void skip() {
						if (src$.hasNext()) {
							if (src$ instanceof SkippableIterator<?>) {
								((SkippableIterator<?>) src$).skip();
							}
							else {
								src$.next();
							}
						}
						else {
							srcT.skip();
						}
					}
					
				};
			}
		};
	}

	@Override
	public ObjectFlow<T> insert(final T t) 
	{
		return insert(Arrays.asList(t));
	}

	@Override
	public Optional<T> minByKey(final ToDoubleFunction<? super T> key) 
	{
		T min = null;
		for (final T element : this) {
			if (min == null || key.applyAsDouble(element) < key.applyAsDouble(min)) {
				min = element;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	@Override
	public <C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key) 
	{
		T min = null;
		for (final T element : this) {
			if (min == null || key.apply(element).compareTo(key.apply(min)) < 0) {
				min = element;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	@Override
	public Optional<T> maxByKey(final ToDoubleFunction<T> key) {
		T max = null;
		for (final T element : this) {
			if (max == null || key.applyAsDouble(element) > key.applyAsDouble(max)) {
				max = element;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	@Override
	public <C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key) 
	{
		T max = null;
		for (final T element : this) {
			if (max == null || key.apply(max).compareTo(key.apply(element)) < 0) {
				max = element;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}
	
	@Override
	public boolean allMatch(final Predicate<? super T> predicate)
	{
		for (final T t : this) {
			if (!predicate.test(t)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean anyMatch(final Predicate<? super T> predicate)
	{
		for (final T t : this) {
			if (predicate.test(t)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean noneMatch(final Predicate<? super T> predicate)
	{
		return allMatch(predicate.negate());
	}
	
	@Override
	public int count() 
	{
		int count = 0;
		for (@SuppressWarnings("unused") final T t : this) {
			count++;
		}
		return count;
	}
	
	@Override
	public T reduce(final T id, final BinaryOperator<? super T> reducer) {
		throw new RuntimeException();
	}
	
	@Override
	public Optional<T> reduce(final BinaryOperator<? super T> reducer)
	{
		throw new RuntimeException();

	}
	
	@Override
	public ObjectFlow<T> accumulate(final BinaryOperator<? super T> accumulator)
	{
		throw new RuntimeException();

	}
	
	@Override
	public <R> ObjectFlow<R> accumulate(final R id, final BiFunction<R, T, R> accumulator)
	{
		throw new RuntimeException();

	}
}
