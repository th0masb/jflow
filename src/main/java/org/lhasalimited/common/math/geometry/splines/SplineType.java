/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;


import static java.util.stream.Collectors.toList;
import static org.lhasalimited.common.chainutilities.CollectionUtil.append;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;
import static org.lhasalimited.common.chainutilities.RangeUtil.objRange;
import static org.lhasalimited.common.chainutilities.RangeUtil.range;
import static org.lhasalimited.common.chainutilities.StreamUtil.collect;

import java.util.Arrays;
import java.util.List;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.MathUtils;
import org.lhasalimited.common.math.geometry.point.IPoint2D;

/**
 * TODO - A spline should also be ableto generate its own parameterisation
 *
 * @author ThomasB
 * @since 3 Nov 2017
 */
public enum SplineType implements SplineConstructor {

//	/*
//	 * TODO - This isn't close to optimal in all situations yet, really need to create
//	 * and interactive 'playground' where you can toggle the tracelines on etc.
//	 */
//	C1_CURVE {
//		@Override
//		public ISpline constructFrom(final List<? extends IPointND> knots)
//		{
//			assert len(knots) > 1;
//			final int n = len(knots);
//			if (n == 2) {
//				return SHARP.constructFrom(knots);
//			} else if (n == 3) {
//				return new Spline(constructSameDirectionSpline(asFunction(knots)));
//			}
//
//			final List<Line> lines = collect(pairFold(Line::new, knots));
//			final DoubleChain angles = pairFoldToDouble((a, b) -> a.pathAngleWith(b), asFunction(lines));
//			final DoubleChain angleSigns = doubleMap(PrimitiveUtil::signum, angles);
//
//			final DoubleChain directionSwitches = pairFold((a,  b) -> b - a, angleSigns);
//			final BitArray innerLinesToSplit = boolRange(x -> !isZero(x), directionSwitches);
//
//			final List<List<IPointND>> components = new ArrayList<>();
//			List<IPointND> currentComponent = new ArrayList<>(asList(head(knots)));
//
//			for (int i = 0; i < n - 3; i++) {
//				final Line toAllocate = lines.get(i + 1);
//				currentComponent.add(toAllocate.from());
//				if (innerLinesToSplit.get(i)) {
//					final IPointND mid = toAllocate.midPoint();
//					currentComponent.add(mid);
//					components.add(currentComponent);
//					currentComponent = new ArrayList<>();
//					currentComponent.add(mid);
//				}
//			}
//
//			final Line last = tail(lines);
//			currentComponent.add(last.from());
//			currentComponent.add(last.to());
//			components.add(currentComponent);
//
//			final List<List<ISplineSegment>> beziers = objMap(xs -> constructSameDirectionSpline(asFunction(xs)), components);
//
//			final BinaryOperator<List<ISplineSegment>> connector = (a, b) ->
//			{
//				if (len(a) == 0) {
//					return b;
//				}
//				final List<ISplineSegment> combined = new ArrayList<>(len(a) + len(b) -1);
//				combined.addAll(a.subList(0, len(a) - 1));
//				combined.add(connect((QuadraticBezier) tail(a), (QuadraticBezier) head(b)));
//				combined.addAll(b.subList(1, len(b)));
//				return combined;
//			};
//
//			return new Spline(foldl(connector, Collections.emptyList(), beziers).get());
//		}
//
//		private CubicBezier connect(final QuadraticBezier a, final QuadraticBezier b)
//		{
//			return new CubicBezier(a.from(), a.control(), b.control(), b.to());
//		}
//
//		private List<ISplineSegment> constructSameDirectionSpline(final Chain<? extends IPointND> knots)
//		{
//			throw new RuntimeException();
////			assert len(knots) > 2;
////
////			final int n = len(knots);
////
////			final List<Line> lines = collect(pairFold(Line::new, knots));
////			final DoubleGenerator angles = pairFoldToDouble((a, b) -> a.pathAngleWith(b), asGenerator(lines));
////			final DoubleGenerator angleSigns = doubleMap(PrimitiveUtil::signum, angles);
////			assert allEqual(angleSigns);
////
////			final Generator<Line> traceLines = RangeUtil.objRange(i -> Line.between(knots.get(i), knots.get(i + 2)), n - 2);
////			final List<Line> controlLines = collect(RangeUtil.objRange(i -> traceLines.get(i).peturbToNewCentre(knots.get(i + 1)), len(traceLines)));
////			final Generator<IPointND> innerControlPoints = pairFold((a, b) -> Line.crossingPoint2D(a, b), controlLines);
////
////			/*
////			 * Now calculate the outer control points heuristically... TODO - explain.
////			 */
////			final IPointND firstKnot = head(knots), secondKnot = knots.get(1);
////			final IPointND penultimateKnot = knots.get(n - 2), lastKnot = tail(knots);
////
////			final Line firstControlLine = head(controlLines), lastControlLine = tail(controlLines);
////
////			final Line v1 = Line.between(firstKnot, secondKnot), v2 = Line.between(penultimateKnot, lastKnot);
////			final double theta1 = v1.minimalAngleWith(firstControlLine), theta2 = v2.minimalAngleWith(lastControlLine);
////			final double scaleFactor = 2 / Math.PI;
////			final double x1 = scaleFactor*theta1*v1.length(), x2 = scaleFactor*theta2*v2.length();
////
////			final IPointND firstControl = firstControlLine.travel(0.5*firstControlLine.length() - x1);
////			final IPointND lastControl = lastControlLine.travel(0.5*lastControlLine.length() + x2);
////
////			//			final RPoint firstControl = head(controlLines).perpendicularIntersection(head(knots));//Line.midPoint(knots.get(1), head(controlLines).perpendicularIntersection(head(knots)));
////			//			final RPoint lastControl = tail(controlLines).perpendicularIntersection(tail(knots));//Line.midPoint(knots.get(n - 2), tail(controlLines).perpendicularIntersection(tail(knots)));
////
////			final List<IPointND> controls = new ArrayList<>(len(innerControlPoints) + 2);
////			controls.add(firstControl);
////			controls.addAll(collect(innerControlPoints));
////			controls.add(lastControl);
////
////			return collect(RangeUtil.objRange(i -> new QuadraticBezier(knots.get(i), controls.get(i), knots.get(i + 1)), n - 1));
//		}
//	},

