package xawd.jflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow<T> extends Iterable<T>
{
	@Override
	SkippableIterator<T> iterator();
	
	<R> Flow<R> map(final Function<? super T, R> f);

	IntFlow mapToInt(ToIntFunction<? extends T> f);

	DoubleFlow mapToDouble(ToDoubleFunction<? extends T> f);

	LongFlow mapToLong(ToLongFunction<? extends T> f);

	<R> Flow<Pair<T, R>> zipWith(final Iterable<R> other);

	Flow<IntWith<T>> zipWith(final PrimitiveIterator.OfInt other);

	Flow<DoubleWith<T>> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<LongWith<T>> zipWith(final PrimitiveIterator.OfLong other);

	<U, R> Flow<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f);

	Flow<IntWith<T>> enumerate();

	Flow<T> cycle();

	Flow<T> take(final int n);

	Flow<T> takeWhile(final Predicate<? super T> p);

	Flow<T> drop(final int n);

	Flow<T> dropWhile(final Predicate<? super T> p);

	Pair<Flow<T>, Flow<T>> split(int leftSize);

	Pair<Flow<T>, Flow<T>> splitByPredicate(final Predicate<? super T> p);

	Flow<T> filter(final Predicate<? super T> p);

	Flow<T> append(Iterable<? extends T> other);
	
	Flow<T> append(T t);

	Flow<T> insert(Iterable<? extends T> other);
	
	Flow<T> insert(T t);

	Optional<T> minByKey(final ToDoubleFunction<? super T> key);

	<C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key);

	Optional<T> maxByKey(final ToDoubleFunction<T> key);

	<C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key);
	
	boolean allMatch(final Predicate<? super T> predicate);

	boolean anyMatch(final Predicate<? super T> predicate);

	boolean noneMatch(final Predicate<? super T> predicate);

	//-------------------------------
	
	default <E extends T> Flow<E> filterClassInstances(final Class<E> klass)
	{
		return filter(klass::isInstance).map(klass::cast);
	}
	
	default Stream<T> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}
	
	default Stream<T> sortByKey(final ToLongFunction<? super T> key)
	{
		return stream().sorted((a, b) -> {
			final long aMap = key.applyAsLong(a), bMap = key.applyAsLong(b);
			return aMap < bMap ? -1 : a == b? 0 : 1 ;
		});
	}

	default <C extends Comparable<C>> Stream<T> sortByObjectKey(final Function<? super T, C> key)
	{
		return stream().sorted((a, b) -> key.apply(a).compareTo(key.apply(b)));
	}

	default <C extends Collection<T>> C toCollection(final Supplier<C> collectionFactory)
	{
		return stream().collect(Collectors.toCollection(collectionFactory));
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

	default <K, V> Map<K, V> toMap(final Function<? super T, K> keyMapper, final Function<? super T, V> valueMapper)
	{
		return stream().collect(Collectors.toMap(keyMapper, valueMapper));
	}

	default <K> Map<K, List<T>> groupBy(final Function<? super T, K> classifier)
	{
		return stream().collect(Collectors.groupingBy(classifier));
	}
}
