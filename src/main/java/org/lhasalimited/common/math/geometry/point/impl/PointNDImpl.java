/**
 *
 */
package org.lhasalimited.common.math.geometry.point.impl;

import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.CollectionUtil.str;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;
import static org.lhasalimited.common.chainutilities.MapUtil.objMap;
import static org.lhasalimited.common.chainutilities.PredicateUtil.all;
import static org.lhasalimited.common.chainutilities.PredicateUtil.any;
import static org.lhasalimited.common.chainutilities.RangeUtil.range;
import static org.lhasalimited.common.chainutilities.StreamUtil.collect;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator.OfDouble;
import java.util.function.DoubleUnaryOperator;

import org.lhasalimited.common.chains.DoubleChain;
import org.lhasalimited.common.chains.rangedfunctions.ImmutableDoubleChainSpliterator;
import org.lhasalimited.common.math.geometry.PointBiOperator;
import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * @author t
 *
 *         R^n real point represented as a column vector, i.e. a nx1 matrix.
 */
public final class PointNDImpl implements IPointND {

	private static final List<IPointND> ORIGIN = collect(objMap(PointNDImpl::originInit, range(1, 15)));

	private final double[] contents;

	public PointNDImpl(final double... ds)
	{
		contents = Arrays.copyOf(ds, len(ds));
	}

	public PointNDImpl(final DoubleChain xs)
	{
		assert all(x -> Double.isFinite(x), xs) : str(xs.toArray());
		assert all(x -> !Double.isNaN(x), xs) : str(xs.toArray());
		contents = collect(xs);
	}

	@Override
	public double elementAt(final int index)
	{
		assert indexIsInRange(index);
		return contents[index];
	}

	@Override
	public int dim()
	{
		return len(contents);
	}

	@Override
	public IPointND apply(final DoubleUnaryOperator f)
	{
		return new PointNDImpl(doubleMap(f, this));
	}

	@Override
	public IPointND add(final IPointND other)
	{
		return operateL(PointBiOperator.SUM, other);
	}

	public static IPointND origin(final int dim)
	{
		return ORIGIN.get(dim);
	}

	private static IPointND originInit(final int dim)
	{
		assert dim > 0;
		return new PointNDImpl(new double[dim]);
	}

	@Override
	public OfDouble spliterator()
	{
		return new ImmutableDoubleChainSpliterator(this);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		for (int i = 0; i < dim(); i++) {
			final long temp = Double.doubleToLongBits(elementAt(i));
			result = prime * result + (int) (temp ^ (temp >>> 32));
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IPointND))
			return false;
		final IPointND other = (IPointND) obj;
		if (other.dim() != dim()) {
			return false;
		}
		if (any(i -> Double.doubleToLongBits(elementAt(i)) != Double.doubleToLongBits(other.elementAt(i)), range(dim()))) {
			return false;
		}
		return true;
	}

}