	SHARP
	{
		@Override
		public ISpline2D constructFrom(final List<? extends IPoint2D> knots)
		{
			final List<Line2D> x = range(len(knots) - 1).stream().mapToObj(i -> Line2D.between(knots.get(i), knots.get(i + 1))).collect(toList());
			return new Spline2D(x);
		}
	},

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
	CUSTOM_SUGIYAMA
	{
		@Override
		public ISpline2D constructFrom(final List<? extends IPoint2D> knots)
		{
			throw new RuntimeException();
//			final double scaleF = 0.85;
//
//			final List<Line> sharp = pairFold(Line::new, knots).toList();
//
//			final List<StraightSegment> scaledStraights = sharp.stream().map(x ->
//			x.scale(scaleF)).collect(Collectors.toList());
//
//			final int nJoins = scaledStraights.size() - 1;
//			final List<ISplineSegment> quadJoins = new ArrayList<>(nJoins);
//			for (int i = 0; i < nJoins; i++)
//			{
//				final Point3D start = scaledStraights.get(i).getEnd();
//				final Point3D end = scaledStraights.get(i + 1).getStart();
//				final Point3D ctrl = sharp.get(i).getEnd();
//				quadJoins.add(new QuadraticBezier(start, ctrl, end));//new
//			}
//
//			final List<ISplineSegment> finalSegments = new ArrayList<>(2 + nJoins +
//					scaledStraights.size());
//			finalSegments.add(new StraightSegment(sharp.get(0).getStart(),
//					scaledStraights.get(0).getStart()));
//			for (int i = 0; i < nJoins; i++)
//			{
//				finalSegments.add(scaledStraights.get(i));
//				finalSegments.add(quadJoins.get(i));
//			}
//			finalSegments.add(scaledStraights.get(nJoins));
//			finalSegments.add(new StraightSegment(scaledStraights.get(nJoins).getEnd(),
//					sharp.get(nJoins).getEnd()));
//
//			return new Spline(finalSegments, edge);
		}
	},
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
	C2_CURVE
	{
		/**
		 * The idea here is:
		 *
		 * Let E be our edge in question which has Knot points K0,....,Kn (endpoints
	 included).
		 * (NOTE- the curve is assumed to not be closed!! i.e K0 != Kn)
		 *
		 * We would like to make a curve C formed of n cubic bezier curves which
	 satisfies:
		 * - C is smooth (twice continuously differentiable)
		 * - C passes through each knot point at least once.
		 *
		 * In order to calculate the control points for each constituent curve we can
	 form a
		 * tri diagonal system of simultaneous equations arising from the smoothness
	 constraints
		 * and apply the Thomas algorithm to solve. We note that boundary conditions
	 are required
		 * to complete the system and so we say that we would like to the curve to be
	 linear at
		 * both endpoints (here is where we assume curve not closed).
		 *
		 * Definition of cubic bezier curve is: ((1-t)^3)P0 + (3t(1-t)^2)P1 +
	 (3(1-t)t^2)P2 + (t^3)P3
		 *
		 * differentiating and equating we obtain the system in terms of the unknown
	 P_j where we define
		 * P_j to be the first control point of the jth curve:
		 *
		 * P_(j-1) + 4P_j + P_(j+1) = 4K_j + 2K_(i+1) for j in [1, n - 2]
		 *
		 * Boundary conditions:
		 * 2P_0 + P_1 = K_0 + 2K_1
		 * 2P_(n-2) + 7P_(n-1) = 8K_(n-1) + K_n
		 *
		 * Once we have the first control point we easily get other.
		 */
		@Override
		public ISpline2D constructFrom(final List<? extends IPoint2D> knots)
		{
			/*
			 * First generate ai, bi, ci, di coefficients to apply the tridiagonal matrix
			 * algorithm (good article on wikipedia). We do this for both x and y
	 coordinates
			 * of the first control points.
			 */
			assert len(knots) > 1;
			final int n = len(knots) - 1;

			if (n == 1){
				return SHARP.constructFrom(knots);
			}

			final double[][] kxy = { doubleMap(k -> k.x(), knots), doubleMap(k -> k.y(), knots) };
			final double[][] axy = { new double[n], new double[n] }, bxy = copy(axy), cxy = copy(axy), dxy = copy(axy);

			/* Iterate over spatial dimensions. */
			for (int row = 0; row < len(kxy); row++) {
				// When i = 0
				bxy[row][0] = 2;
				cxy[row][0] = 1;
				dxy[row][0] = kxy[row][0] + 2*kxy[row][1];

				// When i = n-1
				axy[row][n-1] = 2;
				bxy[row][n-1] = 7;
				dxy[row][n-1] = 8*kxy[row][n-1] + kxy[row][n];

				// Otherwise
				for (int i = 1; i < n-1; i++) {
					axy[row][i] = 1;
					bxy[row][i] = 4;
					cxy[row][i] = 1;
					dxy[row][i] = 4*kxy[row][i] + 2*kxy[row][i+1];
				}
			}

			final double[][] p1 = {
					MathUtils.solveTridiagonalMatrixSystem(axy[0], bxy[0], cxy[0], dxy[0]),
					MathUtils.solveTridiagonalMatrixSystem(axy[1], bxy[1], cxy[1], dxy[1])
			};

			/*
			 * Collect the results for P1 into RPoint objects
			 */
			final List<IPoint2D> P1 = collect(objRange(i -> IPoint2D.of(p1[0][i], p1[1][i]), n));

			/*
			 * Calculate the coordinates for P2 from knot points and P1
			 */
			final Chain<IPoint2D> firstP2Controls = objRange(i -> knots.get(i+1).scale(2).subtract(P1.get(i+1)), n - 1);
			final IPoint2D lastP2Control = knots.get(n).add(P1.get(n-1)).scale(0.5);
			final List<IPoint2D> P2 = collect(append(lastP2Control, firstP2Controls));

			return new Spline2D(objRange(i -> new CubicBezier2D(knots.get(i), P1.get(i), P2.get(i), knots.get(i + 1)), n));
		}

		private double[][] copy(final double[][] src)
		{
			final double[][] cpy = new double[src.length][];
			for (int i = 0; i < src.length; i++) {
				cpy[i] = Arrays.copyOf(src[i], src[i].length);
			}
			return cpy;
		}
	};
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