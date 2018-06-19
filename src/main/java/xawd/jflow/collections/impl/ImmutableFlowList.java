/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Collection;
import java.util.ListIterator;
import java.util.function.IntFunction;

import xawd.jflow.collections.FlowList;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.IterRange;
import xawd.jflow.iterators.impl.FlowFromFunction;

/**
 * @author ThomasB
 *
 */
public class ImmutableFlowList<E> implements FlowList<E>
{
	private final IntFunction<E> indexingFunction;
	private final int size;

	public ImmutableFlowList(IntFunction<E> indexingFunction, int size)
	{
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.indexingFunction = indexingFunction;
		this.size = size;
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	public ImmutableFlowList(E... elements)
	{
		this.size = elements.length;
		final Object[] cpy = new Object[size];
		System.arraycopy(elements, 0, cpy, 0, size);
		this.indexingFunction = i -> (E) cpy[i];
	}

	@SuppressWarnings("unchecked")
	public ImmutableFlowList(Collection<? extends E> src)
	{
		this.size = src.size();
		final Object[] cpy = src.toArray();
		this.indexingFunction = i -> (E) cpy[i];
	}

	@Override
	public boolean add(E arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public void add(int arg0, E arg1)
	{
		throw new RuntimeException();
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1)
	{
		throw new RuntimeException();
	}

	@Override
	public void clear()
	{
		throw new RuntimeException();
	}

	@Override
	public boolean contains(Object arg0)
	{
		return IterRange.to(size)
				.mapToObject(indexingFunction)
				.anyMatch(e -> e.equals(arg0));
	}

	@Override
	public boolean containsAll(Collection<?> arg0)
	{
		throw new RuntimeException("not yet implemented");
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
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public ListIterator<E> listIterator(int arg0)
	{
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean remove(Object arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public E remove(int arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0)
	{
		throw new RuntimeException();
	}

	@Override
	public E set(int arg0, E arg1)
	{
		throw new RuntimeException();
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public FlowList<E> subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		return new ImmutableFlowList<>(i -> indexingFunction.apply(i + fromIndex), toIndex - fromIndex);
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

	@Override
	public <T> T[] toArray(T[] a)
	{
		throw new RuntimeException("not yet implemented");
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
		for (final E element : this) {
			sb.append(element.toString()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args)
	{
		final FlowList<String> xs = new ImmutableFlowList<>("1", "20");
		for (final String x : xs) {
			System.out.println(x);
		}
	}
}
