/**
 *
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import io.xyz.common.rangedescriptor.AbstractRangeDescriptor;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableIntRangeDescriptor extends AbstractRangeDescriptor implements IntRangeDescriptor {

	private final IntUnaryOperator descriptor;

	public ImmutableIntRangeDescriptor(final int rangeBound, final IntUnaryOperator f)
	{
		super(rangeBound);
		this.descriptor = f;
	}

	public static ImmutableIntRangeDescriptor from(final int... xs)
	{
		return new ImmutableIntRangeDescriptor(len(xs), i -> xs[i]);
	}

	// public static ImmutableIntRangeDescriptor range(final int n) {
	// return new ImmutableIntRangeDescriptor(n, i -> i);
	// }

	@Override
	public IntUnaryOperator getDescriptor()
	{
		return descriptor;
	}

	@Override
	public IntRangeDescriptor asIntRange(final IntUnaryOperator f)
	{
		return new ImmutableIntRangeDescriptor(rangeBound(), f);
	}

	@Override
	public IntRangeDescriptor filter(final IntPredicate p)
	{
		/*
		 * We don't lazily filter with this construction. Since we need a notion of
		 * length we must perform all calculations and it makes sense to keep them.
		 */
		final int n = len(this);
		final int[] filtered = new int[n];
		int counter = 0;
		for (int i = 0; i < n; i++) {
			if (p.test(get(i))) {
				filtered[counter++] = get(i);
			}
		}
		final int[] resizedFiltered = Arrays.copyOf(filtered, counter);
		return new ImmutableIntRangeDescriptor(counter, i -> resizedFiltered[i]);
	}

	@Override
	public DoubleRangeDescriptor asDoubleRange(final IntToDoubleFunction f)
	{
		return new ImmutableDoubleRangeDescriptor(rangeBound(), compose(f, descriptor));
	}

	@Override
	public <T> RangeDescriptor<T> asObjRange(final IntFunction<T> f)
	{
		return new ImmutableRangeDescriptor<>(rangeBound(), compose(f, descriptor));
	}
}
