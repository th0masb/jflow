package xawd.jflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.SkippableIterator;
import xawd.jflow.primitiveiterables.IterableDoubles;
import xawd.jflow.primitiveiterables.IterableInts;
import xawd.jflow.primitiveiterables.IterableLongs;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow<T> extends SkippableIterator<T>
{
	Flow<T> slice(final IntUnaryOperator f);

	<R> Flow<R> map(final Function<? super T, R> f);

	IntFlow mapToInt(ToIntFunction<? super T> f);

	DoubleFlow mapToDouble(ToDoubleFunction<? super T> f);

	LongFlow mapToLong(ToLongFunction<? super T> f);

	<R> Flow<Pair<T, R>> zipWith(final Iterator<R> other);

	Flow<IntWith<T>> zipWith(final PrimitiveIterator.OfInt other);

	Flow<DoubleWith<T>> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<LongWith<T>> zipWith(final PrimitiveIterator.OfLong other);

	<U, R> Flow<R> combineWith(final Iterator<U> other, final BiFunction<T, U, R> f);

	Flow<IntWith<T>> enumerate();

	Flow<T> take(final int n);

	Flow<T> takeWhile(final Predicate<? super T> p);

	Flow<T> drop(final int n);

	Flow<T> dropWhile(final Predicate<? super T> p);

	Flow<T> filter(final Predicate<? super T> p);

	Flow<T> append(Iterator<? extends T> other);

	Flow<T> append(@SuppressWarnings("unchecked") T... ts);

	Flow<T> insert(Iterator<? extends T> other);

	Flow<T> insert(@SuppressWarnings("unchecked") T... ts);

	Optional<T> minByKey(final ToDoubleFunction<? super T> key);

	<C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key);

	Optional<T> maxByKey(final ToDoubleFunction<T> key);

	<C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key);

	boolean allMatch(final Predicate<? super T> predicate);

	boolean anyMatch(final Predicate<? super T> predicate);

	boolean noneMatch(final Predicate<? super T> predicate);

	int count();

	T reduce(T id, BinaryOperator<T> reducer);

	Optional<T> reduce(BinaryOperator<T> reducer);

	Flow<T> accumulate(BinaryOperator<T> accumulator);

	<R> Flow<R> accumulate(R id, BiFunction<R, T, R> accumulator);

	default <C extends Collection<T>> C toCollection(final Supplier<C> collectionFactory)
	{
		final C container = collectionFactory.get();
		while (hasNext()) {
			container.add(next());
		}
		return container;
	}

	default List<T> toImmutableList()
	{
		return Collections.unmodifiableList(toList());
	}

	default List<T> toList()
	{
		return toCollection(ArrayList::new);
	}

	default Set<T> toImmutableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}

	default Set<T> toSet()
	{
		return toCollection(HashSet::new);
	}

	default <K, V> Map<K, V> toMap(final Function<? super T, K> keyMapper, final Function<? super T, V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (hasNext()) {
			final T next = next();
			final K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			}
			else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	default <K> Map<K, List<T>> groupBy(final Function<? super T, K> classifier)
	{
		final Map<K, List<T>> collected = new HashMap<>();
		while (hasNext()) {
			final T next = next();
			final K key = classifier.apply(next);
			if (collected.containsKey(key)) {
				collected.get(key).add(next);
			}
			else {
				final List<T> newClassification = new ArrayList<>();
				newClassification.add(next);
				collected.put(key, newClassification);
			}
		}
		return collected;
	}

	default <R> Flow<Pair<T, R>> zipWith(final Iterable<R> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntWith<T>> zipWith(final IterableInts other)
	{
		return zipWith(other.iterator());
	}

	default Flow<DoubleWith<T>> zipWith(final IterableDoubles other)
	{
		return zipWith(other.iterator());
	}

	default Flow<LongWith<T>> zipWith(final IterableLongs other)
	{
		return zipWith(other.iterator());
	}

	default <U, R> Flow<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f)
	{
		return combineWith(other.iterator(), f);
	}

	default Flow<T> append(final Iterable<? extends T> other)
	{
		return append(other.iterator());
	}

	default Flow<T> insert(final Iterable<? extends T> other)
	{
		return insert(other.iterator());
	}
}
