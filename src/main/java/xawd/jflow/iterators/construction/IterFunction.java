/**
 *
 */
package xawd.jflow.iterators.construction;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.FlowFromFunction;

/**
 * @author ThomasB
 *
 */
public final class IterFunction
{
	private IterFunction() {}

	public static <T> Flow<T> objectsFrom(final IntFunction<T> f)
	{
		return new FlowFromFunction.OfObject<>(f);
	}

	public static <T> Flow<T> objectsFrom(final IntFunction<T> f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfObject<>(f, elementCount);
	}

	public static LongFlow longsFrom(final IntToLongFunction f)
	{
		return new FlowFromFunction.OfLong(f);
	}

	public static LongFlow longsFrom(final IntToLongFunction f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfLong(f, elementCount);
	}

	public static IntFlow intsFrom(final IntUnaryOperator f)
	{
		return new FlowFromFunction.OfInt(f);
	}

	public static IntFlow intsFrom(final IntUnaryOperator f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfInt(f, elementCount);
	}

	public static DoubleFlow doublesFrom(final IntToDoubleFunction f)
	{
		return new FlowFromFunction.OfDouble(f);
	}

	public static DoubleFlow doublesFrom(final IntToDoubleFunction f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfDouble(f, elementCount);
	}
}
