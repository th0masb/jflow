/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.iterators.RichIterator;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.IntVec;
import com.github.maumay.jflow.vec.LongVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * An immutable implementation of {@link Vec} which stores only non null
 * references. This class is very space efficient as it simply wraps a single
 * Object array. When combined with {@link RichIterator} instances one can write
 * very clean, efficient and safe code code without ever needing to reference
 * this type directly.
 *
 * @param <E> The type of the elements contained in this List.
 *
 * @author ThomasB
 */
final class VecImpl<E> implements Vec<E>
{
	private final Object[] data;

	public VecImpl()
	{
		this(new Object[0]);
	}

	VecImpl(Object[] cache)
	{
		this.data = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index)
	{
		return (E) data[index];
	}

	@Override
	public int size()
	{
		return data.length;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Vec<?>) {
			final Vec<?> other = (Vec<?>) obj;
			if (other.size() == size()) {
				return Iter.until(size()).allMatch(i -> data[i].equals(other.get(i)));
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
		return iter().mapToInt(Object::hashCode).fold(1, (res, n) -> 31 * res + n);
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("Vec[");
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i].toString());
			if (i < data.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Spliterator<E> spliterator()
	{
		return new VecImplSpliterator<>(this, 0, size());
	}

	@Override
	public Stream<E> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractRichIterator<E> iterRev()
	{
		return new ArraySource.OfObjectReversed<>((E[]) data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractRichIterator<E> iter()
	{
		return new ArraySource.OfObject<E>((E[]) data);
	}

	@Override
	public <R> VecImpl<R> map(Function<? super E, ? extends R> mappingFunction)
	{
		Object[] mappedData = new Object[data.length];
		for (int i = 0; i < data.length; i++) {
			mappedData[i] = mappingFunction.apply(get(i));
		}
		return new VecImpl<>(mappedData);
	}

	@Override
	public <R> VecImpl<R> flatMap(
			Function<? super E, ? extends Iterator<? extends R>> mapping)
	{
		return iter().flatMap(mapping).toVec();
	}

	@Override
	public VecImpl<E> filter(Predicate<? super E> predicate)
	{
		return iter().filter(predicate).toVec();
	}

	@Override
	public <R> VecImpl<R> cast()
	{
		return iter().<R>cast().toVec();
	}

	@Override
	public Vec<E> append(Iterable<? extends E> other)
	{
		return iter().chain(other.iterator()).toVec();
	}

	@Override
	public Vec<E> append(Collection<? extends E> other)
	{
		return iter().chain(new CollectionSource<>(other)).toVec();
	}

	@Override
	public Vec<E> append(E other)
	{
		Object[] newData = new Object[size() + 1];
		System.arraycopy(data, 0, newData, 0, data.length);
		newData[size()] = other;
		return new VecImpl<>(newData);
	}

	@Override
	public Vec<E> insert(Iterable<? extends E> other)
	{
		return iter().insert(other.iterator()).toVec();
	}

	@Override
	public Vec<E> insert(Collection<? extends E> other)
	{
		return iter().insert(new CollectionSource<>(other)).toVec();
	}

	@Override
	public Vec<E> insert(E other)
	{
		Object[] newData = new Object[size() + 1];
		System.arraycopy(data, 0, newData, 1, data.length);
		newData[0] = other;
		return new VecImpl<>(newData);
	}

	@Override
	public Vec<E> take(int n)
	{
		if (n >= size())
			return this;
		else if (n == 0)
			return new VecImpl<>();
		else
			return iter().take(n).toVec();
	}

	@Override
	public Vec<E> takeWhile(Predicate<? super E> predicate)
	{
		return iter().takeWhile(predicate).toVec();
	}

	@Override
	public Vec<E> drop(int n)
	{
		if (n == 0)
			return this;
		else if (n >= size())
			return new VecImpl<>();
		else
			return iter().drop(n).toVec();
	}

	@Override
	public VecImpl<E> dropWhile(Predicate<? super E> predicate)
	{
		return iter().dropWhile(predicate).toVec();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tup<Vec<E>, Vec<E>> span(Predicate<? super E> predicate)
	{
		int split = 0;
		for (Object o : data) {
			if (predicate.test((E) o)) {
				split++;
			} else {
				break;
			}
		}
		Object[] first = new Object[split], second = new Object[data.length - split];
		System.arraycopy(data, 0, first, 0, first.length);
		System.arraycopy(data, first.length, second, 0, second.length);
		return Tup.of(new VecImpl<>(first), new VecImpl<>(second));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tup<Vec<E>, Vec<E>> partition(Predicate<? super E> predicate)
	{
		int n = size();
		Object[] tmp = new Object[size()];
		int trueIndex = 0, falseIndex = 0;
		for (Object o : data) {
			if (predicate.test((E) o)) {
				tmp[trueIndex++] = o;
			} else {
				tmp[n - 1 - falseIndex++] = o;
			}
		}
		Object[] passed = new Object[trueIndex], failed = new Object[falseIndex];
		for (int i = 0; i < trueIndex; i++)
			passed[i] = tmp[i];
		for (int i = 0; i < falseIndex; i++)
			failed[i] = tmp[tmp.length - 1 - i];
		return Tup.of(new VecImpl<>(passed), new VecImpl<>(failed));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<E> toSet()
	{
		Set<E> set = new HashSet<>(size());
		for (Object o : data) {
			set.add((E) o);
		}
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> toList()
	{
		List<E> list = new ArrayList<>(size());
		for (Object o : data) {
			list.add((E) o);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vec<E> sorted(Comparator<? super E> orderingFunction)
	{
		Object[] dcpy = Arrays.copyOf(data, size());
		Arrays.sort(dcpy, (o1, o2) -> orderingFunction.compare((E) o1, (E) o2));
		return new VecImpl<>(dcpy);
	}

	@Override
	public DoubleVecImpl mapToDouble(ToDoubleFunction<? super E> mappingFunc)
	{
		return iter().mapToDouble(mappingFunc).toVec();
	}

	@Override
	public IntVec mapToInt(ToIntFunction<? super E> mappingFunc)
	{
		return iter().mapToInt(mappingFunc).toVec();
	}

	@Override
	public LongVec mapToLong(ToLongFunction<? super E> mappingFunc)
	{
		return iter().mapToLong(mappingFunc).toVec();
	}
}
