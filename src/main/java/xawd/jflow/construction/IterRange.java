package xawd.jflow.construction;

import static io.xyz.chains.utilities.PrimitiveUtil.abs;
import static io.xyz.chains.utilities.PrimitiveUtil.signum;

import xawd.jflow.DoubleFlow;
import xawd.jflow.IntFlow;

public final class IterRange
{
	private IterRange() {}

	public static IntFlow to(final int upperBound)
	{
		return upperBound > 0 ? Iter.intsFrom(i -> i, upperBound) : Iter.emptyInts();
	}

	public static IntFlow between(final int low, final int high)
	{
		return high > low ? Iter.intsFrom(i -> i + low, high - low) : Iter.emptyInts();
	}

	public static IntFlow between(final int low, final int high, final int step)
	{
		final int length = high - low;
		final int elementCount = (int) Math.ceil(abs((double)length / step));
		return signum(step) == signum(length) ? Iter.intsFrom(i -> low + i*step, elementCount) : Iter.emptyInts();
	}

	public static DoubleFlow partition(final double low, final double high, final int nSubIntervals)
	{
		return null;
	}

	public static void main(final String[] args) {
//		List<String> someStrings = Arrays.asList(a)
//
//		IterRange.to(10).toMap(i -> "a" + i, i -> "b" + i);


		IterRange.between(0, 11).forEach(System.out::println);
		Iter.of("a", "b").toMap(string -> string.charAt(0), string -> string.length());
//		stepBetween(0, 11, 2).forEach(System.out::println);
	}
}
