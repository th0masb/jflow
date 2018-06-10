/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;

import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class DoubleReductionConsumption
{
	private DoubleReductionConsumption() {}

	public static OptionalDouble reduce(final PrimitiveIterator.OfDouble source, final DoubleBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		double reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextDouble();
				reductionUninitialised = false;
			}
			else {
				reduction = reducer.applyAsDouble(reduction, source.nextDouble());
			}
		}
		return reductionUninitialised ? OptionalDouble.empty() : OptionalDouble.of(reduction);
	}

	public static double reduce(final PrimitiveIterator.OfDouble source, final double id, final DoubleBinaryOperator reducer)
	{
		double reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsDouble(reduction, source.nextDouble());
		}
		return reduction;
	}

	public static long count(final PrimitiveIterator.OfDouble source)
	{
		final boolean sourceSkippable = source instanceof Skippable;
		int count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			}
			else {
				source.nextDouble();
			}
			count++;
		}
		return count;
	}
}
