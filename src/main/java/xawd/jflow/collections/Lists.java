/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;

import xawd.jflow.collections.impl.FlowArrayList;
import xawd.jflow.collections.impl.ImmutableFlowList;

/**
 * A collection of static factory methods pertaining to the creation of
 * {@link FlowList} instances.
 *
 * @author ThomasB
 */
public final class Lists
{
	private Lists()
	{
	}

	/**
	 * Create an immutable FlowList containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FlowList.
	 * @return An immutable FlowList containing all the specified elements.
	 */
	@SafeVarargs
	public static <E> FlowList<E> build(E... elements)
	{
		return new ImmutableFlowList<>(elements);
	}

	/**
	 * Create an immutable FlowList containing elements in the parameter Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return An immutable FlowList containing the same references as in the
	 *         parameter Collection.
	 */
	public static <E> FlowList<E> copy(Collection<? extends E> src)
	{
		return new ImmutableFlowList<>(src);
	}

	/**
	 * Create an mutable FlowList containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FlowList.
	 * @return n mutable FlowList containing all the specified elements.
	 */
	@SafeVarargs
	public static <E> FlowList<E> buildMutable(E... elements)
	{
		final FlowList<E> mutable = new FlowArrayList<>(elements.length);
		for (final E element : elements) {
			mutable.add(element);
		}
		return mutable;
	}

	/**
	 * Create an mutable FlowList containing elements in the parameter Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return A mutable FlowList containing the same references as in the parameter
	 *         Collection.
	 */
	public static <E> FlowList<E> copyMutable(Collection<? extends E> src)
	{
		return new FlowArrayList<>(src);
	}
}
