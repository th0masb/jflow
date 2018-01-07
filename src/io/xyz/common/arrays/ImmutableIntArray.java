/**
 * 
 */
package io.xyz.common.arrays;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableIntArray implements IntRangeDescriptor {

	private final int[] elements;

	private ImmutableIntArray(final int... elements)
	{
		this.elements = elements;
	}

	@Override
	public int get(final int index)
	{
		return elements[index];
	}

	public ImmutableIntArray sort()
	{
		return sort(0, rangeBound());
	}

	public ImmutableIntArray sort(final int fromIndex, final int toIndex)
	{
		// assert indexInRange(fromIndex, toIndex);
		final int[] copied = copyElements();
		Arrays.sort(copied, fromIndex, toIndex);
		return new ImmutableIntArray(copied);
	}

	public int[] copyElements()
	{
		return Arrays.copyOf(elements, rangeBound());
	}

	public static ImmutableIntArray of(@Nonnull final int... xs)
	{
		return constructFrom(Arrays.copyOf(xs, xs.length));
	}

	private static ImmutableIntArray constructFrom(final int... xs)
	{
		return new ImmutableIntArray(xs);
	}

	public static ImmutableIntArray of(@Nonnull final IntRangeDescriptor descriptor)
	{
		return constructFrom(descriptor);
	}

	private static ImmutableIntArray constructFrom(final IntRangeDescriptor descriptor)
	{
		return new ImmutableIntArray(descriptor.toArray());
	}

	public static ImmutableIntArray fill(@Nonnegative final int length, final int element)
	{
		assert length > -1;
		return constructFrom(ImmutableIntRangeDescriptor.of(length, i -> element));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.xyz.common.rangedescriptor.BaseRangeDescriptor#rangeBound()
	 */
	@Override
	public int rangeBound()
	{
		return elements.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#getDescriptor()
	 */
	@Override
	public IntUnaryOperator getDescriptor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#asIntRange(java.util.
	 * function.IntUnaryOperator)
	 */
	@Override
	public IntRangeDescriptor asIntRange(final IntUnaryOperator f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.xyz.common.rangedescriptor.IntRangeDescriptor#asDoubleRange(java.util.
	 * function.IntToDoubleFunction)
	 */
	@Override
	public DoubleRangeDescriptor asDoubleRange(final IntToDoubleFunction f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#asObjRange(java.util.
	 * function.IntFunction)
	 */
	@Override
	public <T> RangeDescriptor<T> asObjRange(final IntFunction<T> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.xyz.common.rangedescriptor.IntRangeDescriptor#filter(java.util.function.
	 * IntPredicate)
	 */
	@Override
	public IntRangeDescriptor filter(final IntPredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
