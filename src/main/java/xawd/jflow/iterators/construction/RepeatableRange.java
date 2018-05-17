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
			public IntFlow iter() {
				return IterRange.to(upperBound);
			}
		};
	}

	public static IntFlowIterable range(final int lowerBound, final int upperBound)
	{
		return new IntFlowIterable() {

			@Override
			public IntFlow iter() {
				return IterRange.between(lowerBound, upperBound);
			}
		};
	}

	public static IntFlowIterable range(final int lowerBound, final int upperBound, final int step)
	{
		return new IntFlowIterable() {

			@Override
			public IntFlow iter() {
				return IterRange.between(lowerBound, upperBound, step);
			}
		};
	}

//	public static void main(final String[] args) {
//
//		final int[][] arr = new int[3][3];
//
//		IterProduct.of(range(3), range(3)).forEach(pair ->
//		{
//			final int i = pair.getFirst(), j = pair.getSecond();
//			arr[i][j] = i*j;
//		});
//	}
}
