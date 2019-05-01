/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * @author ThomasB
 */
public class ObjectReductionConsumption
{
	private ObjectReductionConsumption()
	{
	}

	public static <E> Optional<E> foldOp(AbstractRichIterator<? extends E> source,
			BinaryOperator<E> reducer)
	{
		source.relinquishOwnership();
		E reduction = null;
		while (source.hasNext()) {
			if (reduction == null) {
				reduction = source.nextImpl();
			} else {
				reduction = reducer.apply(reduction, source.nextImpl());
			}
		}
		return reduction == null ? Optional.empty() : Optional.of(reduction);
	}

	public static <E> E fold(AbstractRichIterator<? extends E> source, BinaryOperator<E> reducer)
	{
		source.relinquishOwnership();
		E reduction = null;
		while (source.hasNext()) {
			if (reduction == null) {
				reduction = source.nextImpl();
			} else {
				reduction = reducer.apply(reduction, source.nextImpl());
			}
		}
		if (reduction == null) {
			throw new NoSuchElementException();
		} else {
			return reduction;
		}
	}

	public static <E, R> R fold(AbstractRichIterator<? extends E> source, R id,
			BiFunction<R, E, R> reducer)
	{
		source.relinquishOwnership();
		R reduction = id;
		while (source.hasNext()) {
			reduction = reducer.apply(reduction, source.nextImpl());
		}
		return reduction;
	}

	public static <E> long count(AbstractRichIterator<? extends E> source)
	{
		source.relinquishOwnership();
		if (source.getSize() instanceof KnownSize) {
			return ((KnownSize) source.getSize()).getValue();
		}
		long count = 0;
		while (source.hasNext()) {
			source.skipImpl();
			count++;
		}
		return count;
	}
}
