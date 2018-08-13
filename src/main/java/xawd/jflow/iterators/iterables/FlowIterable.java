package xawd.jflow.iterators.iterables;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.misc.Bool;
import xawd.jflow.iterators.misc.IntWith;


/**
 * Abstraction of iterable object which can construct enhanced iterators
 * ({@link Flow}).
 *
 * @param <E>
 *            The type of element this object can iterate over.
 *
 * @author ThomasB
 */
@FunctionalInterface
public interface FlowIterable<E> extends Iterable<E>
{
	@Override
	Flow<E> iterator();

	/**
	 * @return A Flow over the elements in this iterable.
	 */
	default Flow<E> flow()
	{
		return iterator();
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#map(Function)} method.
	 */
	default <R> Flow<R> map(Function<? super E, R> mappingFunction)
	{
		return flow().map(mappingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#mapToInt(ToIntFunction)} method.
	 */
	default IntFlow mapToInt(ToIntFunction<? super E> mappingFunction)
	{
		return flow().mapToInt(mappingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#mapToDouble(ToDoubleFunction)} method.
	 */
	default DoubleFlow mapToDouble(ToDoubleFunction<? super E> mappingFunction)
	{
		return flow().mapToDouble(mappingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#mapToLong(ToLongFunction)} method.
	 */
	default LongFlow mapToLong(ToLongFunction<? super E> mappingFunction)
	{
		return flow().mapToLong(mappingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#flatten(Function)} method.
	 */
	default <R> Flow<R> flatten(Function<? super E, ? extends Flow<R>> mapping)
	{
		return flow().flatten(mapping);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#filter(Predicate)} method.
	 */
	default Flow<E> filter(Predicate<? super E> predicate)
	{
		return flow().filter(predicate);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#enumerate()} method.
	 */
	default Flow<IntWith<E>> enumerate()
	{
		return flow().enumerate();
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#append(Iterator)} method.
	 */
	default Flow<E> append(Iterator<? extends E> other)
	{
		return flow().append(other);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#append(Iterator)} method.
	 */
	default Flow<E> append(Iterable<? extends E> other)
	{
		return flow().append(other.iterator());
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#append(Object)} method.
	 */
	default Flow<E> append(E other)
	{
		return flow().append(other);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#min(Comparator)} method.
	 */
	default Optional<E> min(Comparator<? super E> orderingFunction)
	{
		return flow().min(orderingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#max(Comparator)} method.
	 */
	default Optional<E> max(Comparator<? super E> orderingFunction)
	{
		return flow().max(orderingFunction);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#areAllEqual()} method.
	 */
	default boolean areAllEqual()
	{
		return flow().areAllEqual();
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#allMatch(Predicate)} method.
	 */
	default boolean allMatch(Predicate<? super E> condition)
	{
		return flow().allMatch(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#allMatch2(Predicate)} method.
	 */
	default Bool allMatch2(Predicate<? super E> condition)
	{
		return flow().allMatch2(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#anyMatch(Predicate)} method.
	 */
	default boolean anyMatch(Predicate<? super E> condition)
	{
		return flow().anyMatch(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#anyMatch2(Predicate)} method.
	 */
	default Bool anyMatch2(Predicate<? super E> condition)
	{
		return flow().anyMatch2(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#noneMatch(Predicate)} method.
	 */
	default boolean noneMatch(Predicate<? super E> condition)
	{
		return flow().noneMatch(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#noneMatch(Predicate)} method.
	 */
	default Bool noneMatch2(Predicate<? super E> condition)
	{
		return flow().noneMatch2(condition);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#filterAndCastTo(Class)} method.
	 */
	default <R> Flow<R> filterAndCastTo(Class<R> klass)
	{
		return flow().filterAndCastTo(klass);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#groupBy(Function)} method.
	 */
	default <K> Map<K, List<E>> groupBy(Function<? super E, K> classifier)
	{
		return flow().groupBy(classifier);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#fold(Object, BiFunction)} method.
	 */
	default <R> R fold(R id, BiFunction<R, E, R> reducer)
	{
		return flow().fold(id, reducer);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#reduce(BinaryOperator)} method.
	 */
	default Optional<E> reduce(BinaryOperator<E> reducer)
	{
		return flow().reduce(reducer);
	}
}
