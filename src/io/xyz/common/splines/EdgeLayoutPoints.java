/**
 * Copyright � 2017 Lhasa Limited
 * File created: 17 May 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines;

/**
 * An instance of this class is designed to be attached to a single instance of
 * {@link IPolyEdge} (say P with endpoints a := 'from' and b := 'to') which
 * represents the points in space at which P bends. The points contained in this
 * class, say p0,....,pn , are ordered such that the constituent lines of the
 * edge are {(a, p0), (p0, p1), .... , (pn, b)}. It is reversible to allow for
 * directed edges.
 *
 * @author ThomasB
 * @since 17 May 2017
 */
public class EdgeLayoutPoints // implements Iterable<Point3D>, IDataStreamSerializable
{
	// /**
	// * The knot points, i.e. the points the edge will be drawn through.
	// */
	// private final List<Point3D> knotPoints = new ArrayList<>();
	//
	// /**
	// * Flag to show whether this instance is considered to be reversed.
	// */
	// private boolean isReversed = false;
	//
	// public EdgeLayoutPoints(final List<Point3D> knotPoints)
	// {
	// for (final Point3D kp : knotPoints)
	// {
	// addKnot(kp);
	// }
	// }
	//
	// public EdgeLayoutPoints(final List<Point3D> knotPoints, final boolean
	// reversed)
	// {
	// this(knotPoints);
	// isReversed = reversed;
	// }
	//
	// public EdgeLayoutPoints(final EdgeLayoutPoints src)
	// {
	// isReversed = src.isReversed;
	//
	// for (final Point3D p : src.knotPoints)
	// {
	// addKnot(new Point3D(p));
	// }
	// }
	//
	// public EdgeLayoutPoints(){}
	//
	// /**
	// * Adds the parameter point to be the closest to the current 'to' vertex of
	// this edge.
	// * @param point
	// */
	// public void addKnot(final Point3D point)
	// {
	// knotPoints.add(point);
	// }
	//
	// /**
	// * Adds the parameter point to be the (i + 1)th point away from the current
	// 'from'
	// * vertex of this edge.
	// * @param i
	// * @param point
	// */
	// public void insertKnot(final int i, final Point3D point)
	// {
	// knotPoints.add(i, point);
	// }
	//
	// public void reverse()
	// {
	// Collections.reverse(knotPoints);
	// isReversed = !isReversed;
	// // final IVertex temp = startAnchor;
	// // startAnchor = endAnchor;
	// // endAnchor = temp;
	// }
	//
	// /**
	// * {@inheritDoc}
	// * @see java.lang.Iterable#iterator()
	// */
	// @Override
	// public Iterator<Point3D> iterator()
	// {
	// // Iterates from 'from' vertex to 'to' vertex
	// return knotPoints.iterator();
	// }
	//
	// public boolean isReversed()
	// {
	// return isReversed;
	// }
	//
	// public List<Point3D> getKnotPoints()
	// {
	// return knotPoints;
	// }
	//
	// public Point3D get(final int i)
	// {
	// return knotPoints.get(i);
	// }
	//
	// public Point3D rget(final int i)
	// {
	// return get(size() - (i + 1));
	// }
	//
	// /**
	// * Convenience methods for retrieving commonly used knotpoints.
	// */
	// public Point3D getFirstKnot()
	// {
	// return get(0);
	// }
	//
	// public Point3D getSecondKnot()
	// {
	// return get(1);
	// }
	//
	// public Point3D getLastKnot()
	// {
	// return get(size() - 1);
	// }
	//
	// public Point3D getPenultimateKnot()
	// {
	// return get(size() - 2);
	// }
	//
	// public int size()
	// {
	// return knotPoints.size();
	// }
	//
	// public boolean isEmpty()
	// {
	// return knotPoints.isEmpty();
	// }
	//
	// public void clear()
	// {
	// knotPoints.clear();
	// }
	//
	// public EdgeLayoutPoints peturb(final Point3D peturbation)
	// {
	// final List<Point3D> peturbedKnots = new ArrayList<>(knotPoints.size());
	// for (final Point3D kp : knotPoints)
	// {
	// peturbedKnots.add(kp.translate(peturbation));
	// }
	// return new EdgeLayoutPoints(peturbedKnots, isReversed);
	// }
	//
	// @Override
	// public EdgeLayoutPoints clone()
	// {
	// return new EdgeLayoutPoints(this);
	// }
	//
	// @Override
	// public String toString()
	// {
	// final StringBuilder sb = new StringBuilder("{");
	// for (final Point3D kp : knotPoints)
	// {
	// sb.append(kp.toString());
	// sb.append(", ");
	// }
	// sb.append("}");
	// return sb.toString();
	// }
	//
	// @Override
	// public int hashCode()
	// {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + (isReversed ? 1231 : 1237);
	// for (final Point3D p : knotPoints)
	// {
	// result = prime * result + ((p == null) ? 0 : p.hashCode());
	// }
	// return result;
	// }
	//
	// @Override
	// public boolean equals(final Object obj)
	// {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	//
	// final EdgeLayoutPoints other = (EdgeLayoutPoints) obj;
	// if (isReversed != other.isReversed)
	// return false;
	//
	// if (knotPoints == null)
	// {
	// if (other.knotPoints != null)
	// return false;
	// }
	// else if (!deepEquals(knotPoints, other.knotPoints))
	// return false;
	//
	// return true;
	// }
	//
	// private static <T> boolean deepEquals(final List<T> one, final List<T> two)
	// {
	// boolean equal = true;
	// final int n = one.size();
	//
	// if (n != two.size()) return false;
	//
	// for (int i = 0; i < n && equal; i++)
	// {
	// final T onei = one.get(i), twoi = two.get(i);
	//
	// if (onei == null && twoi == null) continue;
	//
	// else if (onei == null && twoi != null) equal = false;
	//
	// else equal = onei.equals(twoi);
	// }
	// return equal;
	// }
	//
	// //----------------------------------------------------------------------------------------------
	// /* Serialisation field IDs */
	// private static final String REVERSED = "reversed";
	// private static final String POINTS = "points";
	//
	// @Override
	// public void fromDataStream(final IDataStreamReader reader) throws
	// DataStreamSerializationException
	// {
	// try
	// {
	// isReversed = reader.readBoolean(REVERSED);
	// knotPoints.addAll(reader.readObjectCollection(Point3D.class));
	// }
	// catch (final Exception e)
	// {
	// throw new DataStreamSerializationException(e);
	// }
	// }
	//
	// @Override
	// public void toDataStream(final IDataStreamWriter writer) throws
	// DataStreamSerializationException
	// {
	// try
	// {
	// writer.writeBoolean(REVERSED, isReversed);
	// writer.writeObjects(POINTS, knotPoints);
	// }
	// catch (final Exception e)
	// {
	// throw new DataStreamSerializationException(e);
	// }
	// }
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