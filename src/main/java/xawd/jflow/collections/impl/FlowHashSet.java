/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Collection;
import java.util.HashSet;

import xawd.jflow.collections.FSet;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * A mutable {@link FSet} implementation which inherits all Set related
 * functionality from the Java {@linkplain HashSet} class and adds enhanced
 * iterator capabilities without needing to resort to wrapping a separate
 * instance via {@linkplain DelegatingFlowSet}.
 *
 * @param <E>
 *            The type of the elements contained in this Set.
 *
 * @author ThomasB
 */
public final class FlowHashSet<E> extends HashSet<E> implements FSet<E>
{
	private static final long serialVersionUID = 2078317551236121767L;

	public FlowHashSet()
	{
	}

	public FlowHashSet(Collection<? extends E> c)
	{
		super(c);
	}

	public FlowHashSet(int initialCapacity)
	{
		super(initialCapacity);
	}

	public FlowHashSet(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	@Override
	public Flow<E> iterator()
	{
		return new FlowFromIterator.OfObject<>(super.iterator(), size());
	}
}
