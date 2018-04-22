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
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.SkippableIterator;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface ObjectFlow<T> extends SkippableIterator<T>
{
	<R> ObjectFlow<R> map(final Function<? super T, R> f);

	IntFlow mapToInt(ToIntFunction<? super T> f);

	DoubleFlow mapToDouble(ToDoubleFunction<? super T> f);

	LongFlow mapToLong(ToLongFunction<? super T> f);

	<R> ObjectFlow<Pair<T, R>> zipWith(final Iterator<R> other);

	ObjectFlow<IntWith<T>> zipWith(final PrimitiveIterator.OfInt other);

	ObjectFlow<DoubleWith<T>> zipWith(final PrimitiveIterator.OfDouble other);

	ObjectFlow<LongWith<T>> zipWith(final PrimitiveIterator.OfLong other);

	<U, R> ObjectFlow<R> combineWith(final Iterator<U> other, final BiFunction<T, U, R> f);

	ObjectFlow<IntWith<T>> enumerate();

	ObjectFlow<T> take(final int n);

	ObjectFlow<T> takeWhile(final Predicate<? super T> p);

	ObjectFlow<T> drop(final int n);

	ObjectFlow<T> dropWhile(final Predicate<? super T> p);

	ObjectFlow<T> filter(final Predicate<? super T> p);

	ObjectFlow<T> append(Iterator<? extends T> other);
	
	ObjectFlow<T> append(T t);

	ObjectFlow<T> insert(Iterator<? extends T> other);
	
	ObjectFlow<T> insert(T t);

	Optional<T> minByKey(final ToDoubleFunction<? super T> key);

	<C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key);

	Optional<T> maxByKey(final ToDoubleFunction<T> key);

	<C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key);
	
	boolean allMatch(final Predicate<? super T> predicate);

	boolean anyMatch(final Predicate<? super T> predicate);

	boolean noneMatch(final Predicate<? super T> predicate);
	
	int count();
	
	T reduce(T id, BinaryOperator<? super T> reducer);
	
	Optional<T> reduce(BinaryOperator<? super T> reducer);
	
	ObjectFlow<T> accumulate(BinaryOperator<? super T> accumulator);
	
	<R> ObjectFlow<R> accumulate(R id, BiFunction<R, T, R> accumulator);
	
//	default <E extends T> ObjectFlow<E> filterClassInstances(final Class<E> klass)
//	{
//		return filter(klass::isInstance).map(klass::cast);
//	}
	
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
	
//	default Stream<T> stream()
//	{
//		return StreamSupport.stream(spliterator(), false);
//	}
//	
//	default Stream<T> sortByKey(final ToLongFunction<? super T> key)
//	{
//		return stream().sorted((a, b) -> {
//			final long aMap = key.applyAsLong(a), bMap = key.applyAsLong(b);
//			return aMap < bMap ? -1 : a == b? 0 : 1 ;
//		});
//	}
//
//	default <C extends Comparable<C>> Stream<T> sortByObjectKey(final Function<? super T, C> key)
//	{
//		return stream().sorted((a, b) -> key.apply(a).compareTo(key.apply(b)));
//	}
}
