package xawd.jflow.collections.impl;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * An implementation of a ListIterator designed for immutable Lists only. It
 * supports traversal only.
 *
 * @param <E>
 *            the type of elements traversed by this iterator.
 *
 * @author ThomasB
 */
class ImmutableListIterator<E> implements ListIterator<E>
{
	private final int size;
	private final IntFunction<E> indexingFunction;

	private int position;

	public ImmutableListIterator(int size, IntFunction<E> indexingFunction, int position)
	{
		if (position < 0 || position > size) {
			throw new IllegalArgumentException();
		}
		this.size = size;
		this.indexingFunction = indexingFunction;
		this.position = position;
	}

	@Override
	public void add(E e)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext()
	{
		return position < size;
	}

	@Override
	public boolean hasPrevious()
	{
		return position > 0;
	}

	@Override
	public E next()
	{
		if (position == size) {
			throw new NoSuchElementException();
		} else {
			return indexingFunction.apply(position++);
		}
	}

	@Override
	public int nextIndex()
	{
		return position;
	}

	@Override
	public E previous()
	{
		if (position == 0) {
			throw new NoSuchElementException();
		} else {
			return indexingFunction.apply(--position);
		}
	}

	@Override
	public int previousIndex()
	{
		return position - 1;
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(E e)
	{
		throw new UnsupportedOperationException();
	}
}