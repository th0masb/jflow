/**
 *
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.CombineUtil.combine;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * This class represents a line in n dimensional space
 *
 * TODO - We need to tidy this up and probably subclass to deal with 2d lines separately
 *
 * @author ThomasB
 * @since 9 Feb 2018
 */
public class Line extends AbstractLine implements ISplineSegment
{
	public Line(final IPointND from, final IPointND to)
	{
		super(from, to);
	}

	public static Line between(final IPointND start, final IPointND end)
	{
		return new Line(start, end);
	}

	public Line peturbToNewCentre(final IPointND newCentre)
	{
		return peturb(newCentre.subtract(midPoint()));
	}

	public IPointND midPoint()
	{
		return IPointND.immutableOf(combine((x1, x2) -> (x1 + x2) / 2, from(), to()));
	}

	public double minimalAngleWith(final Line other)
	{
		return Math.acos(direction().dot(other.direction()));
	}

	@Override
	public Line map(final UnaryOperator<IPointND> f)
	{
		return Line.between(f.apply(from()), f.apply(to()));
	}

	@Override
	public Line peturb(final IPointND peturbation)
	{
		return map(p -> p.add(peturbation));
	}
}
