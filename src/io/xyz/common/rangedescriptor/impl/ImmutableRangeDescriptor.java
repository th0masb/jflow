/**
 *
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import io.xyz.common.rangedescriptor.AbstractRangeDescriptor;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author t
 *
 */
public final class ImmutableRangeDescriptor<T> extends AbstractRangeDescriptor implements RangeDescriptor<T> {

	private final IntFunction<T> descriptor;

	public ImmutableRangeDescriptor(final int rangeBound, final IntFunction<T> f) {
		super(rangeBound);
		descriptor = f;
	}

	@Override
	public <R> RangeDescriptor<R> map(final Function<T, R> f) {
		return new ImmutableRangeDescriptor<>(rangeBound(), compose(f, descriptor));
	}

	@Override
	public IntRangeDescriptor mapToInt(final ToIntFunction<T> f) {
		return new ImmutableIntRangeDescriptor(rangeBound(), compose(f, descriptor));
	}

	@Override
	public DoubleRangeDescriptor mapToDouble(final ToDoubleFunction<T> f) {
		return new ImmutableDoubleRangeDescriptor(rangeBound(), compose(f, descriptor));
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

	public static <T> RangeDescriptor<T> from(final List<T> xs) {
		return new ImmutableRangeDescriptor<>(len(xs), i -> xs.get(i));
	}

	@SafeVarargs
	public static <T> RangeDescriptor<T> from(final T... xs) {
		return new ImmutableRangeDescriptor<>(len(xs), i -> xs[i]);
	}

	@Override
	public IntFunction<T> getDescriptor() {
		return descriptor;
	}

	@Override
	public RangeDescriptor<T> filter(final Predicate<T> p) {
		throw new RuntimeException("NYI");
	}

}
