/**
 *
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;

import io.xyz.common.rangedescriptor.AbstractRangeDescriptor;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableDoubleRangeDescriptor extends AbstractRangeDescriptor implements DoubleRangeDescriptor {

	private final IntToDoubleFunction descriptor;

	/**
	 * @param rangeBound
	 */
	private ImmutableDoubleRangeDescriptor(final int rangeBound, final IntToDoubleFunction f)
	{
		super(rangeBound);
		descriptor = f;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see io.xyz.common.rangedescriptor.DoubleRangeDescriptor#getDescriptor()
	 */
	@Override
	public IntToDoubleFunction getDescriptor()
	{
		return descriptor;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * io.xyz.common.rangedescriptor.DoubleRangeDescriptor#map(java.util.function.
	 * DoubleUnaryOperator)
	 */
	@Override
	public DoubleRangeDescriptor asDoubleRange(final DoubleUnaryOperator f)
	{
		return new ImmutableDoubleRangeDescriptor(rangeBound(), compose(f, descriptor));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * io.xyz.common.rangedescriptor.DoubleRangeDescriptor#filter(java.util.function
	 * .DoublePredicate)
	 */
	@Override
	public DoubleRangeDescriptor filter(final DoublePredicate p)
	{
		/*
		 * We don't lazily filter with this construction. Since we need a notion of
		 * length we must perform all calculations and it makes sense to keep them.
		 */
		final int n = len(this);
		final double[] filtered = new double[n];
		int counter = 0;
		for (int i = 0; i < n; i++) {
			if (p.test(get(i))) {
				filtered[counter++] = get(i);
			}
		}
		final double[] resizedFiltered = Arrays.copyOf(filtered, counter);
		return new ImmutableDoubleRangeDescriptor(counter, i -> resizedFiltered[i]);
	}
	//
	// @Override
	// public DoubleRangeDescriptor concat(final DoubleRangeDescriptor other)
	// {
	//
	// return null;
	// }

	@Override
	public String toString()
	{
		return Arrays.toString(toArray());
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final DoubleRangeDescriptor drd = ImmutableDoubleRangeDescriptor.from(new double[] { 1, 2, 3 });
		System.out.println(Arrays.toString(drd.toArray()));
	}

	@Override
	public IntRangeDescriptor asIntRange(final DoubleToIntFunction f)
	{
		return ImmutableIntRangeDescriptor.of(rangeBound(), compose(f, descriptor));
	}

	@Override
	public <T> RangeDescriptor<T> asObjRange(final DoubleFunction<T> f)
	{
		return new ImmutableRangeDescriptor<>(rangeBound(), compose(f, descriptor));
	}

	public static DoubleRangeDescriptor from(final double... xs)
	{
		return new ImmutableDoubleRangeDescriptor(len(xs), i -> xs[i]);
	}

	public static DoubleRangeDescriptor of(final int length, final IntToDoubleFunction descriptor)
	{
		return new ImmutableDoubleRangeDescriptor(length, descriptor);
	}
}
