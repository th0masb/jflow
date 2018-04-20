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

	Flow<T> reverse();

	Flow<T> take(final int n);

	Flow<T> takeWhile(final Predicate<? super T> p);

	Flow<T> drop(final int n);

	Flow<T> dropWhile(final Predicate<? super T> p);

	Pair<Flow<T>, Flow<T>> splitAt(int index);

	Pair<Flow<T>, Flow<T>> span(final Predicate<? super T> p);

	Pair<Flow<T>, Flow<T>> rSpan(final Predicate<? super T> p);

	Flow<T> filter(final Predicate<? super T> p);

	Flow<T> append(Iterable<? extends T> other);

	Flow<T> insert(Iterable<? extends T> other);

	Optional<T> minByKey(final ToDoubleFunction<? super T> key);

	<C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key);

	Optional<T> maxByKey(final ToDoubleFunction<T> key);

	<C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key);

	Stream<T> sortByKey(final ToDoubleFunction<? super T> key);

	<C extends Comparable<C>> Stream<T> sortByObjectKey(final Function<? super T, C> key);

	//-------------------------------

	default Stream<T> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}

	default boolean allMatch(final Predicate<? super T> predicate)
	{
		return stream().allMatch(predicate);
	}

	default boolean anyMatch(final Predicate<? super T> predicate)
	{
		return stream().anyMatch(predicate);
	}

	default boolean noneMatch(final Predicate<? super T> predicate)
	{
		return stream().noneMatch(predicate);
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
