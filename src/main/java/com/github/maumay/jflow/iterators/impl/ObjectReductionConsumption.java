/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.github.maumay.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public class ObjectReductionConsumption
{
	private ObjectReductionConsumption()
	{
	}

	public static <E> Optional<E> reduceOption(Iterator<? extends E> source,
			BinaryOperator<E> reducer)
	{
		E reduction = null;
		while (source.hasNext()) {
			if (reduction == null) {
				reduction = source.next();
			} else {
				reduction = reducer.apply(reduction, source.next());
			}
		}
		return reduction == null ? Optional.empty() : Optional.of(reduction);
	}

	public static <E> E reduce(Iterator<? extends E> source, BinaryOperator<E> reducer)
	{
		E reduction = null;
		while (source.hasNext()) {
			if (reduction == null) {
				reduction = source.next();
			} else {
				reduction = reducer.apply(reduction, source.next());
			}
		}
		if (reduction == null) {
			throw new NoSuchElementException();
		} else {
			return reduction;
		}
	}

	public static <E, R> R fold(Iterator<? extends E> source, R id,
			BiFunction<R, E, R> reducer)
	{
		R reduction = id;
		while (source.hasNext()) {
			reduction = reducer.apply(reduction, source.next());
		}
		return reduction;
	}

	public static <E> long count(Iterator<? extends E> source)
	{
		boolean sourceSkippable = source instanceof Skippable;
		long count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			} else {
				source.next();
			}
			count++;
		}
		return count;
	}
}
