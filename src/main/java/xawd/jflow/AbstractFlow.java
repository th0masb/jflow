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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author t
 *
 */
public abstract class AbstractFlow<T> implements Flow<T> 
{
	@Override
	public <R> Flow<R> map(final Function<? super T, R> f) 
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<R>() {
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
	public <R> Flow<Pair<T, R>> zipWith(final Iterable<R> other) 
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<Pair<T, R>>() {
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
	public Flow<IntWith<T>> zipWith(final OfInt other) 
	{
		throw new RuntimeException();
	}

	@Override
	public Flow<DoubleWith<T>> zipWith(final OfDouble other) 
	{
		throw new RuntimeException();
	}

	@Override
	public Flow<LongWith<T>> zipWith(final OfLong other) 
	{
		throw new RuntimeException();
	}

	@Override
	public <U, R> Flow<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f)
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<R>() {
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
	public Flow<IntWith<T>> enumerate() 
	{
		final Flow<T> src = this;

		return new AbstractFlow<IntWith<T>>() {
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
	public Flow<T> cycle() 
	{
		final AbstractFlow<T> src = this;

		return new AbstractFlow<T>() {
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
	public Flow<T> take(final int n) 
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		final Flow<T> src = this;
		return new AbstractFlow<T>() {
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
	public Flow<T> takeWhile(final Predicate<? super T> p) 
	{
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
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
	public Flow<T> drop(final int n) 
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		final Flow<T> src = this;
		return new AbstractFlow<T>() {
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
	public Flow<T> dropWhile(final Predicate<? super T> p) 
	{
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
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
	public Pair<Flow<T>, Flow<T>> split(final int leftSize) 
	{
		if (leftSize < 0) {
			throw new IllegalArgumentException();
		}
		return Pair.of(take(leftSize), drop(leftSize));
	}

	@Override
	public Pair<Flow<T>, Flow<T>> splitByPredicate(final Predicate<? super T> p)
	{
		return Pair.of(takeWhile(p), dropWhile(p));
	}


	@Override
	public Flow<T> filter(final Predicate<? super T> p) 
	{
		final AbstractFlow<T> src = this;
		
		return new AbstractFlow<T>() {
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
	public Flow<T> append(final Iterable<? extends T> other) 
	{
		final AbstractFlow<T> src = this;
		return new AbstractFlow<T>() {
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
	public Flow<T> append(final T t) 
	{
		return append(Arrays.asList(t));
	}

	@Override
	public Flow<T> insert(final Iterable<? extends T> other) {
		final AbstractFlow<T> src = this;
		return new AbstractFlow<T>() {
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
	public Flow<T> insert(final T t) 
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
}
