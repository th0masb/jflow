package xawd.lists.listflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;

public final class ArrayListFlow<E> implements ListFlow<E>
{
	private final ArrayList<E> wrappedList;

	public ArrayListFlow()
	{
		this.wrappedList = new ArrayList<>();
	}

	public ArrayListFlow(int initialSize)
	{
		this.wrappedList = new ArrayList<>(initialSize);
	}

	public ArrayListFlow(Collection<? extends E> src)
	{
		this.wrappedList = new ArrayList<>(src);
	}

	@Override
	public int size() {
		return wrappedList.size();
	}

	@Override
	public boolean isEmpty() {
		return wrappedList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return wrappedList.contains(o);
	}

	@Override
	public Object[] toArray() {
		return wrappedList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return wrappedList.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return wrappedList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return wrappedList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return wrappedList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return wrappedList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return wrappedList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return wrappedList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return wrappedList.retainAll(c);
	}

	@Override
	public void clear() {
		wrappedList.clear();
	}

	@Override
	public E get(int index) {
		return wrappedList.get(index);
	}

	@Override
	public E set(int index, E element) {
		return wrappedList.set(index, element);
	}

	@Override
	public void add(int index, E element) {
		wrappedList.add(index, element);
	}

	@Override
	public E remove(int index) {
		return wrappedList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return wrappedList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return wrappedList.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return wrappedList.listIterator(index);
	}

	@Deprecated
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Flow<E> iter() {
		return Iter.of(wrappedList);
	}

	@Override
	public Flow<E> rIter() {
		return new AbstractFlow<E>() {
			int index = wrappedList.size() - 1;
			@Override
			public boolean hasNext() {
				return index >= 0;
			}
			@Override
			public E next() {
				try {
					return wrappedList.get(index--);
				}
				catch (final IndexOutOfBoundsException ex) {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	@Override
	public String toString()
	{
		return wrappedList.toString();
	}
}
