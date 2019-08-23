/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;

/**
 * @author ThomasB
 */
public final class DoubleReductionConsumption
{
	private DoubleReductionConsumption()
	{
	}

	public static OptionalDouble foldOption(AbstractDoubleIterator source,
			DoubleBinaryOperator reducer)
	{
		source.relinquishOwnership();
		boolean reductionUninitialised = true;
		double reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextDoubleImpl();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsDouble(reduction, source.nextDoubleImpl());
			}
		}
		return reductionUninitialised ? OptionalDouble.empty() : OptionalDouble.of(reduction);
	}

	public static double fold(AbstractDoubleIterator source, DoubleBinaryOperator reducer)
	{
		source.relinquishOwnership();
		boolean reductionUninitialised = true;
		double reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextDoubleImpl();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsDouble(reduction, source.nextDoubleImpl());
			}
		}
		if (reductionUninitialised) {
			throw new IllegalStateException("Attempted fold on empty iterator");
		} else {
			return reduction;
		}
	}

	public static double fold(AbstractDoubleIterator source, double id,
			DoubleBinaryOperator reducer)
	{
		source.relinquishOwnership();
		double reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsDouble(reduction, source.nextDoubleImpl());
		}
		return reduction;
	}

	public static long count(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		int count = 0;
		while (source.hasNext()) {
			source.forwardImpl();
			count++;
		}
		return count;
	}
}
