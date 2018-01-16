/**
 *
 */
package io.xyz.common.generators.impl;

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

import io.xyz.common.generators.AbstractGenerator;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.Generator;

/**
 * @author t
 *
 */
public final class ImmutableGenerator<T> extends AbstractGenerator implements Generator<T> {

	private final IntFunction<? extends T> descriptor;

	public ImmutableGenerator(final int rangeBound, final IntFunction<? extends T> f)
	{
		super(rangeBound);
		descriptor = f;
	}

	@Override
	public <R> Generator<R> asObjRange(final Function<T, R> f)
	{
		return new ImmutableGenerator<>(rangeBound(), compose(f, descriptor));
	}

	@Override
	public IntGenerator asIntRange(final ToIntFunction<T> f)
	{
		return ImmutableIntGenerator.of(rangeBound(), compose(f, descriptor));
	}

	@Override
	public DoubleGenerator asDoubleRange(final ToDoubleFunction<T> f)
	{
		return ImmutableDoubleGenerator.of(rangeBound(), compose(f, descriptor));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Generator<T> filter(final Predicate<T> p)
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
		return new ImmutableGenerator<>(counter, i -> (T) resizedFiltered[i]);
	}

	public static <T> Generator<T> from(final List<? extends T> xs)
	{
		return new ImmutableGenerator<>(len(xs), i -> xs.get(i));
	}

	@SafeVarargs
	public static <T, E extends T> Generator<T> from(final E... xs)
	{
		return new ImmutableGenerator<>(len(xs), i -> xs[i]);
	}

	public static <T> Generator<T> of(@Nonnegative final int size, final IntFunction<? extends T> f)
	{
		return new ImmutableGenerator<>(size, f);
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
