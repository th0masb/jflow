/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongBinaryOperator;

import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class LongReductionConsumption
{
	private LongReductionConsumption() {}

	public static OptionalLong reduce(final PrimitiveIterator.OfLong source, final LongBinaryOperator reducer)
	{
		boolean reductionUninitialised = true;
		long reduction = -1L;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextLong();
				reductionUninitialised = false;
			}
			else {
				reduction = reducer.applyAsLong(reduction, source.nextLong());
			}
		}
		return reductionUninitialised ? OptionalLong.empty() : OptionalLong.of(reduction);
	}

	public static long reduce(final PrimitiveIterator.OfLong source, final long id, final LongBinaryOperator reducer)
	{
		long reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsLong(reduction, source.nextLong());
		}
		return reduction;
	}

	public static long count(final PrimitiveIterator.OfLong source)
	{
		final boolean sourceSkippable = source instanceof Skippable;
		long count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			}
			else {
				source.nextLong();
			}
			count++;
		}
		return count;
	}
}
