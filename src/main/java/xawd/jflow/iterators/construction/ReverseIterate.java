/**
 *
 */
package xawd.jflow.iterators.construction;

import static java.util.Arrays.asList;

import java.util.List;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;

/**
 * @author ThomasB
 */
public final class ReverseIterate
{
	public ReverseIterate() {}

	public static <E> Flow<E> over(final List<? extends E> source)
	{
		return new ReverseFlowFromValues.OfObject<>(source);
	}

	public static <E> Flow<E> over(final E e1)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1));
	}

	public static <E> Flow<E> over(final E e1, final E e2)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3, final E e4)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3, e4));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3, final E e4, final E e5)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3, e4, e5));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3, e4, e5, e6));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3, e4, e5, e6, e7));
	}

	public static <E> Flow<E> over(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7, final E e8)
	{
		return new ReverseFlowFromValues.OfObject<>(asList(e1, e2, e3, e4, e5, e6, e7, e8));
	}

	public static DoubleFlow over(final double x1)
	{
		return new FlowFromValues.OfDouble(x1);
	}

	public static DoubleFlow over(final double x1, final double x2)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3, final double x4)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3, x4});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3, final double x4, final double x5)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3, x4, x5});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3, final double x4, final double x5, final double x6)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3, x4, x5, x6});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3, final double x4, final double x5, final double x6, final double x7)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3, x4, x5, x6, x7});
	}

	public static DoubleFlow over(final double x1, final double x2, final double x3, final double x4, final double x5, final double x6, final double x7, final double x8)
	{
		return new ReverseFlowFromValues.OfDouble(new double[] {x1, x2, x3, x4, x5, x6, x7, x8});
	}

	public static DoubleFlow over(final double[] source)
	{
		return new ReverseFlowFromValues.OfDouble(source);
	}

	public static LongFlow over(final long x1)
	{
		return new FlowFromValues.OfLong(x1);
	}

	public static LongFlow over(final long x1, final long x2)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2});
	}

	public static LongFlow over(final long x1, final long x2, final long x3)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3});
	}

	public static LongFlow over(final long x1, final long x2, final long x3, final long x4)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3, x4});
	}

	public static LongFlow over(final long x1, final long x2, final long x3, final long x4, final long x5)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3, x4, x5});
	}

	public static LongFlow over(final long x1, final long x2, final long x3, final long x4, final long x5, final long x6)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3, x4, x5, x6});
	}

	public static LongFlow over(final long x1, final long x2, final long x3, final long x4, final long x5, final long x6, final long x7)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3, x4, x5, x6, x7});
	}

	public static LongFlow over(final long x1, final long x2, final long x3, final long x4, final long x5, final long x6, final long x7, final long x8)
	{
		return new ReverseFlowFromValues.OfLong(new long[] {x1, x2, x3, x4, x5, x6, x7, x8});
	}

	public static LongFlow over(final long[] source)
	{
		return new ReverseFlowFromValues.OfLong(source);
	}

	public static IntFlow over(final int x1)
	{
		return new FlowFromValues.OfInt(x1);
	}

	public static IntFlow over(final int x1, final int x2)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2});
	}

	public static IntFlow over(final int x1, final int x2, final int x3)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3});
	}

	public static IntFlow over(final int x1, final int x2, final int x3, final int x4)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3, x4});
	}

	public static IntFlow over(final int x1, final int x2, final int x3, final int x4, final int x5)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3, x4, x5});
	}

	public static IntFlow over(final int x1, final int x2, final int x3, final int x4, final int x5, final int x6)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3, x4, x5, x6});
	}

	public static IntFlow over(final int x1, final int x2, final int x3, final int x4, final int x5, final int x6, final int x7)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3, x4, x5, x6, x7});
	}

	public static IntFlow over(final int x1, final int x2, final int x3, final int x4, final int x5, final int x6, final int x7, final int x8)
	{
		return new ReverseFlowFromValues.OfInt(new int[] {x1, x2, x3, x4, x5, x6, x7, x8});
	}

	public static IntFlow over(final int[] source)
	{
		return new ReverseFlowFromValues.OfInt(source);
	}
}
