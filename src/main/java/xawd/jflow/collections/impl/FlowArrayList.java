/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.ArrayList;
import java.util.Collection;

import xawd.jflow.collections.FList;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * A mutable {@link FList} implementation which inherits all List related
 * functionality from the Java {@linkplain ArrayList} class and adds enhanced
 * iterator capabilities without needing to resort to wrapping a separate
 * instance via {@linkplain DelegatingFlowList}.
 *
 * @param <E>
 *            The type of the elements contained in this List.
 *
 * @author ThomasB
 */
public final class FlowArrayList<E> extends ArrayList<E> implements FList<E>
{
	private static final long serialVersionUID = 6183971349190497341L;

	public FlowArrayList()
	{
	}

	public FlowArrayList(int initialCapacity)
	{
		super(initialCapacity);
	}

	public FlowArrayList(Collection<? extends E> c)
	{
		super(c);
	}

	@Override
	public FList<E> subList(int fromIndex, int toIndex)
	{
		return new DelegatingFlowList<>(super.subList(fromIndex, toIndex));
	}

	@Override
	public Flow<E> iterator()
	{
		return new FlowFromIterator.OfObject<>(super.iterator(), size());
	}
}
