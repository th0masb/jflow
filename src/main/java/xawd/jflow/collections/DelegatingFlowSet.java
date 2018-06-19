/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;
import java.util.Set;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * @author ThomasB
 *
 */
public final class DelegatingFlowSet<E> implements FlowSet<E>
{
	private final Set<E> delegate;

	public DelegatingFlowSet(Set<E> src)
	{
		this.delegate = src;
	}

	@Override
	public boolean add(E arg0)
	{
		return delegate.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0)
	{
		return delegate.addAll(arg0);
	}

	@Override
	public void clear()
	{
		delegate.clear();
	}

	@Override
	public boolean contains(Object arg0)
	{
		return delegate.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0)
	{
		return delegate.containsAll(arg0);
	}

	@Override
	public boolean equals(Object arg0)
	{
		return delegate.equals(arg0);
	}

	@Override
	public int hashCode()
	{
		return delegate.hashCode();
	}

	@Override
	public boolean isEmpty()
	{
		return delegate.isEmpty();
	}

	@Override
	public Flow<E> iterator()
	{
		return new FlowFromIterator.OfObject<>(delegate.iterator());
	}

	@Override
	public boolean remove(Object arg0)
	{
		return delegate.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0)
	{
		return delegate.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0)
	{
		return delegate.retainAll(arg0);
	}

	@Override
	public int size()
	{
		return delegate.size();
	}

	@Override
	public Object[] toArray()
	{
		return delegate.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return delegate.toArray(arg0);
	}
}
