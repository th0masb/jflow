package xawd.jflow.iterators.construction;

import static java.lang.Math.abs;
import static xawd.jflow.utilities.PrimitiveUtil.signum;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.IntFlow;

public final class IterRange
{
	private IterRange() {}

	public static IntFlow to(final int upperBound)
	{
		return upperBound > 0 ? IterFunction.intsFrom(i -> i, upperBound) : EmptyIteration.ofInts();
	}

	public static IntFlow between(final int low, final int high)
	{
		return high > low ? IterFunction.intsFrom(i -> i + low, high - low) : EmptyIteration.ofInts();
	}

	public static IntFlow between(final int low, final int high, final int step)
	{
		final int length = high - low;
		final int elementCount = (int) Math.ceil(abs((double)length / step));
		return signum(step) == signum(length) ? IterFunction.intsFrom(i -> low + i*step, elementCount) : EmptyIteration.ofInts();
	}

	public static DoubleFlow partition(final double start, final double end, final int nSubIntervals)
	{
		if (nSubIntervals < 1 || Math.abs(end - start) < 0.00001) {
			throw new IllegalArgumentException();
		}
		final double dir = Math.signum(end - start), step = 1.0/nSubIntervals;
		return to(nSubIntervals + 1).mapToDouble(i -> start + i*dir*step);
	}
}
