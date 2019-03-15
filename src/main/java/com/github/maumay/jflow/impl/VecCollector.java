/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public class VecCollector<E> implements Collector<E, AccumulatorImpl<E>, Vec<E>>
{
	private static final Set<Characteristics> CHARACTERISTICS = EnumSet
			.noneOf(Characteristics.class);

	@Override
	public BiConsumer<AccumulatorImpl<E>, E> accumulator()
	{
		return AccumulatorImpl::accumulate;
	}

	@Override
	public Set<Characteristics> characteristics()
	{
		return CHARACTERISTICS;
	}

	@Override
	public BinaryOperator<AccumulatorImpl<E>> combiner()
	{
		return AccumulatorImpl::combine;
	}

	@Override
	public Function<AccumulatorImpl<E>, Vec<E>> finisher()
	{
		return AccumulatorImpl::finish;
	}

	@Override
	public Supplier<AccumulatorImpl<E>> supplier()
	{
		return AccumulatorImpl::new;
	}
}

class AccumulatorImpl<E>
{
	private static final int START_SIZE = 10, INCREASE_FACTOR = 2;

	private Object[] data;
	private int nextEmptyIndex;

	public AccumulatorImpl()
	{
		data = new Object[START_SIZE];
		nextEmptyIndex = 0;
	}

	AccumulatorImpl(Object[] data, int count)
	{
		this.data = data;
		this.nextEmptyIndex = count;
	}

	int freeSpace()
	{
		return data.length - nextEmptyIndex;
	}

	static <E> void accumulate(AccumulatorImpl<E> container, E element)
	{
		if (container.freeSpace() == 0) {
			container.grow();
		}
		container.data[container.nextEmptyIndex++] = element;
	}

	private void grow()
	{
		Object[] newData = new Object[Math.multiplyExact(data.length, INCREASE_FACTOR)];
		System.arraycopy(data, 0, newData, 0, nextEmptyIndex);
		data = newData;
	}

	static <E> AccumulatorImpl<E> combine(AccumulatorImpl<E> a, AccumulatorImpl<E> b)
	{
		int combinedDataSize = Math.addExact(a.data.length, b.data.length);
		int freeSpace = Math.min(a.freeSpace() + b.freeSpace(),
				Integer.MAX_VALUE - combinedDataSize);
		Object[] data = new Object[combinedDataSize + freeSpace];
		System.arraycopy(a.data, 0, data, 0, a.nextEmptyIndex);
		System.arraycopy(b.data, 0, data, a.nextEmptyIndex, b.nextEmptyIndex);
		return new AccumulatorImpl<>(data, a.nextEmptyIndex + b.nextEmptyIndex);
	}

	static <E> Vec<E> finish(AccumulatorImpl<E> source)
	{
		Object[] data = new Object[source.nextEmptyIndex];
		System.arraycopy(source.data, 0, data, 0, source.nextEmptyIndex);
		return new VecImpl<>(data);
	}
}
