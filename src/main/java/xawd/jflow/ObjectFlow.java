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
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import xawd.jflow.iterators.SkippableIterator;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface ObjectFlow<T> extends Iterable<T>
{
	@Override
	SkippableIterator<T> iterator();
	
	<R> ObjectFlow<R> map(final Function<? super T, R> f);

	IntFlow mapToInt(ToIntFunction<? extends T> f);

	DoubleFlow mapToDouble(ToDoubleFunction<? extends T> f);

	LongFlow mapToLong(ToLongFunction<? extends T> f);

	<R> ObjectFlow<Pair<T, R>> zipWith(final Iterable<R> other);

	ObjectFlow<IntWith<T>> zipWith(final PrimitiveIterator.OfInt other);

	ObjectFlow<DoubleWith<T>> zipWith(final PrimitiveIterator.OfDouble other);

	ObjectFlow<LongWith<T>> zipWith(final PrimitiveIterator.OfLong other);

	<U, R> ObjectFlow<R> combineWith(final Iterable<U> other, final BiFunction<T, U, R> f);

	ObjectFlow<IntWith<T>> enumerate();

	ObjectFlow<T> cycle();

	ObjectFlow<T> take(final int n);

	ObjectFlow<T> takeWhile(final Predicate<? super T> p);

	ObjectFlow<T> drop(final int n);

	ObjectFlow<T> dropWhile(final Predicate<? super T> p);

	Pair<ObjectFlow<T>, ObjectFlow<T>> split(int leftSize);

	Pair<ObjectFlow<T>, ObjectFlow<T>> splitByPredicate(final Predicate<? super T> p);

	ObjectFlow<T> filter(final Predicate<? super T> p);

	ObjectFlow<T> append(Iterable<? extends T> other);
	
	ObjectFlow<T> append(T t);

	ObjectFlow<T> insert(Iterable<? extends T> other);
	
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
	//-------------------------------
	
	default <E extends T> ObjectFlow<E> filterClassInstances(final Class<E> klass)
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
