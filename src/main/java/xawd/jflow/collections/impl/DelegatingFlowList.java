/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import xawd.jflow.collections.FList;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * A {@link FList} implementation following the 'Delegator' design pattern.
 * This class simply wraps a List instance and all methods described in the List
 * interface are delegated to the implementation of the wrapped List. The main
 * purpose of this class is to offer the enhanced iterator capabilities to
 * existing List implementations.
 *
 * @param <E>
 *            The type of the elements contained in this List.
 *
 * @author ThomasB
 */
public final class DelegatingFlowList<E> implements FList<E>
{
	private final List<E> delegate;

	public DelegatingFlowList(List<E> src)
	{
		delegate = src;
	}

	@Override
	public boolean add(E e)
	{
		return delegate.add(e);
	}

	@Override
	public void add(int index, E element)
	{
		delegate.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		return delegate.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		return delegate.addAll(index, c);
	}

	@Override
	public void clear()
	{
		delegate.clear();
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
	public E get(int index)
	{
		return delegate.get(index);
	}

	@Override
	public int hashCode()
	{
		return delegate.hashCode();
	}

	@Override
	public int indexOf(Object o)
	{
		return delegate.indexOf(o);
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
	public int lastIndexOf(Object o)
	{
		return delegate.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return delegate.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return delegate.listIterator(index);
	}

	@Override
	public E remove(int index)
	{
		return delegate.remove(index);
	}

	@Override
	public boolean remove(Object o)
	{
		return delegate.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return delegate.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return delegate.retainAll(c);
	}

	@Override
	public E set(int index, E element)
	{
		return delegate.set(index, element);
	}

	@Override
	public int size()
	{
		return delegate.size();
	}

	@Override
	public FList<E> subList(int fromIndex, int toIndex)
	{
		return new DelegatingFlowList<>(delegate.subList(fromIndex, toIndex));
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
	public String toString()
	{
		return delegate.toString();
	}
}
