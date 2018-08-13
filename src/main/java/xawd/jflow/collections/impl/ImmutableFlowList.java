/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Spliterator;

import xawd.jflow.collections.FList;
import xawd.jflow.collections.Lists;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.IterRange;
import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.utilities.Optionals;

/**
 * An immutable implementation of {@link FList} which stores only non null
 * references. This class is very space efficient as it simply wraps a single
 * Object array. When combined with {@link Flow} instances one can write very
 * clean, efficient and safe code code without ever needing to reference this
 * type directly. In fact I would say it is bad practice to ever directly
 * reference this type, static factories and Flow collection should be used to
 * instantiate them. See {@link Lists#build(Object...)},
 * {@link Lists#copy(Collection)} {@link Flow#toList()}.
 *
 * @param <E>
 *            The type of the elements contained in this List.
 *
 * @author ThomasB
 */
public final class ImmutableFlowList<E> implements FList<E>
{
	private static final String EXTRACTION_ERR = "A concrete size must be supplied to construct an instance.";
	
	private final Object[] cache;

	public ImmutableFlowList(Flow<? extends E> src)
	{
		cache = new Object[Optionals.extract(src.size(), EXTRACTION_ERR)];
		int count = 0;
		while (src.hasNext()) {
			cache[count++] = Objects.requireNonNull(src.next());
		}
	}

	@SafeVarargs
	public ImmutableFlowList(E... elements)
	{
		this(Iterate.over(elements));
	}

	public ImmutableFlowList(Collection<? extends E> src)
	{
		this(Iterate.over(src));
	}

	@Deprecated
	@Override
	public boolean add(E e)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void add(int index, E element)
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
	public boolean addAll(int index, Collection<? extends E> c)
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
		for (final Object e : cache) {
			if (e.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return Iterate.over(c).allMatch(this::contains);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index)
	{
		return (E) cache[index];
	}

	@Override
	public int indexOf(Object o)
	{
		for (int i = 0; i < cache.length; i++) {
			if (cache[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty()
	{
		return cache.length == 0;
	}

	@Override
	public int lastIndexOf(Object o)
	{
		for (int i = cache.length - 1; i > -1; i--) {
			if (cache[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ImmutableListIterator<>(cache.length, this::get, 0);
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new ImmutableListIterator<>(cache.length, this::get, index);
	}

	@Deprecated
	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public E remove(int index)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public E set(int index, E element)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int size()
	{
		return cache.length;
	}

	@Override
	public Object[] toArray()
	{
		return Arrays.copyOf(cache, size());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a)
	{
		if (a.length < size()) {
			final T[] newAlloc = Arrays.copyOf(a, size());
			IterRange.to(size()).forEach(i -> newAlloc[i] = (T) cache[i]);
			return newAlloc;
		} else {
			IterRange.to(size()).forEach(i -> a[i] = (T) cache[i]);
			if (a.length > size()) {
				a[size()] = null;
			}
			return a;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public FList<E> subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		return new FunctionalFlowList<>(i -> (E) cache[i + fromIndex], toIndex - fromIndex);
	}

	@Override
	public Flow<E> iterator()
	{
		return new AbstractFlow<E>(OptionalInt.of(size())) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < cache.length;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next()
			{
				if (hasNext()) {
					return (E) cache[count++];
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void skip()
			{
				if (count++ >= cache.length) {
					throw new NoSuchElementException();
				}
			}
		};
	}

	@Override
	public Spliterator<E> spliterator()
	{
		return new ImmutableListSpliterator<>(this, 0, size());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof List<?>) {
			final List<?> other = (List<?>) obj;
			if (other.size() == size()) {
				return IterRange.to(size()).allMatch(i -> cache[i].equals(other.get(i)));
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return flow().mapToInt(Object::hashCode).fold(1, (res, n) -> 31 * res + n);
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < cache.length; i++) {
			sb.append(cache[i].toString());
			if (i < cache.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
