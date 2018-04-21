package xawd.jflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import xawd.jflow.iterators.SkippableIntIterator;
import xawd.jflow.primitiveiterables.IterableInts;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends IterableInts
{
	@Override
	SkippableIntIterator iterator();
	
	IntFlow map(final IntUnaryOperator f);

	<T> ObjectFlow<T> mapToObject(IntFunction<T> f);

	DoubleFlow mapToDouble(IntToDoubleFunction f);

	LongFlow mapToLong(IntToLongFunction f);

	<T> ObjectFlow<IntWith<T>> zipWith(final Iterable<T> other);

//	ObjectFlow zipWith(final PrimitiveIterator.OfInt other);
//
//	ObjectFlow zipWith(final PrimitiveIterator.OfDouble other);
//
//	ObjectFlow zipWith(final PrimitiveIterator.OfLong other);

//	<U, R> ObjectFlow combineWith(final Iterable other, final BiFunction<T, U, R> f);

	ObjectFlow<IntPair> enumerate();

	IntFlow cycle();

	IntFlow take(final int n);

	IntFlow takeWhile(final IntPredicate p);

	IntFlow drop(final int n);

	IntFlow dropWhile(final IntPredicate p);

	Pair<IntFlow, IntFlow> split(int leftSize);

	Pair<IntFlow, IntFlow> splitByPredicate(final IntPredicate p);

	IntFlow filter(final IntPredicate p);

	IntFlow append(IterableInts other);
	
	IntFlow append(int x);

	IntFlow insert(IterableInts other);
	
	IntFlow insert(int x);

	OptionalInt minByKey(final IntToDoubleFunction key);

	 Optional minByObjectKey(final Function<? super T, C> key);

	Optional maxByKey(final ToDoubleFunction key);

	 Optional maxByObjectKey(final Function<? super T, C> key);
	
	boolean allMatch(final IntPredicate predicate);

	boolean anyMatch(final IntPredicate predicate);

	boolean noneMatch(final IntPredicate predicate);
	
	int count();
	
	T reduce(T id, BinaryOperator<? super T> reducer);
	
	Optional reduce(BinaryOperator<? super T> reducer);
	
	ObjectFlow accumulate(BinaryOperator<? super T> accumulator);
	
	 ObjectFlow accumulate(R id, BiFunction<R, T, R> accumulator);
	//-------------------------------
	
	default IntStream stream()
	{
		return StreamSupport.intStream(spliterator(), false) ;
	}
	
	default Stream sortByKey(final ToLongFunction<? super T> key)
	{
		return stream().sorted((a, b) -> {
			final long aMap = key.applyAsLong(a), bMap = key.applyAsLong(b);
			return aMap < bMap ? -1 : a == b? 0 : 1 ;
		});
	}

	default  Stream sortByObjectKey(final Function<? super T, C> key)
	{
		return stream().sorted((a, b) -> key.apply(a).compareTo(key.apply(b)));
	}

	default  C toCollection(final Supplier collectionFactory)
	{
		return stream().collect(Collectors.toCollection(collectionFactory));
	}

	default List toList()
	{
		return Collections.unmodifiableList(toArrayList());
	}

	default List toArrayList()
	{
		return toCollection(ArrayList::new);
	}

	default Set toSet()
	{
		return Collections.unmodifiableSet(toHashSet());
	}

	default Set toHashSet()
	{
		return toCollection(HashSet::new);
	}

	default <K, V> Map<K, V> toMap(final Function<? super T, K> keyMapper, final Function<? super T, V> valueMapper)
	{
		return stream().collect(Collectors.toMap(keyMapper, valueMapper));
	}

	default  Map<K, List> groupBy(final Function<? super T, K> classifier)
	{
		return stream().collect(Collectors.groupingBy(classifier));
	}
}
