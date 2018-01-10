/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.head;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CollectionUtil.tail;
import static io.xyz.common.funcutils.FoldUtil.pairFold;
import static io.xyz.common.funcutils.FoldUtil.pairFoldToDouble;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.objRange;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.ArrayList;
import java.util.List;

import io.xyz.common.funcutils.PrimitiveUtil;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * TODO - A spline should also be ableto generate its own parameterisation
 *
 * @author ThomasB
 * @since 3 Nov 2017
 */
public enum SplineType implements SplineConstructor {

	C1_CURVE {
		@Override
		public ISpline constructFrom(final List<RPoint> knots)
		{
			assert len(knots) > 1;
			final int n = len(knots);
			if (n == 2) {
				return null;//Line.between(knots.get(0), knots.get(1));
			}

			final List<Line> lines = collect(pairFold(Line::new, knots));
			final DoubleRangeDescriptor angles = pairFoldToDouble((a, b) -> a.pathAngleWith(b), asDescriptor(lines));
			final DoubleRangeDescriptor angleSigns = doubleRange(PrimitiveUtil::signum, angles);
			assert allEqual(angleSigns);

			final RangeDescriptor<Line> traceLines = objRange(i -> Line.between(knots.get(i), knots.get(i + 2)), n - 2);
			final List<Line> controlLines = collect(objRange(i -> traceLines.get(i).peturbToNewCentre(knots.get(i + 1)), len(traceLines)));
			final RangeDescriptor<RPoint> innerControlPoints = pairFold((a, b) -> Line.crossingPoint2D(a, b), controlLines);

			//			Line firstControl = controlLines.get(0), lastControl = con
			final RPoint firstControl = Line.midPoint(knots.get(1), head(controlLines).perpendicularIntersection(head(knots)));
			final RPoint lastControl = Line.midPoint(knots.get(n - 2), tail(controlLines).perpendicularIntersection(tail(knots)));

			final List<RPoint> controls = new ArrayList<>(len(innerControlPoints) + 2);
			controls.add(firstControl);
			controls.addAll(collect(innerControlPoints));
			controls.add(lastControl);

			final RangeDescriptor<QuadraticBezier> segments = objRange(i -> new QuadraticBezier(knots.get(i), controls.get(i), knots.get(i + 1)), n - 1);
			//			RPoint firstControl =
			// TODO Auto-generated method stub
			return null;
		}
	};

