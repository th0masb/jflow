/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntFunction;

import xawd.jflow.collections.FlowList;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * @author ThomasB
 *
 */
public final class FlowArrayList<E> extends ArrayList<E> implements FlowList<E>
{
	private static final long serialVersionUID = 6183971349190497341L;

	public FlowArrayList()
	{
	}

	public FlowArrayList(int arg0)
	{
		super(arg0);
	}

	public FlowArrayList(Collection<? extends E> c)
	{
		super(c);
	}

	@Override
	public FlowList<E> subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		final IntFunction<E> indexingFunction = this::get;
		return new ImmutableFlowList<>(i -> indexingFunction.apply(i + fromIndex), toIndex - fromIndex);
	}

	@Override
	public Flow<E> iterator()
	{
		return new FlowFromIterator.OfObject<>(super.iterator(), size());
	}
}
