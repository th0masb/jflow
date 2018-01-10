/**
 *
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import javax.annotation.Nonnegative;

import io.xyz.common.rangedescriptor.AbstractRangeDescriptor;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableRangeDescriptor<T> extends AbstractRangeDescriptor implements RangeDescriptor<T> {

	private final IntFunction<? extends T> descriptor;

	public ImmutableRangeDescriptor(final int rangeBound, final IntFunction<? extends T> f)
	{
		super(rangeBound);
		descriptor = f;
	}

	@Override
	public <R> RangeDescriptor<R> asObjRange(final Function<T, R> f)
	{
		return new ImmutableRangeDescriptor<>(rangeBound(), compose(f, descriptor));
	}

	@Override
	public IntRangeDescriptor asIntRange(final ToIntFunction<T> f)
	{
		return ImmutableIntRangeDescriptor.of(rangeBound(), compose(f, descriptor));
	}

	@Override
	public DoubleRangeDescriptor asDoubleRange(final ToDoubleFunction<T> f)
	{
		return ImmutableDoubleRangeDescriptor.of(rangeBound(), compose(f, descriptor));
	}

	@SuppressWarnings("unchecked")
	@Override
	public RangeDescriptor<T> filter(final Predicate<T> p)
	{
		/*
		 * We don't lazily filter with this construction. Since we need a notion of
		 * length we must perform all calculations and it makes sense to keep them.
		 */
		final int n = len(this);
		final List<T> filtered = new ArrayList<>(n);
		final int counter = 0;
		for (int i = 0; i < n; i++) {
			if (p.test(get(i))) {
				filtered.add(get(i));
			}
		}
		/* Conserve memory by removing unnecessary space allocation. */
		final Object[] resizedFiltered = filtered.toArray();
		return new ImmutableRangeDescriptor<>(counter, i -> (T) resizedFiltered[i]);
	}

	public static <T> RangeDescriptor<T> from(final List<? extends T> xs)
	{
		return new ImmutableRangeDescriptor<>(len(xs), i -> xs.get(i));
	}

	@SafeVarargs
	public static <T, E extends T> RangeDescriptor<T> from(final E... xs)
	{
		return new ImmutableRangeDescriptor<>(len(xs), i -> xs[i]);
	}

	public static <T> RangeDescriptor<T> of(@Nonnegative final int size, final IntFunction<? extends T> f)
	{
		return new ImmutableRangeDescriptor<>(size, f);
	}

	@Override
	public IntFunction<? extends T> getDescriptor()
	{
		return descriptor;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		// TODO Auto-generated method stub

	}
}
