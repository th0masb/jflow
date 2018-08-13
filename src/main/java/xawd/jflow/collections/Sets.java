/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;
import java.util.Collections;

import xawd.jflow.collections.impl.FlowHashSet;
import xawd.jflow.collections.impl.UnmodifiableDelegatingFlowSet;

/**
 * A collection of static factory methods for creating {@link FSet}
 * instances.
 *
 * @author ThomasB
 */
public final class Sets
{
	private Sets()
	{
	}

	/**
	 * Create an immutable FSet containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FSet.
	 * @return An immutable FSet containing all the specified elements.
	 */
	@SafeVarargs
	public static <E> FSet<E> build(E... elements)
	{
		final FSet<E> mutable = new FlowHashSet<>();
		for (final E element : elements) {
			mutable.add(element);
		}
		return new UnmodifiableDelegatingFlowSet<>(mutable);
	}

	/**
	 * Create an immutable FSet containing elements in the parameter Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return An immutable FSet containing the same references as in the
	 *         parameter Collection.
	 */
	public static <E> FSet<E> copy(Collection<? extends E> src)
	{
		final FSet<E> mutable = new FlowHashSet<>(src);
		return new UnmodifiableDelegatingFlowSet<>(mutable);
	}
	
	/**
	 * Create an immutable empty set.
	 */
	public static <E> FSet<E> empty()
	{
		return new UnmodifiableDelegatingFlowSet<>(Collections.emptySet());
	}

	/**
	 * Create a mutable FSet containing the passed arguments.
	 *
	 * @param elements
	 *            The elements to cache into a FSet.
	 * @return n mutable FSet containing all the specified elements.
	 */
	@SafeVarargs
	public static <E> FSet<E> buildMutable(E... elements)
	{
		final FSet<E> mutable = new FlowHashSet<>();
		for (final E element : elements) {
			mutable.add(element);
		}
		return mutable;
	}

	/**
	 * Create a mutable FSet containing elements in the parameter Collection.
	 *
	 * @param src
	 *            The container to copy references from.
	 * @return A mutable FSet containing the same references as in the parameter
	 *         Collection.
	 */
	public static <E> FSet<E> copyMutable(Collection<? extends E> src)
	{
		return new FlowHashSet<>(src);
	}
}
