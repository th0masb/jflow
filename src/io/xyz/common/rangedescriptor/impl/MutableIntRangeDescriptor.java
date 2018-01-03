/**
 * 
 */
package io.xyz.common.rangedescriptor.impl;

/**
 * @author t
 *
 */
public final class MutableIntRangeDescriptor {// extends AbstractRangeDescriptor implements IntRangeDescriptor {

	// private static final int DEFAULT_FILTER_CAPACITY = 1;
	//
	// private IntUnaryOperator descriptor;
	// private final List<IntPredicate> filters;
	//
	// private MutableIntRangeDescriptor(final int rangeBound, final
	// IntUnaryOperator f,
	// final List<IntPredicate> filters) {
	// super(rangeBound);
	// this.descriptor = f;
	// this.filters = isNull(filters)? new ArrayList<>(DEFAULT_FILTER_CAPACITY) :
	// filters;
	// }
	//
	// private MutableIntRangeDescriptor(final int rangeBound, final
	// IntUnaryOperator f) {
	// this(rangeBound, f, null);
	// }
	//
	// public static MutableIntRangeDescriptor from(final int... xs) {
	// return new MutableIntRangeDescriptor(len(xs), i -> xs[i]);
	// }
	//
	// public static MutableIntRangeDescriptor range(final int n) {
	// assert n > 0;
	// return new MutableIntRangeDescriptor(n, i -> i);
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#getDescriptor()
	// */
	// @Override
	// public IntUnaryOperator getDescriptor() {
	// return descriptor;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#rangeBound()
	// */
	// @Override
	// public int rangeBound() {
	// return getAsInt();
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see io.xyz.common.rangedescriptor.IntRangeDescriptor#getFilters()
	// */
	// @Override
	// public List<IntPredicate> getFilters() {
	// return filters;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// io.xyz.common.rangedescriptor.IntRangeDescriptor#map(java.util.function.
	// * IntUnaryOperator)
	// */
	// @Override
	// public IntRangeDescriptor map(final IntUnaryOperator f) {
	// descriptor = descriptor.andThen(f);
	// return this;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * io.xyz.common.rangedescriptor.IntRangeDescriptor#filter(java.util.function.
	// * IntPredicate)
	// */
	// @Override
	// public IntRangeDescriptor filter(final IntPredicate p) {
	// filters.add(p);
	// return this;
	// }

}
