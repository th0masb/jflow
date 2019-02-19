package maumay.jflow.iterators.iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import maumay.jflow.iterators.EnhancedIterator;

/**
 * Abstraction of iterable object which can construct enhanced iterators
 * ({@link EnhancedIterator}).
 *
 * @param <E> The type of element this object can iterate over.
 *
 * @author ThomasB
 */
@FunctionalInterface
public interface EnhancedIterable<E> extends Iterable<E>
{
	/**
	 * @return An {@link EnhancedIterator} traversing the elements in this iterable.
	 */
	EnhancedIterator<E> iter();

	@Override
	default EnhancedIterator<E> iterator()
	{
		return iter();
	}

	/**
	 * Finds the first element (if any) which satisfies a given predicate.
	 * 
	 * @param predicate The predicate which will be used to test elements.
	 * @return The first element to pass the predicate test if there is one, nothing
	 *         otherwise.
	 */
	default Optional<E> findFirst(Predicate<? super E> predicate)
	{
		return iter().filter(predicate).nextOption();
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#min(Comparator)} method.
	 */
	default Optional<E> min(Comparator<? super E> orderingFunction)
	{
		return iter().min(orderingFunction);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#max(Comparator)} method.
	 */
	default Optional<E> max(Comparator<? super E> orderingFunction)
	{
		return iter().max(orderingFunction);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#areAllEqual()} method.
	 */
	default boolean areAllEqual()
	{
		return iter().areAllEqual();
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#allMatch(Predicate)} method.
	 */
	default boolean allMatch(Predicate<? super E> condition)
	{
		return iter().allMatch(condition);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#anyMatch(Predicate)} method.
	 */
	default boolean anyMatch(Predicate<? super E> condition)
	{
		return iter().anyMatch(condition);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#noneMatch(Predicate)} method.
	 */
	default boolean noneMatch(Predicate<? super E> condition)
	{
		return iter().noneMatch(condition);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#groupBy(Function)} method.
	 */
	default <K> Map<K, List<E>> groupBy(Function<? super E, K> classifier)
	{
		return iter().groupBy(classifier);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#fold(Object, BiFunction)} method.
	 */
	default <R> R fold(R id, BiFunction<R, E, R> reducer)
	{
		return iter().fold(id, reducer);
	}

	/**
	 * Spawns an enhanced iterator and delegates to its
	 * {@link EnhancedIterator#foldOption(BinaryOperator)} method.
	 */
	default Optional<E> foldOption(BinaryOperator<E> reducer)
	{
		return iter().foldOption(reducer);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#fold(BinaryOperator)} method.
	 */
	default E fold(BinaryOperator<E> reducer)
	{
		return iter().fold(reducer);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#toMap(Function, Function)} method.
	 */
	default <K, V> Map<K, V> toMap(Function<? super E, ? extends K> keyMap,
			Function<? super E, ? extends V> valueMap)
	{
		return iter().toMap(keyMap, valueMap);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#toMap(Function)} method.
	 */
	default <V> Map<E, V> toMap(Function<? super E, ? extends V> valueMap)
	{
		return iter().toMap(valueMap);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#toList()} method.
	 */
	default List<E> toList()
	{
		return toCollection(ArrayList::new);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#toSet()} method.
	 */
	default Set<E> toSet()
	{
		return toCollection(HashSet::new);
	}

	/**
	 * Spawns an enhanced iterator and delegates to it's
	 * {@link EnhancedIterator#toCollection(Supplier)} method.
	 */
	default <C extends Collection<E>> C toCollection(Supplier<C> collectionFactory)
	{
		return iter().toCollection(collectionFactory);
	}
}
