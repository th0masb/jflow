package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.intMap;
import static org.lhasalimited.common.chainutilities.PredicateUtil.allEqual;

import java.util.List;

import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.point.IPointND;

import com.google.common.collect.ImmutableList;

/**
 * @author ThomasB
 * @since 17 Nov 2017
 */
public abstract class AbstractSplineSegment
{
	/**
	 * The constituent points which form this spline segment. In general the curve
	 * does not travel through each of these points.
	 */
	private final ImmutableList<IPointND> constituents;

	public AbstractSplineSegment(final ImmutableList<IPointND> ps)
	{
		assert ps.size() > 1 && allEqual(intMap(p -> p.dim(), ps));
		constituents = ps;
	}

	public AbstractSplineSegment(final IPointND... ps)
	{
		this(ImmutableList.copyOf(ps));
	}

	public AbstractSplineSegment(final List<IPointND> ps)
	{
		this(ImmutableList.copyOf(ps));
	}

	public IPointND elementAt(final int index)
	{
		return constituents.get(index);
	}

	public int linkCount()
	{
		return len(constituents);
	}

	public IPointND from()
	{
		return elementAt(0);
	}

	public IPointND to()
	{
		return elementAt(linkCount() - 1);
	}

	public double length()
	{
		return ICurve.length(parameterise());
	}

	public int dim()
	{
		return from().dim();
	}

	public abstract ICurve parameterise();

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constituents == null) ? 0 : constituents.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AbstractSplineSegment other = (AbstractSplineSegment) obj;
		if (constituents == null) {
			if (other.constituents != null)
				return false;
		}
		else if (!constituents.equals(other.constituents))
			return false;
		return true;
	}
}