	// /**
	// * The most basic of spline types, we simply have a sequance of straight lines
	// * connecting the constituent knot points.
	// */
	// SHARP
	// {
	// @Override
	// public ISpline constructFrom(final IPolyEdge edge)
	// {
	// final List<Point3D> constituents = edge.getLayoutPoints().getKnotPoints();
	// final List<ISplineSegment> lineSegments = IntStream.range(0,
	// constituents.size() - 1)
	// .mapToObj(i -> new StraightSegment(constituents.get(i),
	// constituents.get(i+1)))
	// .collect(Collectors.toList());
	// return new Spline(lineSegments, edge);
	// }
	// },
	//
	// CUSTOM_SUGIYAMA
	// {
	// @Override
	// public ISpline constructFrom(final IPolyEdge edge)
	// {
	// final double scaleF = 0.85;
	//
	// final List<Point3D> constituents = edge.getLayoutPoints().getKnotPoints();
	// final List<StraightSegment> sharp = IntStream.range(0, constituents.size() -
	// 1)
	// .mapToObj(i -> new StraightSegment(constituents.get(i),
	// constituents.get(i+1)))
	// .collect(Collectors.toList());
	//
	// final List<StraightSegment> scaledStraights = sharp.stream().map(x ->
	// x.scale(scaleF)).collect(Collectors.toList());
	//
	// final int nJoins = scaledStraights.size() - 1;
	// final List<ISplineSegment> quadJoins = new ArrayList<>(nJoins);
	// for (int i = 0; i < nJoins; i++)
	// {
	// final Point3D start = scaledStraights.get(i).getEnd();
	// final Point3D end = scaledStraights.get(i + 1).getStart();
	// final Point3D ctrl = sharp.get(i).getEnd();
	// quadJoins.add(new QuadraticBezier(start, ctrl, end));//new
	// StraightSegment(start, end));//);
	// }
	//
	// final List<ISplineSegment> finalSegments = new ArrayList<>(2 + nJoins +
	// scaledStraights.size());
	// finalSegments.add(new StraightSegment(sharp.get(0).getStart(),
	// scaledStraights.get(0).getStart()));
	// for (int i = 0; i < nJoins; i++)
	// {
	// finalSegments.add(scaledStraights.get(i));
	// finalSegments.add(quadJoins.get(i));
	// }
	// finalSegments.add(scaledStraights.get(nJoins));
	// finalSegments.add(new StraightSegment(scaledStraights.get(nJoins).getEnd(),
	// sharp.get(nJoins).getEnd()));
	//
	// return new Spline(finalSegments, edge);
	// }
	// },
	//
	// NAIVE_BEZIER // Must have no more than four control points
	// {
	// @Override
	// public ISpline constructFrom(final IPolyEdge edge)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	// },
	//
	// PIECEWISE_CUBIC_BEZIER
	// {
	// /**
	// * The idea here is:
	// *
	// * Let E be our edge in question which has Knot points K0,....,Kn (endpoints
	// included).
	// * (NOTE- the curve is assumed to not be closed!! i.e K0 != Kn)
	// *
	// * We would like to make a curve C formed of n cubic bezier curves which
	// satisfies:
	// * - C is smooth (twice continuously differentiable)
	// * - C passes through each knot point at least once.
	// *
	// * In order to calculate the control points for each constituent curve we can
	// form a
	// * tri diagonal system of simultaneous equations arising from the smoothness
	// constraints
	// * and apply the Thomas algorithm to solve. We note that boundary conditions
	// are required
	// * to complete the system and so we say that we would like to the curve to be
	// linear at
	// * both endpoints (here is where we assume curve not closed).
	// *
	// * Definition of cubic bezier curve is: ((1-t)^3)P0 + (3t(1-t)^2)P1 +
	// (3(1-t)t^2)P2 + (t^3)P3
	// *
	// * differentiating and equating we obtain the system in terms of the unknown
	// P_j where we define
	// * P_j to be the first control point of the jth curve:
	// *
	// * P_(j-1) + 4P_j + P_(j+1) = 4K_j + 2K_(i+1) for j in [1, n - 2]
	// *
	// * Boundary conditions:
	// * 2P_0 + P_1 = K_0 + 2K_1
	// * 2P_(n-2) + 7P_(n-1) = 8K_(n-1) + K_n
	// *
	// * Once we have the first control point we easily get other.
	// */
	// @Override
	// public ISpline constructFrom(final IPolyEdge edge)
	// {
	// /*
	// * First generate ai, bi, ci, di coefficients to apply the tridiagonal matrix
	// * algorithm (good article on wikipedia). We do this for both x and y
	// coordinates
	// * of the first control points.
	// */
	// final Point3D[] K = edge.getLayoutPoints().getKnotPoints().toArray(new
	// Point3D[0]);
	// final int n = K.length - 1;
	//
	// if (n == 1)
	// {
	// // Use straight line
	// return SHARP.constructFrom(edge);
	// }
	//
	// final double[][] kxy = {
	// Arrays.stream(K).mapToDouble(k -> k.getX()).toArray(),
	// Arrays.stream(K).mapToDouble(k -> k.getY()).toArray()
	// };
	//
	// final double[][] axy = { new double[n], new double[n] }, bxy = copy(axy), cxy
	// = copy(axy), dxy = copy(axy);
	//
	// /* Iterate over spatial dimensions. */
	// for (int row = 0; row < kxy.length; row++)
	// {
	// // When i = 0
	// bxy[row][0] = 2;
	// cxy[row][0] = 1;
	// dxy[row][0] = kxy[row][0] + 2*kxy[row][1];
	//
	// // When i = n-1
	// axy[row][n-1] = 2;
	// bxy[row][n-1] = 7;
	// dxy[row][n-1] = 8*kxy[row][n-1] + kxy[row][n];
	//
	// // Otherwise
	// for (int i = 1; i < n-1; i++)
	// {
	// axy[row][i] = 1;
	// bxy[row][i] = 4;
	// cxy[row][i] = 1;
	// dxy[row][i] = 4*kxy[row][i] + 2*kxy[row][i+1];
	// }
	// }
	//
	// final double[][] p1 = {
	// MathUtils.solveTridiagonalMatrixSystem(axy[0], bxy[0], cxy[0], dxy[0]),
	// MathUtils.solveTridiagonalMatrixSystem(axy[1], bxy[1], cxy[1], dxy[1])
	// };
	//
	// // Collect the calculated coordinated for P1
	// final Point3D[] P1 = new Point3D[n];
	// for (int i = 0; i < n; i++)
	// {
	// P1[i] = new Point3D(p1[0][i], p1[1][i], 0);
	//
	// }
	//
	// // Calculate the coordianates for P2
	// final Point3D[] P2 = new Point3D[n];
	// for (int i = 0; i < n-1; i++)
	// {
	// P2[i] = K[i+1].scale(2).subtract(P1[i+1]);
	// }
	// P2[n-1] = K[n].translate(P1[n-1]).scale(0.5);
	//
	// // Generate the cubic segments
	// final List<ISplineSegment> segments = new ArrayList<>(n);
	// for (int i = 0; i < n; i++)
	// {
	// segments.add(new CubicBezier(K[i], P1[i], P2[i], K[i+1]));
	// }
	// return new Spline(segments, edge);
	// }
	//
	// private double[][] copy(final double[][] src)
	// {
	// final double[][] cpy = new double[src.length][];
	// for (int i = 0; i < src.length; i++)
	// {
	// cpy[i] = Arrays.copyOf(src[i], src[i].length);
	// }
	// return cpy;
	// }
	// };
}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */