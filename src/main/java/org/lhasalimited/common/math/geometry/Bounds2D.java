/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static org.lhasalimited.common.chainutilities.CollectionUtil.str;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.isZero;

import java.io.IOException;

import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.serialization.IDataStreamReader;
import org.lhasalimited.common.serialization.IDataStreamSerializable;
import org.lhasalimited.common.serialization.IDataStreamWriter;

import com.google.common.collect.ImmutableList;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * 2D rectangular bounds, immutable.
 *
 * @author ThomasB
 * @since 19 Jan 2018
 */
public final class Bounds2D
{
	private final double minX, minY, width, height;

	private Bounds2D(final IPoint2D upperLeft, final double width, final double height)
	{
		this(upperLeft.x(), upperLeft.y(), width, height);
	}

	private Bounds2D(final double minX, final double minY, final double width, final double height)
	{
		this.minX = minX;
		this.minY = minY;
		this.width = width;
		this.height = height;
	}

	public boolean contains(final double x, final double y)
	{
		final boolean xInRange = minX() <= x && x <= maxX();
		final boolean yInRange = minY() <= y && y <= maxY();
		return xInRange && yInRange;
	}

	public IPoint2D centre()
	{
		return IPoint2D.of((minX + maxX()) / 2, (minY + maxY()) / 2);
	}

	public IPoint2D upperLeftCorner()
	{
		return IPoint2D.of(minX, minY);
	}

	public IPoint2D lowerRightCorner()
	{
		return IPoint2D.of(maxX(), maxY());
	}

	public Bounds2D scale(final double factor)
	{
		assert !isZero(factor) : "do you need to collapse Bounds to a point?";
		final double newW = factor * width, newH = factor * height;
		return new Bounds2D(minX - (newW - width) / 2, minY - (newH - height) / 2, newW, newH);
	}

	public Bounds2D translate(final double dx, final double dy)
	{
		return new Bounds2D(minX + dx, minY + dy, width, height);
	}

	public Bounds2D centreAt(final IPoint2D newCentre)
	{
		return new Bounds2D(newCentre.x() - width/2, newCentre.y() - height/2, width, height);
	}

	public Bounds2D expandLeft(final double x)
	{
		assert minX - x < maxX();
		return Bounds2D.of(minX - x, minY, width + x, height);
	}

	public Bounds2D expandRight(final double x)
	{
		assert maxX() + x > minX() : "Bounds: " + str(this) + " x: " + str(x);
		return Bounds2D.of(minX, minY, width + x, height);
	}

	public Bounds2D expandByConstant(final double c)
	{
		return Bounds2D.of(minX - c, minY - c, width + 2*c, height + 2*c);
	}

	public boolean contains(final Bounds2D other)
	{
		return minX <= other.minX && minY <= other.minY && other.maxX() <= maxX() && other.maxY() <= maxY();
	}

	public boolean intersects(final Bounds2D other)
	{
		return !(minX + width < other.minX
				|| other.minX + other.width < minX
				|| minY + height < other.minY
				|| other.minY + other.height < minY);
	}

	public boolean contains(final IPoint2D p)
	{
		return minX <= p.x() && p.x() <= maxX() && minY <= p.y() && p.y() <= maxY();
	}

	public ImmutableList<IPoint2D> getCorners()
	{
		return ImmutableList.of(
				IPoint2D.of(minX(), minY()),
				IPoint2D.of(maxX(), minY()),
				IPoint2D.of(maxX(), maxY()),
				IPoint2D.of(minX(), maxY()));
	}

	public Bounds toFxBounds()
	{
		return new BoundingBox(minX, minY, width, height);
	}

	public DTO toSerializableDTO()
	{
		return new DTO(this);
	}

	public boolean isDegenerate()
	{
		return isZero(width) && isZero(height);
	}

	public double minX()
	{
		return minX;
	}

	public double maxX()
	{
		return minX + width;
	}

	public double minY()
	{
		return minY;
	}

	public double maxY()
	{
		return minY + height;
	}

	public double width()
	{
		return width;
	}

	public double height()
	{
		return height;
	}

	public static Bounds2D of(final IPoint2D upperLeft, final double width, final double height)
	{
		return new Bounds2D(upperLeft, width, height);
	}

	public static Bounds2D of(final double minX, final double minY, final double width, final double height)
	{
		return new Bounds2D(minX, minY, width, height);
	}

	public static Bounds2D of(final Bounds fxBounds)
	{
		return new Bounds2D(fxBounds.getMinX(), fxBounds.getMinY(), fxBounds.getWidth(), fxBounds.getHeight());
	}

	public static Bounds2D inscribedSquare(final IPoint2D centre, final double radius)
	{
		assert radius > 0;
		final double halfSqSideLength = 0.5 * Math.sqrt(2) * radius;
		return Bounds2D.of(centre.x() - halfSqSideLength, centre.y() - halfSqSideLength, 2 * halfSqSideLength, 2 * halfSqSideLength);
	}

	public static Bounds2D circumscribedSquare(final IPoint2D centre, final double radius)
	{
		assert radius > 0;
		return Bounds2D.of(centre.x() - radius / 2, centre.y() - radius / 2, radius, radius);
	}

	public Polygon asRectangle()
	{
		return Polygon.of(getCorners());
	}

	@Override
	public String toString()
	{
		return toFxBounds().toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		final Bounds2D other = (Bounds2D) obj;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(minX) != Double.doubleToLongBits(other.minX))
			return false;
		if (Double.doubleToLongBits(minY) != Double.doubleToLongBits(other.minY))
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

	/**
	 *
	 * @author ThomasB
	 * @since 22 Feb 2018
	 */
	public static class DTO implements IDataStreamSerializable
	{
		private static final String MIN_X = "minx", MIN_Y = "miny", WIDTH = "width", HEIGHT = "height";

		private double minX, minY, width, height;

		public DTO() {}

		public DTO(final Bounds2D bounds)
		{
			minX = bounds.minX();
			minY = bounds.minY();
			width = bounds.width();
			height = bounds.height();
		}

		@Override
		public void fromDataStream(final IDataStreamReader reader) throws IOException
		{
			minX = reader.readDouble(MIN_X);
			minY = reader.readDouble(MIN_Y);
			width = reader.readDouble(WIDTH);
			height = reader.readDouble(HEIGHT);
		}

		@Override
		public void toDataStream(final IDataStreamWriter writer) throws IOException
		{
			writer.writeDouble(MIN_X, minX);
			writer.writeDouble(MIN_Y, minY);
			writer.writeDouble(WIDTH, width);
			writer.writeDouble(HEIGHT, height);
		}

		public Bounds2D construct()
		{
			return Bounds2D.of(minX, minY, width, height);
		}
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