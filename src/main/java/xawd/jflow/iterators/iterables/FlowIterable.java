package xawd.jflow.iterators.iterables;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	default <K> Map<K, List<E>> groupBy(final Function<? super E, K> classifier)
	{
		return flow().groupBy(classifier);
	}
}
