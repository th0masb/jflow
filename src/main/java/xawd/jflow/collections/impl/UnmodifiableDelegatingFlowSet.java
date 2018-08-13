/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Collection;
import java.util.Set;

import xawd.jflow.collections.FSet;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * An implementation of {@link FSet} following the 'Delegator' design
 * pattern but with mutation methods disabled. This class simply wraps a Set
 * instance and all non-mutation methods described in the Set interface are
 * delegated to the implementation of the wrapped Set. The main purpose of this
 * class is to offer the enhanced iterator capabilities to existing Set
 * implementations through an immutable view.
 *
 * @param <E>
 *            The type of the elements contained in this Set.
 *
 * @author ThomasB
 */
public final class UnmodifiableDelegatingFlowSet<E> implements FSet<E>
{
	private final Set<E> delegate;

	public UnmodifiableDelegatingFlowSet(Set<E> delegate)
	{
		this.delegate = delegate;
	}

	@Deprecated
	@Override
	public boolean add(E e)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
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

	@Deprecated
	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
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

	@Deprecated
	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString()
	{
		return delegate.toString();
	}
}
