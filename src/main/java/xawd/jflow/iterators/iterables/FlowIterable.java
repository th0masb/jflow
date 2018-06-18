package xawd.jflow.iterators.iterables;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.misc.IntWith;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface FlowIterable<E> extends Iterable<E>
{
	@Override
	Flow<E> iterator();

	default Flow<E> flow()
	{
		return iterator();
	}

	default <R> Flow<R> map(Function<? super E, R> mappingFunction)
	{
		return flow().map(mappingFunction);
	}

	default IntFlow mapToInt(ToIntFunction<? super E> mappingFunction)
	{
		return flow().mapToInt(mappingFunction);
	}

	default DoubleFlow mapToDouble(ToDoubleFunction<? super E> mappingFunction)
	{
		return flow().mapToDouble(mappingFunction);
	}

	default LongFlow mapToLong(ToLongFunction<? super E> mappingFunction)
	{
		return flow().mapToLong(mappingFunction);
	}

	default <R> Flow<R> flatten(Function<? super E, ? extends Flow<R>> mapping)
	{
		return flow().flatten(mapping);
	}

	default Flow<E> filter(Predicate<? super E> predicate)
	{
		return flow().filter(predicate);
	}

	default Flow<IntWith<E>> enumerate()
	{
		return flow().enumerate();
	}

	/*
	 * index related functions should go in ListFlow interface
	 */
//	default Flow<E> slice(final IntUnaryOperator indexMap)
//	{
//		return flow().slice(indexMap);
//	}
//
//	default Flow<E> take(final int n)
//	{
//		return flow().take(n);
//	}
//
//	default Flow<E> takeWhile(final Predicate<? super E> predicate)
//	{
//		return flow().takeWhile(predicate);
//	}
//
//	default Flow<E> drop(final int n)
//	{
//		return flow().drop(n);
//	}
//
//	default Flow<E> dropWhile(final Predicate<? super E> predicate)
//	{
//		return flow().dropWhile(predicate);
//	}

	default Flow<E> append(Iterator<? extends E> other)
	{
		return flow().append(other);
	}

	default Flow<E> append(Iterable<? extends E> other)
	{
		return flow().append(other.iterator());
	}

	default Flow<E> append(E other)
	{
		return flow().append(other);
	}

	/*
	 * Put in ListFlow since it requires notion of a 'start point' in a collection
	 */
//	default Flow<E> insert(Iterator<? extends E> other)
//	{
//		return flow().insert(other);
//	}
//
//	default Flow<E> insert(Iterable<? extends E> other)
//	{
//		return flow().insert(other.iterator());
//	}
//
//	default Flow<E> insert(E other)
//	{
//		return flow().insert(other);
//	}

	default Optional<E> min(Comparator<? super E> orderingFunction)
	{
		return flow().min(orderingFunction);
	}

	default Optional<E> max(Comparator<? super E> orderingFunction)
	{
		return flow().max(orderingFunction);
	}

	default boolean areAllEqual()
	{
		return flow().areAllEqual();
	}

	default boolean allMatch(final Predicate<? super E> condition)
	{
		return flow().allMatch(condition);
	}

	default boolean anyMatch(final Predicate<? super E> condition)
	{
		return flow().anyMatch(condition);
	}

	default boolean noneMatch(final Predicate<? super E> condition)
	{
		return flow().noneMatch(condition);
	}

	default <R> Flow<R> filterAndCastTo(final Class<R> klass)
	{
		return flow().filterAndCastTo(klass);
	}
}
