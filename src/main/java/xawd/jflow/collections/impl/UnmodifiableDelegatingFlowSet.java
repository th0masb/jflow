/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Collection;
import java.util.Set;

import xawd.jflow.collections.FlowSet;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * @author ThomasB
 */
public final class UnmodifiableDelegatingFlowSet<E> implements FlowSet<E>
{
	private final Set<E> delegate;

	public UnmodifiableDelegatingFlowSet(Set<E> delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public boolean add(E e)
	{
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public void clear()
	{
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public boolean contains(Object o)
	{
		return delegate.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return delegate.containsAll(c);
	}

	@Override
	public boolean equals(Object o)
	{
		return delegate.equals(o);
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
		return new FlowFromIterator.OfObject<>(delegate.iterator(), size());
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new RuntimeException("Operation not supported");
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
	public <T> T[] toArray(T[] a)
	{
		return delegate.toArray(a);
	}

	@Override
	public boolean remove(Object o)
	{
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new RuntimeException("Operation not supported");
	}
}
