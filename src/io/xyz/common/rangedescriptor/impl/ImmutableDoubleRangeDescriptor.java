/**
 *
 */
package io.xyz.common.rangedescriptor.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;

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
public final class ImmutableDoubleRangeDescriptor extends AbstractRangeDescriptor implements DoubleRangeDescriptor<ImmutableDoubleRangeDescriptor> {

	private final IntToDoubleFunction descriptor;

	/**
	 * @param rangeBound
	 */
	public ImmutableDoubleRangeDescriptor(final int rangeBound, final IntToDoubleFunction f) {
		super(rangeBound);
		descriptor = f;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see io.xyz.common.rangedescriptor.DoubleRangeDescriptor#getDescriptor()
	 */
	@Override
	public IntToDoubleFunction getDescriptor() {
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
	public DoubleRangeDescriptor mapToSameDescriptor(final DoubleUnaryOperator f) {
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
	public DoubleRangeDescriptor filter(final DoublePredicate p) {
		throw new RuntimeException("NYI");
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public IntRangeDescriptor mapToIntDescriptor(final DoubleToIntFunction f) {
		return new ImmutableIntRangeDescriptor(rangeBound(), compose(f, descriptor));
	}

	@Override
	public <T> RangeDescriptor<T> mapToObjDescriptor(final DoubleFunction<T> f) {
		return new ImmutableRangeDescriptor<>(rangeBound(), compose(f, descriptor));
	}

	public static DoubleRangeDescriptor from(final double[] xs) {
		return new ImmutableDoubleRangeDescriptor(len(xs), i -> xs[i]);
	}
}
