/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;

import xawd.jflow.collections.impl.FlowArrayList;
import xawd.jflow.collections.impl.ImmutableFlowList;

/**
 * A collection of static factory methods for creating {@link FList}
 * instances.
 *
 * @author ThomasB
 */
public final class Lists
{
	private Lists()
	{
	}

	/**
	 * Create an immutable array-backed FList containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FList.
	 * @return An immutable FList containing all the specified elements.
	 * @throws IllegalStateException
	 *             if a null reference is passed.
	 */
	@SafeVarargs
	public static <E> FList<E> build(E... elements)
	{
		return new ImmutableFlowList<>(elements);
	}
	
	/**
	 * Create an immutable array-backed FList containing elements in the
	 * parameter Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return An immutable FList containing the same references as in the
	 *         parameter Collection.
	 * @throws IllegalStateException
	 *             if the source collection contains a null reference.
	 */
	public static <E> FList<E> copy(Collection<? extends E> src)
	{
		return new ImmutableFlowList<>(src);
	}
	
	/**
	 * Create an immutable empty FList.
	 * 
	 * @return an empty FList.
	 */
	public static <E> FList<E> empty()
	{
		return new ImmutableFlowList<E>();
	}

	/**
	 * Create a mutable array-backed FList containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FList.
	 * @return n mutable FList containing all the specified elements.
	 */
	@SafeVarargs
	public static <E> FList<E> buildMutable(E... elements)
	{
		final FList<E> mutable = new FlowArrayList<>(elements.length);
		for (final E element : elements) {
			mutable.add(element);
		}
		return mutable;
	}

	/**
	 * Create a mutable array-backed FList containing elements in the parameter
	 * Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return A mutable FList containing the same references as in the parameter
	 *         Collection.
	 */
	public static <E> FList<E> copyMutable(Collection<? extends E> src)
	{
		return new FlowArrayList<>(src);
	}
	
	/**
	 * Create a mutable array-backed FList which is initially empty.
	 * 
	 * @return an empty mutable FList.
	 */
	public static <E> FList<E> emptyMutable()
	{
		return new FlowArrayList<E>();
	}
}
