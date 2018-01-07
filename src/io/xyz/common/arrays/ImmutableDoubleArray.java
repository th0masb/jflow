/**
 * 
 */
package io.xyz.common.arrays;

import static io.xyz.common.funcutils.MapUtil.doubleRange;

import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableDoubleArray implements DoubleRangeDescriptor {

	private final double[] elements;

	private ImmutableDoubleArray(final double[] elements)
	{
		this.elements = elements;
	}

	@Override
	public double get(final int index)
	{
		return elements[index];
	}

	public ImmutableDoubleArray sort()
	{
		return sort(0, rangeBound());
	}

	public ImmutableDoubleArray sort(final int fromIndex, final int toIndex)
	{
		// assert indexInRange(fromIndex, toIndex);
		final double[] copied = copyElements();
		Arrays.sort(copied, fromIndex, toIndex);
		return new ImmutableDoubleArray(copied);
	}

	public double[] copyElements()
	{
		return Arrays.copyOf(elements, rangeBound());
	}

	public static ImmutableDoubleArray of(@Nonnull final double... xs)
	{
		return constructFrom(Arrays.copyOf(xs, xs.length));
	}

	private static ImmutableDoubleArray constructFrom(final double... xs)
	{
		return new ImmutableDoubleArray(xs);
	}

	public static ImmutableDoubleArray of(@Nonnull final DoubleRangeDescriptor descriptor)
	{
		return constructFrom(descriptor);
	}

	private static ImmutableDoubleArray constructFrom(final DoubleRangeDescriptor descriptor)
	{
		return new ImmutableDoubleArray(descriptor.toArray());
	}

	public static ImmutableDoubleArray fill(@Nonnegative final int length, final double element)
	{
		assert length > -1;
		return constructFrom(ImmutableDoubleRangeDescriptor.of(length, i -> element));
	}

	@Override
	public int rangeBound()
	{
		return elements.length;
	}

	@Override
	public IntToDoubleFunction getDescriptor()
	{
		return i -> elements[i];
	}

	@Override
	public DoubleRangeDescriptor asDoubleRange(final DoubleUnaryOperator f)
	{
		return constructFrom(doubleRange(f, elements));
	}

	@Override
	public IntRangeDescriptor asIntRange(final DoubleToIntFunction f)
	{
		return null;// constructFrom(intRange(f, elements));
	}

	@Override
	public <T> RangeDescriptor<T> asObjRange(final DoubleFunction<T> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleRangeDescriptor filter(final DoublePredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
