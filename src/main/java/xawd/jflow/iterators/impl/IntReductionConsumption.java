/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;

import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class IntReductionConsumption
{
	private IntReductionConsumption() {}

	public static OptionalInt reduce(final PrimitiveIterator.OfInt source, final IntBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		int reduction = -1;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextInt();
				reductionUninitialised = false;
			}
			else {
				reduction = reducer.applyAsInt(reduction, source.nextInt());
			}
		}
		return reductionUninitialised ? OptionalInt.empty() : OptionalInt.of(reduction);
	}

	public static int reduce(final PrimitiveIterator.OfInt source, final int id, final IntBinaryOperator reducer)
	{
		int reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsInt(reduction, source.nextInt());
		}
		return reduction;
	}

	public static long count(final PrimitiveIterator.OfInt source)
	{
		final boolean sourceSkippable = source instanceof Skippable;
		int count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			}
			else {
				source.nextInt();
			}
			count++;
		}
		return count;
	}
}
