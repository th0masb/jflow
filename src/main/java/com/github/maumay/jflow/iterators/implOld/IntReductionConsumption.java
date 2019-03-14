/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;

import com.github.maumay.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class IntReductionConsumption
{
	private IntReductionConsumption()
	{
	}

	public static OptionalInt foldOption(PrimitiveIterator.OfInt source,
			IntBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		int reduction = -1;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextInt();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsInt(reduction, source.nextInt());
			}
		}
		return reductionUninitialised ? OptionalInt.empty() : OptionalInt.of(reduction);
	}

	public static int fold(PrimitiveIterator.OfInt source, IntBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		int reduction = -1;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextInt();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsInt(reduction, source.nextInt());
			}
		}
		if (reductionUninitialised) {
			throw new NoSuchElementException("Attempted to unsafely fold empty iterator");
		} else {
			return reduction;
		}
	}

	public static int fold(PrimitiveIterator.OfInt source, int id,
			IntBinaryOperator reducer)
	{
		int reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsInt(reduction, source.nextInt());
		}
		return reduction;
	}

	public static long count(PrimitiveIterator.OfInt source)
	{
		boolean sourceSkippable = source instanceof Skippable;
		int count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			} else {
				source.nextInt();
			}
			count++;
		}
		return count;
	}
}
