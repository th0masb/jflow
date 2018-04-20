package xawd.jflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow3<T> extends Iterable<T>
{
	static <T> Flow3<T> of(final Iterable<T> src)
	{
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return src.iterator();
			}
		};
	}

	static <T> Flow3<T> from(final IntFunction<T> f, final long elementCount)
	{
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					int count = 0;
					@Override
					public boolean hasNext()
					{
						return count < elementCount;
					}
					@Override
					public T next()
					{
						return f.apply(count++);
					}
				};
			}
		};
	}

	/**
	 * Careful!
	 */
	static <T> Flow3<T> infinite(final IntFunction<T> f)
	{
		return from(f, Long.MAX_VALUE);
	}

	default <R> Flow3<R> map(final Function<? super T, R> f)
	{
		final Flow3<T> src = this;
		return new Flow3<R>()
		{
			@Override
			public Iterator<R> iterator()
			{
				return new Iterator<R>()
				{
					Iterator<T> srcIter = src.iterator();
					@Override
					public boolean hasNext()
					{
						return srcIter.hasNext();
					}
					@Override
					public R next()
					{
						return f.apply(srcIter.next());
					}
				};
			}
		};
	}


	default <R> Flow3<Pair<T, R>> zipWith(final Iterable<R> other)
	{
		final Flow3<T> src = this;
		return new Flow3<Pair<T,R>>()
		{
			@Override
			public Iterator<Pair<T, R>> iterator()
			{
				return new Iterator<Pair<T,R>>()
				{
					Iterator<T> srcT = src.iterator();
					Iterator<R> srcR = other.iterator();
					@Override
					public boolean hasNext()
					{
						return srcT.hasNext() && srcR.hasNext();
					}

					@Override
					public Pair<T, R> next()
					{
						return Pair.of(srcT.next(), srcR.next());
					}
				};
			}
		};
	}

	default <U, R> Flow3<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f)
	{
		final Flow3<T> src = this;
		return new Flow3<R>()
		{
			@Override
			public Iterator<R> iterator()
			{
				return new Iterator<R>()
				{
					Iterator<T> srcT = src.iterator();
					Iterator<U> srcU = other.iterator();
					@Override
					public boolean hasNext()
					{
						return srcT.hasNext() && srcU.hasNext();
					}

					@Override
					public R next()
					{
						return f.apply(srcT.next(), srcU.next());
					}
				};
			}
		};
	}

	default Flow3<IntWith<T>> enumerate()
	{
		final Flow3<T> src = this;
		return new Flow3<IntWith<T>>()
		{
			@Override
			public Iterator<IntWith<T>> iterator()
			{
				return new Iterator<IntWith<T>>()
				{
					Iterator<T> srcT = src.iterator();
					int count = 0;
					@Override
					public boolean hasNext()
					{
						return srcT.hasNext();
					}
					@Override
					public IntWith<T> next()
					{
						return IntWith.of(count++, srcT.next());
					}
				};
			}
		};
	}

	default Flow3<T> take(final int n)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final Flow3<T> src = this;
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					Iterator<T> srcT = src.iterator();
					int count = 0;
					@Override
					public boolean hasNext()
					{
						return srcT.hasNext() && count < n;
					}

					@Override
					public T next()
					{
						count++;
						return srcT.next();
					}
				};
			}
		};
	}

	default Flow3<T> drop(final int n)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final Flow3<T> src = this;
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					Iterator<T> srcT = src.iterator();
					{
						int count = 0;
						while (count++ < n && srcT.hasNext()) {
							srcT.next();
						}
					}
					@Override
					public boolean hasNext()
					{
						return srcT.hasNext();
					}

					@Override
					public T next()
					{
						return srcT.next();
					}
				};
			}
		};
	}

	default Flow3<T> dropWhile(final Predicate<? super T> p)
	{
		final Flow3<T> src = this;
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					Iterator<T> srcT = src.iterator();
					T firstFailure = null;
					{
						while (srcT.hasNext() && firstFailure == null) {
							final T next = srcT.next();
							if (!p.test(next)) {
								firstFailure = next;
							}
						}
					}
					@Override
					public boolean hasNext()
					{
						if (firstFailure == null) {
							return srcT.hasNext();
						}
						else {
							return true;
						}
					}

					@Override
					public T next()
					{
						if (firstFailure == null) {
							return srcT.next();
						}
						else {
							final T ret = firstFailure;
							firstFailure = null;
							return ret;
						}
					}
				};
			}
		};
	}

	default Flow3<T> takeWhile(final Predicate<? super T> p)
	{
		final Flow3<T> src = this;
		return new Flow3<T>()
		{
			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					Iterator<T> srcT = src.iterator();
					T cached = null;
					boolean nextReady = false;
					boolean iteratorExhausted = false;

					@Override
					public boolean hasNext()
					{
						if (nextReady) {
							return true;
						}
						final boolean srcHasNext = srcT.hasNext();
						if (srcHasNext) {
							cached = srcT.next();
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
					public T next()
					{
						if (iteratorExhausted) {
							throw new NoSuchElementException();
						}
						else if (nextReady) {
							nextReady = false;
							return cached;
						}
						else if (cached == null) { // hasnext wasn't called
							if (hasNext()) {
								nextReady = false;
								return cached;
							}
							else {
								throw new NoSuchElementException();
							}
						}
						else {
							throw new AssertionError();
						}
					}
				};
			}
		};
	}

	default boolean allSatisfy(final Predicate<? super T> p)
	{
		for (final T element : this) {
			if (!p.test(element)) {
				return false;
			}
		}
		return true;
	}

	default boolean anySatisfy(final Predicate<? super T> p)
	{
		for (final T element : this) {
			if (p.test(element)) {
				return true;
			}
		}
		return false;
	}

	default <C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key)
	{
		T min = null;
		for (final T element : this) {
			if (min == null || key.apply(element).compareTo(key.apply(min)) < 0) {
				min = element;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	default Optional<T> minByKey(final ToDoubleFunction<? super T> key)
	{
		T min = null;
		for (final T element : this) {
			if (min == null || key.applyAsDouble(element) < key.applyAsDouble(min)) {
				min = element;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	default <C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key)
	{
		T max = null;
		for (final T element : this) {
			if (max == null || key.apply(max).compareTo(key.apply(element)) < 0) {
				max = element;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	default Optional<T> maxByKey(final ToDoubleFunction<T> key)
	{
		T max = null;
		for (final T element : this) {
			if (max == null || key.applyAsDouble(element) > key.applyAsDouble(max)) {
				max = element;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	default <C extends Comparable<C>> Stream<T> sortByObjectKey(final Function<? super T, C> key)
	{
		return stream().sorted((a, b) -> key.apply(a).compareTo(key.apply(b)));
	}

	default Stream<T> sortByKey(final ToDoubleFunction<? super T> key)
	{
		return stream().sorted((a, b) -> Double.compare(key.applyAsDouble(a), key.applyAsDouble(b)));
	}

	default List<T> toList()
	{
		return Collections.unmodifiableList(toArrayList());
	}

	default List<T> toArrayList()
	{
		return toCollection(ArrayList::new);
	}

	default Set<T> toSet()
	{
		return Collections.unmodifiableSet(toHashSet());
	}

	default Set<T> toHashSet()
	{
		return toCollection(HashSet::new);
	}

	default <C extends Collection<T>> C toCollection(final Supplier<C> collectionFactory)
	{
		return stream().collect(Collectors.toCollection(collectionFactory));
	}

	default <K, V> Map<K, V> toMap(final Function<? super T, K> keyMapper, final Function<? super T, V> valueMapper)
	{
		return stream().collect(Collectors.toMap(keyMapper, valueMapper));
	}

	default Stream<T> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}

	public static void main(final String[] args)
	{
		 final Flow3<Integer> xs = Flow3.from(i -> 2*i, 20).dropWhile(i -> i < 10);

		System.out.println(xs.toList());
	}
}
