/**
 * 
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CompositionUtil.compose;

import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

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

	/**
	 * @param rangeBound
	 */
	public ImmutableRangeDescriptor(final int rangeBound, final IntFunction<T> f) {
		super(rangeBound);
		descriptor = f;
	}

	@Override
	public RangeDescriptor<T> map(final UnaryOperator<T> f) {
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

	@Override
	public IntFunction<T> getDescriptor() {
		return descriptor;
	}

	@Override
	public RangeDescriptor<T> filter(final Predicate<T> p) {
		// TODO Auto-generated method stub
		return null;
	}
}
