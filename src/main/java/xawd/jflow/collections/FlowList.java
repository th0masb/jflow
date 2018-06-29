/**
 *
 */
package xawd.jflow.collections;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * An instance of this interface has all the functionality of a standard Java
 * List along with enhanced iterators and functional programming style methods
 * implemented by delegating to these enhanced iterators. See {@link Flow}.
 *
 * @author ThomasB
 */
public interface FlowList<E> extends List<E>, FlowIterable<E>
{
	@Override
	FlowList<E> subList(int fromIndex, int toIndex);

	/**
	 * @return An iteration of the elements in this List in reverse order. I.e from
	 *         tail to head.
	 */
	default Flow<E> reverse()
	{
		return Iterate.reverseOver(this);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#slice(IntUnaryOperator)} method.
	 */
	default Flow<E> slice(final IntUnaryOperator indexMap)
	{
		return flow().slice(indexMap);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#take(int)} method.
	 */
	default Flow<E> take(final int n)
	{
		return flow().take(n);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#takeWhile(Predicate)} method.
	 */
	default Flow<E> takeWhile(final Predicate<? super E> predicate)
	{
		return flow().takeWhile(predicate);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#drop(int)} method.
	 */
	default Flow<E> drop(final int n)
	{
		return flow().drop(n);
	}

	/**
	 * A convenience method which spawns a Flow and delegates to its
	 * {@link Flow#dropWhile(Predicate)} method.
	 */
	default Flow<E> dropWhile(final Predicate<? super E> predicate)
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
}
