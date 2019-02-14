/**
 *
 */
package maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;

import maumay.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class DoubleReductionConsumption
{
	private DoubleReductionConsumption()
	{
	}

	public static OptionalDouble foldOption(PrimitiveIterator.OfDouble source,
			DoubleBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		double reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextDouble();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsDouble(reduction, source.nextDouble());
			}
		}
		return reductionUninitialised ? OptionalDouble.empty()
				: OptionalDouble.of(reduction);
	}

	public static double fold(PrimitiveIterator.OfDouble source,
			DoubleBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		double reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextDouble();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsDouble(reduction, source.nextDouble());
			}
		}
		if (reductionUninitialised) {
			throw new NoSuchElementException("Attempted fold on empty iterator");
		} else {
			return reduction;
		}
	}

	public static double fold(PrimitiveIterator.OfDouble source, double id,
			DoubleBinaryOperator reducer)
	{
		double reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsDouble(reduction, source.nextDouble());
		}
		return reduction;
	}

	public static long count(PrimitiveIterator.OfDouble source)
	{
		boolean sourceSkippable = source instanceof Skippable;
		int count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			} else {
				source.nextDouble();
			}
			count++;
		}
		return count;
	}
}
