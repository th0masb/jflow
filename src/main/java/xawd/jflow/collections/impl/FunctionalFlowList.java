/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.IntFunction;

import xawd.jflow.collections.FList;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.IterRange;
import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.impl.FlowFromFunction;

/**
 * A functional implementation of a {@link FList}. It works by wrapping a
 * function whose domain is the natural numbers and a positive number which
 * determines the size of the list and the index range of its elements. Treating
 * this class alone it is immutable, however constructing an instance via some
 * impure function can result in mutable and possibly undefined behaviour.
 * Therefore it is recommended only to pass pure (or essentially pure) functions
 * to the relevant constructor.
 *
 * @param <E>
 *            The type of the elements contained in this List.
 *
 * @author ThomasB
 */
public class FunctionalFlowList<E> implements FList<E>
{
	private final IntFunction<E> indexingFunction;
	private final int size;

	public FunctionalFlowList(IntFunction<E> indexingFunction, int size)
	{
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.indexingFunction = Objects.requireNonNull(indexingFunction);
		this.size = size;
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	public FunctionalFlowList(E... elements)
	{
		this.size = elements.length;
		final Object[] cpy = new Object[size];
		System.arraycopy(elements, 0, cpy, 0, size);
		this.indexingFunction = i -> (E) cpy[i];
	}

	@SuppressWarnings("unchecked")
	public FunctionalFlowList(Collection<? extends E> src)
	{
		this.size = src.size();
		final Object[] cpy = src.toArray();
		this.indexingFunction = i -> (E) cpy[i];
	}

	@Deprecated
	@Override
	public boolean add(E arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void add(int arg0, E arg1)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean addAll(Collection<? extends E> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1)
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
	public boolean contains(Object arg0)
	{
		return IterRange.to(size).mapToObject(indexingFunction).anyMatch(e -> Objects.equals(e, arg0));
	}

	@Override
	public boolean containsAll(Collection<?> arg0)
	{
		return Iterate.over(arg0).allMatch(this::contains);
	}

	@Override
	public E get(int arg0)
	{
		if (arg0 < 0 || arg0 >= size) {
			throw new IndexOutOfBoundsException();
		}
		return indexingFunction.apply(arg0);
	}

	@Override
	public int indexOf(Object arg0)
	{
		for (int i = 0; i < size; i++) {
			if (indexingFunction.apply(i).equals(arg0)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public int lastIndexOf(Object arg0)
	{
		for (int i = size - 1; i > -1; i--) {
			if (indexingFunction.apply(i).equals(arg0)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ImmutableListIterator<>(size, indexingFunction, 0);
	}

	@Override
	public ListIterator<E> listIterator(int arg0)
	{
		return new ImmutableListIterator<>(size, indexingFunction, arg0);
	}

	@Deprecated
	@Override
	public boolean remove(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public E remove(int arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean removeAll(Collection<?> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public boolean retainAll(Collection<?> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public E set(int arg0, E arg1)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public FList<E> subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		return new FunctionalFlowList<>(i -> indexingFunction.apply(i + fromIndex), toIndex - fromIndex);
	}

	@Override
	public Object[] toArray()
	{
		final Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = indexingFunction.apply(i);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a)
	{
		if (a.length < size) {
			final T[] newAlloc = Arrays.copyOf(a, size);
			IterRange.to(size).forEach(i -> newAlloc[i] = (T) indexingFunction.apply(i));
			return newAlloc;
		} else {
			IterRange.to(size).forEach(i -> a[i] = (T) indexingFunction.apply(i));
			if (a.length > size) {
				a[size] = null;
			}
			return a;
		}
	}

	@Override
	public Flow<E> iterator()
	{
		return new FlowFromFunction.OfObject<>(indexingFunction, size);
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			sb.append(Objects.toString(indexingFunction.apply(i)));
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof List<?>) {
			final List<?> other = (List<?>) obj;
			if (other.size() == size()) {
				return IterRange.to(size())
						.allMatch(i -> Objects.equals(indexingFunction.apply(i), other.get(i)));
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
		return flow().mapToInt(Objects::hashCode).fold(1, (res, n) -> 31 * res + n);
	}
}
