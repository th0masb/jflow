/**
 *
 */
package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.iterables.IntFlowIterable;

/**
 * Currently experimental
 *
 * @author ThomasB
 *
 */
public final class RepeatableRange
{
	private RepeatableRange() {}

	public static IntFlowIterable range(final int upperBound)
	{
		return new IntFlowIterable() {

			@Override
			public IntFlow iterator() {
				return IterRange.to(upperBound);
			}
		};
	}

	public static IntFlowIterable range(final int lowerBound, final int upperBound)
	{
		return new IntFlowIterable() {

			@Override
			public IntFlow iterator() {
				return IterRange.between(lowerBound, upperBound);
			}
		};
	}

	public static IntFlowIterable range(final int lowerBound, final int upperBound, final int step)
	{
		return new IntFlowIterable() {

			@Override
			public IntFlow iterator() {
				return IterRange.between(lowerBound, upperBound, step);
			}
		};
	}
}
