/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongBinaryOperator;

import com.github.maumay.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class LongReductionConsumption
{
	private LongReductionConsumption()
	{
	}

	public static OptionalLong foldOption(PrimitiveIterator.OfLong source,
			LongBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		long reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextLong();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsLong(reduction, source.nextLong());
			}
		}
		return reductionUninitialised ? OptionalLong.empty() : OptionalLong.of(reduction);
	}

	public static long fold(PrimitiveIterator.OfLong source, LongBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		long reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextLong();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsLong(reduction, source.nextLong());
			}
		}

		if (reductionUninitialised) {
			throw new NoSuchElementException("Attempted to unsafely fold empty iterator");
		} else {
			return reduction;
		}
	}

	public static long fold(PrimitiveIterator.OfLong source, long id,
			LongBinaryOperator reducer)
	{
		long reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsLong(reduction, source.nextLong());
		}
		return reduction;
	}

	public static long count(PrimitiveIterator.OfLong source)
	{
		boolean sourceSkippable = source instanceof Skippable;
		long count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			} else {
				source.nextLong();
			}
			count++;
		}
		return count;
	}
}
