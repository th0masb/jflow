/**
 *
 */
package xawd.jflow.collections;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.iterables.FlowIterable;
import xawd.jflow.utilities.Optionals;


/**
 * An extension of the List interface adding enhanced iterators to support a
 * more functional style of programming. See {@link Flow}.
 *
 * @param <E>
 *            The type of the elements in this list.
 *
 * @author ThomasB
 */
public interface FList<E> extends List<E>, FlowIterable<E>
{
	@Override
	FList<E> subList(int fromIndex, int toIndex);

	/**
	 * Method for safely retrieving the head of this List.
	 * 
	 * @return The first element in this list if it is non-empty, nothing otherwise.
	 */
	default Optional<E> head()
	{
		return isEmpty() ? Optional.empty() : Optionals.of(get(0));
	}

	/**
	 * Method for safely retrieving the last element of this List.
	 * 
	 * @return The last element in this list if it is non-empty, nothing otherwise.
	 */
	default Optional<E> last()
	{
		return isEmpty() ? Optional.empty() : Optionals.of(get(size() - 1));
	}

	/**
	 * @return An iteration of the elements in this List in reverse order. I.e from
	 *         last to head.
	 */
	default Flow<E> rflow()
	{
		return Iterate.overReversed(this);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#slice(IntUnaryOperator)} method.
	 */
	default Flow<E> slice(IntUnaryOperator indexMap)
	{
		return flow().slice(indexMap);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#take(int)} method.
	 */
	default Flow<E> take(int n)
	{
		return flow().take(n);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#takeWhile(Predicate)} method.
	 */
	default Flow<E> takeWhile(Predicate<? super E> predicate)
	{
		return flow().takeWhile(predicate);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#drop(int)} method.
	 */
	default Flow<E> drop(int n)
	{
		return flow().drop(n);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#dropWhile(Predicate)} method.
	 */
	default Flow<E> dropWhile(Predicate<? super E> predicate)
	{
		return flow().dropWhile(predicate);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#insert(Iterator)} method.
	 */
	default Flow<E> insert(Iterator<? extends E> other)
	{
		return flow().insert(other);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#insert(Iterator)} method.
	 */
	default Flow<E> insert(Iterable<? extends E> other)
	{
		return flow().insert(other.iterator());
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#insert(Object)} method.
	 */
	default Flow<E> insert(E other)
	{
		return flow().insert(other);
	}

	/**
	 * Finds the first element (if any) which satisfies a given predicate.
	 * 
	 * @param predicate
	 *            The predicate which will be used to test elements.
	 * @return The first element to pass the predicate test if there is one, nothing
	 *         otherwise.
	 */
	default Optional<E> findFirst(Predicate<? super E> predicate)
	{
		return filter(predicate).safeNext();
	}
}
