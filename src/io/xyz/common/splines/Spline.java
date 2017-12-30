/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import static java.util.Arrays.stream;
import static org.lhasalimited.common.function.FunctionalUtils.collect;
import static org.lhasalimited.common.function.FunctionalUtils.collectSet;
import static org.lhasalimited.common.function.FunctionalUtils.drange;
import static org.lhasalimited.common.function.FunctionalUtils.range;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

import org.lhasalimited.common.logging.Common;
import org.lhasalimited.common.math.ICurveParameterisation;
import org.lhasalimited.common.math.ILhasaToFXPointMap;
import org.lhasalimited.common.math.Point3D;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class Spline implements ISpline
{
	/** The constituent segments of this spline. */
	private final ISplineSegment[] segments;

	/** The approximated segment lengths */
	private final double[] approxLengths;

	/** Cached parameterisation of this spline */
	private final Curve parameterisation;

	public Spline(final List<ISplineSegment> segments, final IPolyEdge parent)
	{
		this.parent = parent;
		this.segments = segments.toArray(new ISplineSegment[0]);
		this.approxLengths = segments.stream().mapToDouble(seg -> seg.approximateLength()).toArray();
		parameterisation = calculateTotalParameterisation();
	}

	private Curve calculateTotalParameterisation()
	{
		final List<Curve> individuals = collect(stream(segments).map(seg -> seg.parameterise()));

		final double lenSum = getLengthApproximation();
		final double[] cumulativeRatios = stream(approxLengths).map(len -> len/lenSum).toArray();
		Arrays.parallelPrefix(cumulativeRatios, (a, b) -> a + b); // turns array into cumulative sums.

		return t ->
		{
			final int n = cumulativeRatios.length;
			final OptionalInt segIndex = range(n).filter(m -> t <= cumulativeRatios[m]).findFirst();

			if (!segIndex.isPresent())
			{
				return individuals.get(cumulativeRatios.length - 1).map(1);
			}

			final int i = segIndex.getAsInt();
			final double prevRatio = i == 0? 0 : cumulativeRatios[i-1];
			return individuals.get(i).map((t - prevRatio) / (cumulativeRatios[i] - prevRatio));
		};
	}

	@Override
	public void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateMap, final Point3D perturbation)
	{
		for (final ISplineSegment segment : segments)
		{
			segment.draw(gc, coordinateMap, perturbation);
		}
	}

	@Override
	public Curve parameterise()
	{
		return parameterisation;
	}

	@Override
	public IPolyEdge getParent()
	{
		return parent;
	}

	@Override
	public double getLengthApproximation()
	{
		return Arrays.stream(approxLengths).sum();
	}

	@Override
	public Set<Point3D> getPointApproximation(final double maximalSpacing)
	{
		final double stepSize = maximalSpacing/getLengthApproximation();
		return collectSet(drange(0, 1, stepSize).mapToObj(t -> parameterisation.map(t)));
	}

	@Override
	public ISpline peturb(final Point3D peturbation)
	{
		return new Spline(collect(range(segments.length).mapToObj(i -> segments[i].peturb(peturbation))), getParent());
	}

	public static void main(final String[] args)
	{
		final double[] d = {0.1, 0.2, 0.5, 0.2};
		Arrays.parallelPrefix(d, (a, b) -> a + b);

		Common.out.info(Arrays.toString(d));
	}
}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */