/**
 *
 */
package io.xyz.common.generators.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import io.xyz.common.generators.AbstractGenerator;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.Generator;

/**
 * @author t
 *
 */
public final class ImmutableIntGenerator extends AbstractGenerator implements IntGenerator {

	private final IntUnaryOperator descriptor;

	private ImmutableIntGenerator(final int rangeBound, final IntUnaryOperator f)
	{
		super(rangeBound);
		this.descriptor = f;
	}

	public static ImmutableIntGenerator of(final int length, final IntUnaryOperator descriptor)
	{
		return new ImmutableIntGenerator(length, descriptor);
	}

	public static ImmutableIntGenerator from(final int... xs)
	{
		return new ImmutableIntGenerator(len(xs), i -> xs[i]);
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
	public IntGenerator asIntRange(final IntUnaryOperator f)
	{
		return new ImmutableIntGenerator(rangeBound(), f);
	}

	@Override
	public IntGenerator filter(final IntPredicate p)
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
		return new ImmutableIntGenerator(counter, i -> resizedFiltered[i]);
	}

	@Override
	public DoubleGenerator asDoubleRange(final IntToDoubleFunction f)
	{
		return ImmutableDoubleGenerator.of(rangeBound(), compose(f, descriptor));
	}

	@Override
	public <T> Generator<T> asObjRange(final IntFunction<T> f)
	{
		return new ImmutableGenerator<>(rangeBound(), compose(f, descriptor));
	}
}
